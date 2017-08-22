/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;

import com.fresh.market.core.util.Persistence;
import com.fresh.market.core.ejb.entity.AdminUserLog;
import com.fresh.market.core.ejb.entity.AdminUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class AdminUserLogDAO extends AbstractDAO<AdminUserLog> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminUserLogDAO() {
        super(AdminUserLog.class);
    }

    public List<AdminUserLog> findSysUserLogList(AdminUser user) throws Exception {
        Query q = em.createQuery("select o from AdminUserLog o where o.userId.id = :userId order by o.createdDt desc");
        q.setParameter("userId", user.getId());
        return q.getResultList();
    }

     public List<AdminUserLog> findSysUserLogListByCriteria(String username,String firstname) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o FROM AdminUserLog o ");
        sb.append("where 1=1 ");
        if(null != username && username.length() > 0){
            sb.append("and lower(o.userId.username) like :username ");
        }
        
        if(null != firstname && firstname.length() > 0){
            sb.append("and lower(o.userId.firstName) like :firstname ");
        }

        Query q = em.createQuery(sb.toString());
        if(null != username && username.length() > 0){
            q.setParameter("username", "%" + StringUtils.lowerCase(username) + "%");
        }
        
        if(null != firstname && firstname.length() > 0){
             q.setParameter("firstname", "%" + StringUtils.lowerCase(firstname) + "%");
        }           
        
        return q.getResultList();
    }
}
