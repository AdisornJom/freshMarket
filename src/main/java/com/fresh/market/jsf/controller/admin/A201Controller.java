package com.fresh.market.jsf.controller.admin;

import com.fresh.market.ejb.facade.AdminFacade;
import com.fresh.market.jsf.common.UserInfoController;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.MD5Generator;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.core.util.StringUtil;
import com.fresh.market.core.ejb.entity.AdminUser;
import com.fresh.market.core.ejb.entity.AdminUserRole;
import com.fresh.market.core.ejb.entity.AdminWhitelist;
import com.fresh.market.core.ejb.entity.AdminWhitelistPK;
import com.fresh.market.core.util.Constants;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named(A201Controller.CONTROLLER_NAME)
public class A201Controller implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(A201Controller.class);
    public static final String CONTROLLER_NAME = "a201Controller";

    @Inject
    private UserInfoController userInfo;
    @Inject
    private AdminFacade adminFacade;
    //
    private List<AdminUser> items;
    private AdminUser selected;
    private AdminWhitelist ip_selected;

    //find by criteria
    private String username;
    private String firstname;
    private String choice;
    private String choiceValue;
    private Integer status;

    @PostConstruct
    public void init() {
        if (null == choice) {
            choice = "username";
        }
        search();
    }

    public void genSound() {
        if (null != selected && null != selected.getSound() && !selected.getSound().equals("")) {
            RequestContext.getCurrentInstance().execute("(document.getElementById('audio')).src='resources/sound/" + selected.getSound() + "'");
        } else {
            RequestContext.getCurrentInstance().execute("(document.getElementById('audio')).src=''");
        }
        //RequestContext.getCurrentInstance().update("listForm:sound1");
        RequestContext.getCurrentInstance().update("audio");
    }

    public void search() {
        try {
            items = adminFacade.findAdminUserListByCriteria(StringUtils.trimToEmpty(choice), StringUtils.trimToEmpty(choiceValue), status);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public String create() {
        try {
            if (StringUtils.isBlank(selected.getFirstName())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.firstname.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getLastName())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.lastname.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getPosition())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.position.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getOrganization())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.organization.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getEmail())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.email.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getMobile())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.mobile.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (!StringUtils.equals(selected.getPassword(), selected.getConfirmPassword())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.1018"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (adminFacade.isExistUser(selected.getUsername(), selected.getEmail())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.1002", selected.getUsername(), selected.getEmail()));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (!StringUtil.validateEmail(selected.getEmail())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.1003"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (!StringUtil.validateUserName_back(selected.getUsername())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.1008"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (!StringUtil.validatePasswd2(selected.getPassword())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.1005", "8"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

//            if (Objects.equals(Constants.ROLE_AUDIT,selected.getRoleId().getId()) || Objects.equals(Constants.ROLE_STAFF,selected.getRoleId().getId())) {
//                if(selected.getAdminWhitelistList().isEmpty()){ 
//                    Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", MessageBundleLoader.getMessage("a201.profile.ipaddress")));
//                    RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
//                    return null;
//                }
//            }

            selected.setPassword(MD5Generator.md5(selected.getPassword()));
            selected.setId(UUID.randomUUID().toString());

            List<AdminWhitelist> detal_add = new ArrayList();
            if (selected.getAdminWhitelistList() != null && !selected.getAdminWhitelistList().isEmpty()) {
                detal_add.addAll(selected.getAdminWhitelistList());
            }

            selected.setAdminWhitelistList(new ArrayList());
            selected.setStatus(1);
            selected.setUsed(1);
            selected.setCreatedBy(userInfo.getAdminUser().getUsername());
            selected.setCreatedDt(DateTimeUtil.getSystemDate());
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            adminFacade.createAdminUser(selected);

            //create ip access
            for (AdminWhitelist adminWhitelist : detal_add) {
                adminWhitelist.getAdminWhitelistPK().setUserId(selected.getId());
                adminFacade.createAdminWhiteList(adminWhitelist);
            }

            items = adminFacade.findSysUserList();
            //refresh data
            clearData();
            init();
            Messages.addInfo("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.4001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            return null;
        } catch (NoSuchAlgorithmException ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        } catch (UnsupportedEncodingException ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }

    public String prepareEdit() {
        return "edit?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = new AdminUser();
        selected.setSound("noti.mp3");
        selected.setRoleId(new AdminUserRole());

        ip_selected = new AdminWhitelist();
        ip_selected.setAdminWhitelistPK(new AdminWhitelistPK());
        return "create?faces-redirect=true";
    }

    public String edit() {
        try {
            if (StringUtils.isBlank(selected.getFirstName())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.firstname.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getLastName())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.lastname.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getPosition())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.position.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getOrganization())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.organization.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getEmail())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.email.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isBlank(selected.getMobile())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("a201.profile.mobile.validate"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (!StringUtil.validateEmail(selected.getEmail())) {
                Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.1003"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (StringUtils.isNotBlank(selected.getNewPassword()) || StringUtils.isNotBlank(selected.getConfirmPassword())) {
                if (!StringUtils.equals(selected.getNewPassword(), selected.getConfirmPassword())) {
                    Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.1018"));
                    RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                    return null;
                }

                if (!StringUtil.validatePasswd2(selected.getNewPassword())) {
                    Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.1004"));
                    RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                    return null;
                }

                selected.setPassword(MD5Generator.md5(selected.getNewPassword()));
            }

            //deleteDetail
            adminFacade.deleteAdminWhiteList(selected.getId());

            //insertDetail
            List<AdminWhitelist> detail_edit = new ArrayList();
            for (AdminWhitelist adminWhitelist : selected.getAdminWhitelistList()) {
                adminWhitelist.getAdminWhitelistPK().setUserId(selected.getId());
                detail_edit.add(adminWhitelist);
            }
            selected.setAdminWhitelistList(detail_edit);

            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            adminFacade.editAdminUser(selected);
            search();
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessageFormat("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    public String cancel() {
        search();
        return "index?faces-redirect=true";
    }

    public void delete() {
        try {
            selected.setUsed(0);
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            adminFacade.editAdminUser(selected);
            //refresh data
            items = adminFacade.findAdminUserListByCriteria(username, firstname, status);
        } catch (Exception ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
    }

    public void addDetail() {
        try {
            //validate field iteam 
            if (selected == null || ip_selected.getAdminWhitelistPK().getIp() == null) {
                return;
            }

            if (!StringUtil.validateIPAddress(ip_selected.getAdminWhitelistPK().getIp())) {
                clearData_whlitlistip();
                return;
            }

            if (selected.getAdminWhitelistList() == null) {
                selected.setAdminWhitelistList(new ArrayList<>());
            }

            ip_selected.getAdminWhitelistPK().setUserId(selected.getId());
            ip_selected.setCreatedBy(userInfo.getAdminUser().getUsername());
            ip_selected.setCreatedDt(DateTimeUtil.getSystemDate());
            ip_selected.setModifieldBy(userInfo.getAdminUser().getUsername());
            ip_selected.setModifieldDt(DateTimeUtil.getSystemDate());

            //is match
            List<String> list = new ArrayList();
            for (AdminWhitelist adminWhitelist : selected.getAdminWhitelistList()) {
                list.add(adminWhitelist.getAdminWhitelistPK().getIp());
            }
            if (!list.contains(ip_selected.getAdminWhitelistPK().getIp())) {
                selected.getAdminWhitelistList().add(ip_selected);
            }
            clearData_whlitlistip();
        } catch (Exception ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
    }

    public void deleteDetail() {
        try {
            //delete ip 
            selected.getAdminWhitelistList().remove(ip_selected);
            // adminFacade.deleteAdminWhiteList(ip_selected);
            clearData_whlitlistip();
        } catch (Exception ex) {
            Messages.addError("listForm:edit_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
    }

    public void clearData_whlitlistip() {
        ip_selected = new AdminWhitelist();
        ip_selected.setAdminWhitelistPK(new AdminWhitelistPK());
    }

    public void addWhitelist() {
        ip_selected = new AdminWhitelist();
        ip_selected.setAdminWhitelistPK(new AdminWhitelistPK());
    }

    private void clearData() {
        selected = new AdminUser();
        AdminUserRole adUserRole = new AdminUserRole();
        selected.setRoleId(adUserRole);
    }

    public List<AdminUser> getItems() {
        return items;
    }

    public void setItems(List<AdminUser> items) {
        this.items = items;
    }

    public AdminUser getSelected() {
        if (null == selected.getRefresh()) {
            selected.setRefresh(10);
        }
        return selected;
    }

    public void setSelected(AdminUser selected) {
        this.selected = selected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AdminWhitelist getIp_selected() {
        return ip_selected;
    }

    public void setIp_selected(AdminWhitelist ip_selected) {
        this.ip_selected = ip_selected;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }

}
