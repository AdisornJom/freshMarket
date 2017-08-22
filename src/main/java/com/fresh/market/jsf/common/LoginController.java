package com.fresh.market.jsf.common;


import com.fresh.market.core.util.Constants;
import com.fresh.market.core.util.JsfUtil;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.core.util.StringUtil;
import com.fresh.market.ejb.facade.CoreFacade;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author MR Adisorn.jo 
 */
@SessionScoped
@ManagedBean(name = LoginController.CONTROLLER_NAME)
public class LoginController extends BaseController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    public static final String CONTROLLER_NAME = "loginController";
    private String username;
    private String password;
    private String captchaCode;

    //
    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager;
    @Inject
    private LogController log;
    @Inject
    private GlobalController globalController;
    @Inject
    private NotiController notiController;
    //
    @Inject
    private CoreFacade coreFacade;

    // For sound
    private Long depositCount;
    private Long withdrawCount;
    //

    @PostConstruct
    @Override
    public void init() {
    }


    public void putData2Global() {
        globalController.putData(username, new Date());
    }

    public void login() {
        try {
            ExternalContext context = Faces.getExternalContext();
            if (context.getUserPrincipal() != null) {
                String serverName = context.getRequestServerName();
                if (StringUtils.equals(serverName, "localhost") || StringUtils.equals(serverName, "127.0.0.1")) {
                    Faces.redirect(context.getRequestContextPath());
                    Faces.responseComplete();
                } else {
                    Faces.redirect("/");
                    Faces.responseComplete();
                }
                return;
            }

            if (StringUtils.isBlank(username)) {
                captchaCode = "";
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "บัญชีผู้ใช้งาน"));
                return;
            }
            if (StringUtils.isBlank(password)) {
                captchaCode = "";
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รหัสผ่าน"));
                return;
            }

            if (StringUtils.isBlank(captchaCode)) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รหัสภาพ"));
                return;
            }

            HttpSession session = (HttpSession) context.getSession(false);
            Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);

            if (!captcha.isCorrect(captchaCode)) {
                captchaCode = "";
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.2025"));
                return;
            }

            // Check IP Whitelist
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddr = req.getHeader("X-FORWARDED-FOR");
            if (ipAddr == null) {
                ipAddr = req.getRemoteAddr();
            }
            ipAddr = StringUtil.getMyRealIp(ipAddr);

            if (!coreFacade.checkIPWhitelist(username, ipAddr)) {
                captchaCode = "";
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.2026"));
                return;
            }

            // Check only 1 login / time
            Date lastDatePoll = globalController.getData(username);
            Date currentDate = new Date();
            if (null != lastDatePoll) {
                if (currentDate.getTime() - lastDatePoll.getTime() < (Constants.INTERVAL_POLL * 1000) + 2000) {
                    captchaCode = "";
                    JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.9006", username));
                    return;
                }
            } 

            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext secu = SecurityContextHolder.getContext();
            secu.setAuthentication(result);

            UserInfoController userInfoController = (UserInfoController) JsfUtil.getManagedBeanValue(UserInfoController.CONTROLLER_NAME);
            userInfoController.init();

            log.login();

            //For Issue only 1 login / time
            putData2Global();

            //
           // notiController.chkNotify();

            String serverName = context.getRequestServerName();
            if (StringUtils.equals(serverName, "localhost") || StringUtils.equals(serverName, "127.0.0.1")) {
                Faces.redirect(context.getRequestContextPath());
                Faces.responseComplete();
            } else {
                Faces.redirect("/");
                Faces.responseComplete();
            }

        } catch (IOException ex) {
            JsfUtil.addFacesErrorMessage(ex.getMessage());
            LOG.error(ex.getMessage(), ex);
        } catch (AuthenticationException ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.1001"));
            LOG.error(ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }

    }

    public void isLogin() {
        try {
            Principal principal = Faces.getExternalContext().getUserPrincipal();
            if (principal != null) {
                ExternalContext context = Faces.getExternalContext();
                context.redirect(context.getRequestContextPath());
                Faces.responseComplete();
            }

        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public synchronized void logout() throws IOException {
        //this.username = null;
        //this.password = null;
        Faces.redirect(Faces.getRequestContextPath() + "/j_spring_security_logout");
        Faces.responseComplete();
        globalController.putData(username, null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public Long getDepositCount() {
        return depositCount;
    }

    public void setDepositCount(Long depositCount) {
        this.depositCount = depositCount;
    }

    public Long getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(Long withdrawCount) {
        this.withdrawCount = withdrawCount;
    }
}
