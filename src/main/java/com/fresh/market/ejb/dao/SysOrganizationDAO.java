/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;


import com.fresh.market.core.ejb.entity.SysOrganization;
import javax.ejb.Stateless;
import com.fresh.market.core.util.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class SysOrganizationDAO extends AbstractDAO<SysOrganization> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysOrganizationDAO() {
        super(SysOrganization.class);
    }
    
    public SysOrganization findSysOrganizationByStatus(String status) {
        Query q = em.createQuery("select o from SysOrganization o "
                + "where o.status=:status ");
        q.setParameter("status", status );
       
        try {
            return (SysOrganization)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
   
    
}
