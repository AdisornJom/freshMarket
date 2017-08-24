package com.fresh.market.jsf.controller.custom;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.ejb.facade.CustomFacade;
import com.fresh.market.jsf.common.UserInfoController;
import com.fresh.market.jsf.model.LazyCompanyDataModel;
import java.io.Serializable;
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
@Named(C101Controller.CONTROLLER_NAME)
public class C101Controller implements Serializable {

    public static final String CONTROLLER_NAME = "c101Controller";
    private static final Logger LOG = LoggerFactory.getLogger(C101Controller.class);

    @Inject
    private UserInfoController userInfo;
    @Inject
    private CustomFacade customFacade;

    private List<SysCompany> items;
    private SysCompany selected;
    
    private LazyDataModel<SysCompany> lazyModel;

    //find by criteria
    private String companyName;
    private String status;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        try {
            lazyModel = new LazyCompanyDataModel(customFacade, StringUtils.trimToEmpty(companyName),"Y");
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("listForm:itemTable");
            if (null != dataTable) {
                dataTable.setFirst(0);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public String prepareCreate() {
        selected = new SysCompany();
        return "create?faces-redirect=true";
    }

    public String prepareEdit() {
        return "edit?faces-redirect=true";
    }
    //..........................................................................................................................................................................................................................      
   public String create() {
        try {

            if (StringUtils.isEmpty(selected.getCompanyNameTh())) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อสินค้า(ไทย)"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            selected.setCreatedBy(userInfo.getAdminUser().getUsername());
            selected.setCreatedDt(DateTimeUtil.getSystemDate());
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            customFacade.createSysCompany(selected);

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
            if (StringUtils.isEmpty(selected.getCompanyNameTh())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อสินค้า(ไทย)"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            customFacade.editSysCompany(selected);
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
            customFacade.deleteSysCompany(selected);
            
            search();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }


    private void clearData() {
         selected=new SysCompany();
    }

    public String cancel() {
        clearData();
        search();
        return "index?faces-redirect=true";
    }

    public List<SysCompany> getItems() {
        return items;
    }

    public void setItems(List<SysCompany> items) {
        this.items = items;
    }

    public SysCompany getSelected() {
        return selected;
    }

    public void setSelected(SysCompany selected) {
        this.selected = selected;
    }

    public LazyDataModel<SysCompany> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SysCompany> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    
}
