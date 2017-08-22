package com.fresh.market.ejb.facade;


import com.fresh.market.core.ejb.entity.AdminUser;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import com.fresh.market.ejb.bo.CoreBO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class CoreFacade {

    @EJB
    private CoreBO coreBO;

    public AdminUser findUserByUsername(String username) throws Exception {
        return coreBO.findUserByUsername(username);
    }

    public boolean checkIPWhitelist(String username, String ip) throws Exception {
        return coreBO.checkIPWhitelist(username, ip);
    }

    public void createSysUserLog(AdminUserLog log) throws Exception {
        coreBO.createSysUserLog(log);
    }

    public List<AdminUserLog> findSysUserLogList(AdminUser user) throws Exception {
        return coreBO.findSysUserLogList(user);
    }

}
