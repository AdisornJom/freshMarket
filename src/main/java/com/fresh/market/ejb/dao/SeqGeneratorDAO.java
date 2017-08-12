/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.ejb.dao;

import com.fresh.market.core.util.Persistence;
import com.fresh.market.core.ejb.entity.SeqGenerator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aekasit
 */
@Stateless
public class SeqGeneratorDAO {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    public Long getPimaryKey(String table) {
        try {
            Query q = em.createNativeQuery("SELECT nextval('" + table + "') as ID", SeqGenerator.class);
            SeqGenerator seq = (SeqGenerator) q.getSingleResult();
            return seq.getId();
        } catch (Exception e) {
            return null;
        }
    }
}
