package com.fresh.market.jsf.common;

import com.fresh.market.ejb.facade.CoreFacade;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import com.fresh.market.core.util.DateTimeUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MR Adisorn.jo  (
 */
@SessionScoped
@Named(LogController.CONTROLLER_NAME)
public class LogController extends BaseController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(LogController.class);
    public static final String CONTROLLER_NAME = "logController";
    public static final String TYPE_LOGIN = "Login";
    public static final String TYPE_ACCESS = "Access";
    public static final String TYPE_UPDATE = "Update";
    public static final String TYPE_DELETE = "Delete";
    public static final String TYPE_INSERT = "Insert";
    public static final String TYPE_IMPORT = "Import";

    @Inject
    private UserInfoController info;

    @Inject
    private CoreFacade coreFacade;

    @PostConstruct
    @Override
    public void init() {
    }

    public void login() {
        try {
            AdminUserLog log = new AdminUserLog();
            log.setType(TYPE_LOGIN);
            log.setBrowser(info.getBrowser());
            log.setDetail(null);
            log.setDevice(info.getDevice());
            log.setIp(info.getIpAddr());
            log.setUserId(info.getAdminUser());
            log.setCreatedDt(DateTimeUtil.getSystemDate());
            coreFacade.createSysUserLog(log);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public void access(String detail) {
        try {
            AdminUserLog log = new AdminUserLog();
            log.setType(TYPE_ACCESS);
            log.setBrowser(info.getBrowser());
            log.setDetail(detail);
            log.setDevice(info.getDevice());
            log.setIp(info.getIpAddr());
            log.setUserId(info.getAdminUser());
            log.setCreatedDt(DateTimeUtil.getSystemDate());
            coreFacade.createSysUserLog(log);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

}
