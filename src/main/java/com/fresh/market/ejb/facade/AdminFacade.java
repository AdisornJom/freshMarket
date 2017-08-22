package com.fresh.market.ejb.facade;

import com.fresh.market.ejb.bo.AdminBO;
import com.fresh.market.core.ejb.entity.AdminUser;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import com.fresh.market.core.ejb.entity.AdminWhitelist;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class AdminFacade implements Serializable {

    @EJB
    private AdminBO adminBO;

    public List<AdminUser> findSysUserList() throws Exception {
        return adminBO.findSysUserList();
    }

    public List<AdminUserLog> findSysUserLogList() throws Exception {
        return adminBO.findSysUserLogList();
    }

    public boolean isExistUser(String username, String email) throws Exception {
        return adminBO.isExistUser(username, email);
    }

    public void createAdminUser(AdminUser adminUser) throws Exception {
        adminBO.createAdminUser(adminUser);
    }

    public void editAdminUser(AdminUser adminUser) throws Exception {
        adminBO.editAdminUser(adminUser);
    }

    public void deleteAdminUser(AdminUser adminUser) throws Exception {
        adminBO.deleteAdminUser(adminUser);
    }

    public List<AdminUser> findAdminUserListByCriteria(String choice, String choiceValue, Integer status) throws Exception {
        return adminBO.findAdminUserListByCriteria(choice, choiceValue, status);
    }

    public List<AdminUserLog> findSysUserLogListByCriteria(String username, String firstname) throws Exception {
        return adminBO.findSysUserLogListByCriteria(username, firstname);
    }

    public void deleteAdminWhiteList(String userId) throws Exception {
        adminBO.deleteAdminWhiteList(userId);
    }

    public void createAdminWhiteList(AdminWhitelist adminWhitelist) throws Exception {
        adminBO.createAdminWhiteList(adminWhitelist);
    }
}
