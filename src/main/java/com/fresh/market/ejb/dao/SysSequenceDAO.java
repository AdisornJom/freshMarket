/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysSequence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;


@Stateless
public class SysSequenceDAO extends AbstractDAO<SysSequence> {

    @PersistenceContext(unitName = com.fresh.market.core.util.Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysSequenceDAO() {
        super(SysSequence.class);
    }
    
    
    public List<SysSequence> findSysSequenceList() {
        Query q = em.createQuery("select o from SysSequence o ");
        return q.getResultList();
    }
     
     public List<SysSequence> findSysSequenceListByCriteria(String custName) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o FROM SysSequence o ");
        sb.append("where 1=1");
        if(null != custName && custName.length() > 0){
            sb.append("and o.customerId.customerNameTh like :custName ");
        }

        Query q = em.createQuery(sb.toString());
        if(null != custName && custName.length() > 0){
            q.setParameter("custName", "%" + custName + "%");
        }

        return q.getResultList();
    }
     
    
     public SysSequence findSysSequenceByCustomerIdRunningType(String runningType) {        
        StringBuilder sb = new StringBuilder();
        sb.append("select o from SysSequence o ");
        sb.append("where 1=1 ");

        if (null != runningType) {
            sb.append("and o.runningType = :runningType ");
        }

        Query q = em.createQuery(sb.toString());
        if (null != runningType) {
            q.setParameter("runningType", runningType);
        }
        
        try {
            return (SysSequence)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
