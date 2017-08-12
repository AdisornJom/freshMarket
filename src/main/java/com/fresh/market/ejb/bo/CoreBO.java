/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.bo;

import com.fresh.market.ejb.dao.AdminUserDAO;
import com.fresh.market.ejb.dao.AdminUserLogDAO;
import com.fresh.market.ejb.dao.AdminWhiteListDAO;
import com.fresh.market.core.ejb.entity.AdminUser;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Aekasit
 */
@Stateless(name = "market.CoreBO")
public class CoreBO {

    @EJB
    private AdminUserDAO sysUserDAO;
    @EJB
    private AdminUserLogDAO sysUserLogDAO;
    @EJB
    private AdminWhiteListDAO adminWhiteListDAO;

    public AdminUser findUserByUsername(String username) throws Exception {
        return sysUserDAO.findByUsername(username);
    }

    public void createSysUserLog(AdminUserLog log) throws Exception {
        sysUserLogDAO.create(log);
    }

    public List<AdminUserLog> findSysUserLogList(AdminUser user) throws Exception {
        return sysUserLogDAO.findSysUserLogList(user);
    }

    public boolean checkIPWhitelist(String username, String ip) throws Exception {
        return adminWhiteListDAO.checkIPWhitelist(username, ip);
    }

}
