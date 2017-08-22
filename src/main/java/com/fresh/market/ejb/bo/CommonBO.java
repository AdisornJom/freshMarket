/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.bo;

import com.fresh.market.core.ejb.entity.AdminUserRole;
import com.fresh.market.core.ejb.entity.Language;
import com.fresh.market.core.ejb.entity.SysSeleteitem;
import com.fresh.market.ejb.dao.AdminUserRoleDAO;
import com.fresh.market.ejb.dao.LanguageDAO;
import com.fresh.market.ejb.dao.SysSeleteItemDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless(name = "market.CommonBO")
public class CommonBO {

    @EJB
    private AdminUserRoleDAO sysUserRoleDAO;
    @EJB
    private LanguageDAO languageDAO;
    @EJB
    private SysSeleteItemDAO sysSeleteItemDAO;

    public List<AdminUserRole> findAdminUserRoleList() throws Exception {
        return sysUserRoleDAO.findAll();
    }

    public List<Language> findLanguageList() throws Exception {
        return languageDAO.findLanguageList();
    }
    
    public List<SysSeleteitem> findSysSeleteitemByCriteria(String name) throws Exception {
       return sysSeleteItemDAO.findSysSeleteitemByCriteria(name);
    }
}

