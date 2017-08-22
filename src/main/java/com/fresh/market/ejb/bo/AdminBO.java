package com.fresh.market.ejb.bo;

import com.fresh.market.ejb.dao.AdminUserDAO;
import com.fresh.market.ejb.dao.AdminUserLogDAO;
import com.fresh.market.ejb.dao.AdminWhiteListDAO;
import com.fresh.market.core.ejb.entity.AdminUser;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import com.fresh.market.core.ejb.entity.AdminWhitelist;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless(name = "market.AdminBO")
public class AdminBO {

    @EJB
    private AdminUserDAO userDAO;
    @EJB
    private AdminUserLogDAO userLogDAO;
    @EJB
    private AdminWhiteListDAO adminWhiteListDAO;

    public List<AdminUser> findSysUserList() throws Exception {
        List<AdminUser> rtnList = userDAO.findSysUserList();
        for(AdminUser a : rtnList){
            a.getAdminWhitelistList().toString();
        }
        return rtnList;
    }

    public List<AdminUserLog> findSysUserLogList() throws Exception {
        return userLogDAO.findAll();
    }

    public List<AdminUser> findAdminUserListByCriteria(String choice, String choiceValue, Integer status) throws Exception {
        List<AdminUser> list = userDAO.findAdminUserListByCriteria(choice, choiceValue, status);
        for (AdminUser l : list) {
            l.getAdminWhitelistList().toString();
        }
        return list;
    }

    public List<AdminUserLog> findSysUserLogListByCriteria(String username, String firstname) throws Exception {
        return userLogDAO.findSysUserLogListByCriteria(username, firstname);
    }

    public boolean isExistUser(String username, String email) throws Exception {
        AdminUser useradmin = userDAO.findByExistUser(username, email);
        return useradmin != null;
    }

    public void createAdminUser(AdminUser adminUser) throws Exception {
        adminUser.setId(UUID.randomUUID().toString());
        adminUser.setStatus(1);
        adminUser.setUsed(1);
        userDAO.create(adminUser);
    }

    public void editAdminUser(AdminUser adminUser) throws Exception {
        userDAO.edit(adminUser);
    }

    public void deleteAdminUser(AdminUser adminUser) throws Exception {
        userDAO.edit(adminUser);
    }
    
    public void deleteAdminWhiteList(String userId) throws Exception {
        adminWhiteListDAO.deleteWhitelistByUserId(userId);
    }
    
    public void createAdminWhiteList(AdminWhitelist adminWhitelist) throws Exception {
        adminWhiteListDAO.create(adminWhitelist);
    }
}
