/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;

import com.fresh.market.core.util.Persistence;
import com.fresh.market.core.ejb.entity.AdminUserRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class AdminUserRoleDAO extends AbstractDAO<AdminUserRole> {
    @PersistenceContext(unitName =Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminUserRoleDAO() {
        super(AdminUserRole.class);
    }
    
}
