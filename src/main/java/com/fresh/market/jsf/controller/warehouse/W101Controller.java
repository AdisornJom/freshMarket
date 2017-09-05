package com.fresh.market.jsf.controller.warehouse;

import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.ejb.facade.WareHouseFacade;
import com.fresh.market.jsf.common.UserInfoController;
import com.fresh.market.jsf.model.LazyItemDataModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named(W101Controller.CONTROLLER_NAME)
public class W101Controller implements Serializable {

    public static final String CONTROLLER_NAME = "w101Controller";
    private static final Logger LOG = LoggerFactory.getLogger(W101Controller.class);

    @Inject
    private UserInfoController userInfo;
    @Inject
    private WareHouseFacade wareHouseFacade;

    private List<SysItem> items;
    private SysItem selected;
    
    private LazyDataModel<SysItem> lazyModel;

    //find by criteria
    private String itemName;
    private String status;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        try {
            lazyModel = new LazyItemDataModel(wareHouseFacade, StringUtils.trimToEmpty(itemName),"Y");
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("listForm:itemTable");
            if (null != dataTable) {
                dataTable.setFirst(0);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public String prepareCreate() {
        selected = new SysItem();
        return "create?faces-redirect=true";
    }

    public String prepareEdit() {
        return "edit?faces-redirect=true";
    }
    //..........................................................................................................................................................................................................................      
   public String create() {
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


    private void clearData() {
         selected=new SysItem();
    }

    public String cancel() {
        clearData();
        search();
        return "index?faces-redirect=true";
    }

    public List<SysItem> getItems() {
        return items;
    }

    public void setItems(List<SysItem> items) {
        this.items = items;
    }

    public SysItem getSelected() {
        return selected;
    }

    public void setSelected(SysItem selected) {
        this.selected = selected;
    }

    public LazyDataModel<SysItem> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SysItem> lazyModel) {
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

    
}
