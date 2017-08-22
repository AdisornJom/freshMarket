/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;

import com.fresh.market.core.util.Persistence;
import com.fresh.market.core.ejb.entity.AdminWhitelist;
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
public class AdminWhiteListDAO extends AbstractDAO<AdminWhitelist> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminWhiteListDAO() {
        super(AdminWhitelist.class);
    }

    public boolean checkIPWhitelist(String username, String ip) throws Exception {

        Query q = em.createQuery("select b from AdminUser a , AdminWhitelist b where a.id = b.adminWhitelistPK.userId and b.adminUser.username = :username");
        q.setParameter("username", username);

        List<AdminWhitelist> list = q.getResultList();

//        System.out.println("list = " + list.size());

        if (list.isEmpty()) {
            return true;
        }

        for (AdminWhitelist address : list) {
//            System.out.println("check ip : " + address.getAdminWhitelistPK().getIp());
            
            if (StringUtils.equals(address.getAdminWhitelistPK().getIp(), ip)) {
                return true;
            }
            
        }

        return false;
    }

    public void deleteWhitelistByUserId(String userId) throws Exception {
        Query q = em.createQuery("delete from AdminWhitelist o where o.adminWhitelistPK.userId = :userId ");
        q.setParameter("userId", userId);
        q.executeUpdate();
    }
}
