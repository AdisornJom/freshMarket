package com.fresh.market.jsf.common;

import com.fresh.market.ejb.facade.AdminFacade;
import com.fresh.market.ejb.facade.CoreFacade;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.DeviceUtil;
import com.fresh.market.core.util.JsfUtil;
import com.fresh.market.core.util.MD5Generator;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.core.util.StringUtil;
import com.fresh.market.core.ejb.entity.AdminUser;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.poseidon.view.GuestPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named(UserInfoController.CONTROLLER_NAME)
public class UserInfoController extends BaseController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(UserInfoController.class);
    public static final String CONTROLLER_NAME = "userInfoController";

    @Inject
    private AdminFacade adminFacade;

    @Inject
    private CoreFacade coreFacade;

    private AdminUser adminUser;
    private String ipAddr;
    private String browser;
    private String pass1;
    private String pass2;
    private String device;
    private String menuLayout;
    private String theme;

    @PostConstruct
    @Override
    public void init() {
        try {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            adminUser = coreFacade.findUserByUsername(principal.getName());
            HttpServletRequest req = (HttpServletRequest) JsfUtil.getFacesContext().getExternalContext().getRequest();

            menuLayout = adminUser.getMenuModes();
            theme = adminUser.getTheme();
            GuestPreferences guestPreferences = (GuestPreferences) JsfUtil.getManagedBeanValue("guestPreferences");

            if (StringUtils.isNotBlank(menuLayout)) {
                guestPreferences.setMenuLayout(menuLayout);
            }
            if (StringUtils.isNotBlank(theme)) {
                guestPreferences.setTheme(theme);
            }

            ipAddr = req.getHeader("X-FORWARDED-FOR");
            if (ipAddr == null) {
                ipAddr = req.getRemoteAddr();
            }
            ipAddr = StringUtil.getMyRealIp(ipAddr);

            browser = DeviceUtil.getBrowser(req);
            device = DeviceUtil.isDevice((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void edit() {
        try {
            if (StringUtils.isBlank(adminUser.getFirstName())
                    || StringUtils.isBlank(adminUser.getLastName())
                    || StringUtils.isBlank(adminUser.getEmail())
                    || StringUtils.isBlank(adminUser.getPosition())
                    || StringUtils.isBlank(adminUser.getMobile())) {

                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.2001"));
                return;
            }

            if (!StringUtil.validateEmail(adminUser.getEmail())) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.1003"));
                return;
            }

            if (StringUtils.isNotBlank(adminUser.getNewPassword()) || StringUtils.isNotBlank(adminUser.getConfirmPassword())) {
                if (!StringUtils.equals(adminUser.getNewPassword(), adminUser.getConfirmPassword())) {
                    JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.1018"));
                    return;
                }

                if (!StringUtil.validatePasswd2(adminUser.getNewPassword())) {
                    JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.1004"));
                    return;
                }

                adminUser.setPassword(MD5Generator.md5(adminUser.getNewPassword()));
            }

            GuestPreferences guestPreferences = (GuestPreferences) JsfUtil.getManagedBeanValue("guestPreferences");
            guestPreferences.setMenuLayout(menuLayout);
            guestPreferences.setTheme(theme);

            adminUser.setMenuModes(menuLayout);
            adminUser.setTheme(theme);

            adminUser.setModifiedBy(adminUser.getUsername());
            adminUser.setModifiedDt(DateTimeUtil.getSystemDate());
            adminFacade.editAdminUser(adminUser);
            JsfUtil.addFacesInformationMessage(MessageBundleLoader.getMessage("messages.code.4001"));

        } catch (Exception ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.9001"));
            LOG.error(ex.getMessage(), ex);
        }
    }

    public AdminUser getAdminUser() {
        if (null == adminUser.getRefresh()) {
            adminUser.setRefresh(10);
        }
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getMenuLayout() {
        return menuLayout;
    }

    public void setMenuLayout(String menuLayout) {
        this.menuLayout = menuLayout;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
