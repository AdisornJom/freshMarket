/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysSeleteitem;
import com.fresh.market.core.util.Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class SysSeleteItemDAO extends AbstractDAO<SysSeleteitem> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysSeleteItemDAO() {
        super(SysSeleteitem.class);
    }
    


     public List<SysSeleteitem> findSysSeleteitemByCriteria(String name) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o FROM SysSeleteitem o ");
        sb.append("where 1=1 ");
        if(null != name && name.length() > 0){
            sb.append("and o.name = :name ");
        }

        sb.append(" order by typeId asc");

        Query q = em.createQuery(sb.toString());
        if(null != name && name.length() > 0){
            q.setParameter("name", name);
        }
 
       
        return q.getResultList();
    }
}
