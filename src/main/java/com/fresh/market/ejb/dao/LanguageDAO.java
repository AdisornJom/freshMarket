/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;


import com.fresh.market.core.ejb.entity.Language;
import com.fresh.market.core.util.Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class LanguageDAO extends AbstractDAO<Language> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LanguageDAO() {
        super(Language.class);
    }

    public List<Language> findLanguageList() throws Exception {
        Query q = em.createQuery("select o from Language o where o.status = 1");
        return q.getResultList();
    }

}
