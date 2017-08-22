package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.util.Persistence;
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
public class ItemDetailDAO extends AbstractDAO<SysItem> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemDetailDAO() {
        super(SysItem.class);
    }

    public List<SysItem> findSysItemByCriteria(String itemName, String status, int[] range) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT u FROM SysItem u WHERE 1=1");

        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.itemStatus = :status ");
        }
        if (StringUtils.isNotBlank(itemName)) {
            sb.append("AND lower(u.itemTh) LIKE :itemName ");
        }

        sb.append("ORDER BY u.createdDt DESC ");

        Query q = em.createQuery(sb.toString());
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }
        if (StringUtils.isNotBlank(itemName)) {
            q.setParameter("itemName", "%" + StringUtils.lowerCase(itemName) + "%");
        }

        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countSysItemByCriteria(String itemName, String status) {
        StringBuilder sb = new StringBuilder("SELECT count(u) FROM SysItem u WHERE 1=1 ");

        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.itemStatus = :status ");
        }
        if (StringUtils.isNotBlank(itemName)) {
            sb.append("AND lower(u.itemTh) LIKE :itemName ");
        }

        Query q = em.createQuery(sb.toString());
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }
        if (StringUtils.isNotBlank(itemName)) {
            q.setParameter("itemName", "%" + StringUtils.lowerCase(itemName) + "%");
        }

        return ((Number) q.getSingleResult()).intValue();
    }
}
