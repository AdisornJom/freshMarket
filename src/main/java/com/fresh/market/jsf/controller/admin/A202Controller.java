package com.fresh.market.jsf.controller.admin;

import com.fresh.market.ejb.facade.AdminFacade;
import com.fresh.market.jsf.common.BaseController;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named(A202Controller.CONTROLLER_NAME)
public class A202Controller extends BaseController implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(A202Controller.class);
    public static final String CONTROLLER_NAME = "a202Controller";
    @Inject
    private AdminFacade adminFacade;
    //
    private List<AdminUserLog> items;

    //find by criteria
    private String username;
    private String firstname;

    @PostConstruct
    @Override
    public void init() {
    }

    public void search() {
        try {
            // items = adminFacade.findSysUserLogList();
            items = adminFacade.findSysUserLogListByCriteria(StringUtils.trimToEmpty(username), StringUtils.trimToEmpty(firstname));
            Collections.sort(items, new AdminUserLog());
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

}
