package com.fresh.market.jsf.controller.warehouse;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysItemCompany;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.JsfUtil;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.ejb.facade.CustomFacade;
import com.fresh.market.ejb.facade.WareHouseFacade;
import com.fresh.market.jsf.common.UserInfoController;
import com.fresh.market.jsf.model.LazyItemCompanyDataModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named(W102Controller.CONTROLLER_NAME)
public class W102Controller implements Serializable {

    public static final String CONTROLLER_NAME = "w102Controller";
    private static final Logger LOG = LoggerFactory.getLogger(W102Controller.class);

    @Inject
    private UserInfoController userInfo;
    @Inject
    private WareHouseFacade wareHouseFacade;
    @Inject
    private CustomFacade customFacade;

    private List<SysItemCompany> items;
    private SysItemCompany selected;
    
    private LazyDataModel<SysItemCompany> lazyModel;

    //find by criteria
    private SysCompany sysCompany_find;
    private String itemName;
    private String status;
    private Date startDate;
    private Date toDate;
    
    private SysItem sysItem;
    private List<SysItemCompany> items_add;
    private SysItemCompany selected_add;
    
    //detial 
    private SysItemCompany itemCompanyDetail_selected;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        try {
            lazyModel = new LazyItemCompanyDataModel(wareHouseFacade,(null!=sysCompany_find)?sysCompany_find:null,StringUtils.trimToEmpty(itemName),"Y");
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("listForm:itemTable");
            if (null != dataTable) {
                dataTable.setFirst(0);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public String prepareCreate() {
        selected = new SysItemCompany();
        itemCompanyDetail_selected =new SysItemCompany();
        items_add=new ArrayList();
        return "create?faces-redirect=true";
    }

    public String prepareEdit() {
        return "edit?faces-redirect=true";
    }
    //..........................................................................................................................................................................................................................      
   public String create() {
        try {

            if (null==selected.getCompanyId()) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อบริษัท"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            //insert detail
            for(SysItemCompany detail:items_add){
                detail.setItemCompanyId(null);//auto generate
                detail.setCompanyId(selected.getCompanyId());
                        
                detail.setCreatedBy(userInfo.getAdminUser().getUsername());
                detail.setCreatedDt(DateTimeUtil.getSystemDate());
                detail.setModifiedBy(userInfo.getAdminUser().getUsername());
                detail.setModifiedDt(DateTimeUtil.getSystemDate());
                wareHouseFacade.createSysItemCompany(detail);
            }

            //refresh data
            clearData();
            init();
            Messages.addInfo("listForm:create_msg", MessageBundleLoader.getMessage("messages.code.4001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            return null;
        }  catch (Exception ex) {
            Messages.addError("listForm:create_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }
    
   
    public String edit() {
        try {
            if (selected.getCompanyQty()== null || 0.0 >= selected.getCompanyQty().compareTo(BigDecimal.ZERO)) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "จำนวน"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            if (StringUtils.isEmpty(selected.getCompanyUnit())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "หน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            if (selected.getCompanyPrice()== null || 0.0 >= selected.getCompanyPrice().compareTo(BigDecimal.ZERO)) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ราคาต่อหน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            wareHouseFacade.editSysItemCompany(selected);
            search();
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            Messages.addFlashError("listForm:create_msg", ex.getMessage());
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }
    
    public void delete() {
        try {
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            wareHouseFacade.deleteSysItemCompany(selected);
            
            search();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
    
    
    
    public void addItemDetail(){
        try {
            if (null == selected.getCompanyId()) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อบริษัท"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return;
            }
           //validate field iteam 
            if (sysItem == null) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รายการ"));
                RequestContext.getCurrentInstance().scrollTo("listForm:create_msg");
                return;
            }

            if (itemCompanyDetail_selected.getCompanyQty()== null || 0.0 >= itemCompanyDetail_selected.getCompanyQty().compareTo(BigDecimal.ZERO)) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "จำนวน"));
                RequestContext.getCurrentInstance().scrollTo("listForm:create_msg");
                return;
            }

            if(StringUtils.isEmpty(itemCompanyDetail_selected.getCompanyUnit())){
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "หน่วย"));
                RequestContext.getCurrentInstance().scrollTo("listForm:create_msg");
                return;
            }

            if (itemCompanyDetail_selected.getCompanyPrice()== null || 0.0 >= itemCompanyDetail_selected.getCompanyPrice().compareTo(BigDecimal.ZERO)) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "ราคาต่อหน่วย"));
                RequestContext.getCurrentInstance().scrollTo("listForm:create_msg");
                return;
            }

            //เช็ค ถ้าบริษัทนี้ add รายการไปแล้วหาย insert ซ้ำ
            List<SysItemCompany> itemCompanys=wareHouseFacade.findSysItemCompanyByCriteria(selected.getCompanyId(),sysItem,"Y");
            if(itemCompanys.size()>0){
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รายการสินค้าของบริษัทนี้มีอยู่แล้ว"));
                RequestContext.getCurrentInstance().scrollTo("listForm:create_msg");
                return;
            }

            Random rand = new Random();
            int n = rand.nextInt(500) + 1;
            itemCompanyDetail_selected.setItemCompanyId(n);
            itemCompanyDetail_selected.setItemId(sysItem);

            if(items_add.isEmpty()){
               items_add.add(itemCompanyDetail_selected); 
            }else{
                HashMap ckContains=new HashMap();
                for (SysItemCompany company : items_add) {
                    if (!ckContains.containsKey(company.getItemId().getItemId())) {
                        ckContains.put(company.getItemId().getItemId(), company.getItemId().getItemTh());
                    }
                }
                if(!ckContains.containsKey(sysItem.getItemId())) {
                   items_add.add(itemCompanyDetail_selected); 
                }
            }

            clearData_ItemDetail();
        } catch (Exception ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.9001"));
            LOG.error(ex.getMessage(), ex);
        }
    }  
    
    public void deleteItemDetail(){
        try {
              items_add.remove(selected_add);
        } catch (Exception ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.9001"));
            LOG.error(ex.getMessage(), ex);
        }
    } 
    
    public void clearData_ItemDetail(){
         sysItem = new SysItem();
         itemCompanyDetail_selected=new SysItemCompany();
    }
    
    //Auto Complete==========================================================================  
     //Auto complete company
    public List<SysCompany> completeCompany(String query) {
         List<SysCompany> filteredSysCompany = new ArrayList<>();
       try {
            List<SysCompany> allCompanys = customFacade.findSysCompanyList();
            for (SysCompany sysCompany:allCompanys) {
               if(sysCompany.getCompanyNameTh()!=null && sysCompany.getCompanyNameTh().length()>0){
                if(sysCompany.getCompanyNameTh().toLowerCase().startsWith(query)) {
                    filteredSysCompany.add(sysCompany);
                }
               }
            }
         } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return filteredSysCompany;
    }
    
     //Auto complete SysItem
    public List<SysItem> completeItem(String query) {
         List<SysItem> filteredSysItem= new ArrayList<>();
       try {
            List<SysItem> allSysItem = wareHouseFacade.findSysItemList();
            for (SysItem sysSysItem:allSysItem) {
               if(sysSysItem.getItemTh()!=null && sysSysItem.getItemTh().length()>0){
                if(sysSysItem.getItemTh().toLowerCase().startsWith(query)) {
                    filteredSysItem.add(sysSysItem);
                }
               }
            }
         } catch (Exception ex) {
           LOG.error(ex.getMessage(), ex);
        }
        return filteredSysItem;
    }

 //End Auto Complete==========================================================================  

    private void clearData() {
         selected=new SysItemCompany();
         items_add=new ArrayList();
    }

    public String cancel() {
        clearData();
        search();
        return "index?faces-redirect=true";
    }

    public List<SysItemCompany> getItems() {
        return items;
    }

    public void setItems(List<SysItemCompany> items) {
        this.items = items;
    }

    public SysItemCompany getSelected() {
        return selected;
    }

    public void setSelected(SysItemCompany selected) {
        this.selected = selected;
    }

    public LazyDataModel<SysItemCompany> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SysItemCompany> lazyModel) {
        this.lazyModel = lazyModel;
    }

   

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SysCompany getSysCompany_find() {
        return sysCompany_find;
    }

    public void setSysCompany_find(SysCompany sysCompany_find) {
        this.sysCompany_find = sysCompany_find;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public SysItem getSysItem() {
        return sysItem;
    }

    public void setSysItem(SysItem sysItem) {
        this.sysItem = sysItem;
    }

    public List<SysItemCompany> getItems_add() {
        return items_add;
    }

    public void setItems_add(List<SysItemCompany> items_add) {
        this.items_add = items_add;
    }

    public SysItemCompany getSelected_add() {
        return selected_add;
    }

    public void setSelected_add(SysItemCompany selected_add) {
        this.selected_add = selected_add;
    }

    public SysItemCompany getItemCompanyDetail_selected() {
        return itemCompanyDetail_selected;
    }

    public void setItemCompanyDetail_selected(SysItemCompany itemCompanyDetail_selected) {
        this.itemCompanyDetail_selected = itemCompanyDetail_selected;
    }

    
}
