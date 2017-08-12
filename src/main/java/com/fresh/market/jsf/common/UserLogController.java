package com.fresh.market.jsf.common;

import com.fresh.market.ejb.facade.CoreFacade;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Aekasit
 */
@SessionScoped
@Named(UserLogController.CONTROLLER_NAME)
public class UserLogController extends BaseController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(UserLogController.class);
    public static final String CONTROLLER_NAME = "userLogController";

    @Inject
    private CoreFacade coreFacade;
    @Inject
    private UserInfoController info;
    //
    private List<AdminUserLog> items;

    @PostConstruct
    @Override
    public void init() {
        try {
            items = coreFacade.findSysUserLogList(info.getAdminUser());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public List<AdminUserLog> getItems() {
        return items;
    }

    public void setItems(List<AdminUserLog> items) {
        this.items = items;
    }

}
