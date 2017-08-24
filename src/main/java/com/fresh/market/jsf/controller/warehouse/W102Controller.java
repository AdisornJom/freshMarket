package com.fresh.market.jsf.controller.warehouse;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItemCompany;
import com.fresh.market.ejb.facade.CustomFacade;
import com.fresh.market.ejb.facade.WareHouseFacade;
import com.fresh.market.jsf.common.UserInfoController;
import com.fresh.market.jsf.model.LazyItemCompanyDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.primefaces.component.datatable.DataTable;
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
        return "create?faces-redirect=true";
    }

    public String prepareEdit() {
        return "edit?faces-redirect=true";
    }
    //..........................................................................................................................................................................................................................      
/*   public String create() {
        try {

            if (StringUtils.isEmpty(selected.getItemCode())) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "รหัสสินค้า"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isEmpty(selected.getItemTh())) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อสินค้า"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            if (StringUtils.isEmpty(selected.getItemUnit())) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "หน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            if (selected.getItemPrice()== null || 0.0 >= selected.getItemPrice().compareTo(BigDecimal.ZERO)) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ราคาต่อหน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            selected.setCreatedBy(userInfo.getAdminUser().getUsername());
            selected.setCreatedDt(DateTimeUtil.getSystemDate());
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            wareHouseFacade.createSysItem(selected);

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
            if (StringUtils.isEmpty(selected.getItemCode())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "รหัสสินค้า"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isEmpty(selected.getItemTh())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อสินค้า"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            if (StringUtils.isEmpty(selected.getItemUnit())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "หน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            if (selected.getItemPrice()== null || 0.0 >= selected.getItemPrice().compareTo(BigDecimal.ZERO)) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ราคาต่อหน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            wareHouseFacade.editSysItem(selected);
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
            wareHouseFacade.deleteSysItem(selected);
            
            search();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
    */
    //Auto Complete==========================================================================  
     //Auto complete Foreman
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
 //End Auto Complete==========================================================================  

    private void clearData() {
         selected=new SysItemCompany();
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

    
}
