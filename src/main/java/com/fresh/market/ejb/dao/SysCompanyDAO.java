package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysCompany;
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
public class SysCompanyDAO extends AbstractDAO<SysCompany> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysCompanyDAO() {
        super(SysCompany.class);
    }

    public List<SysCompany> findSysCompanyByCriteria(String companyNameTh, String status, int[] range) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT u FROM SysCompany u WHERE 1=1");

        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.status = :status ");
        }
        if (StringUtils.isNotBlank(companyNameTh)) {
            sb.append("AND lower(u.companyNameTh) LIKE :companyNameTh ");
        }

        sb.append("ORDER BY u.createdDt DESC ");

        Query q = em.createQuery(sb.toString());
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }
        if (StringUtils.isNotBlank(companyNameTh)) {
            q.setParameter("companyNameTh", "%" + StringUtils.lowerCase(companyNameTh) + "%");
        }

        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countSysCompanyByCriteria(String companyNameTh, String status) {
        StringBuilder sb = new StringBuilder("SELECT count(u) FROM SysCompany u WHERE 1=1 ");

        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.status = :status ");
        }
        if (StringUtils.isNotBlank(companyNameTh)) {
            sb.append("AND lower(u.companyNameTh) LIKE :companyNameTh ");
        }

        Query q = em.createQuery(sb.toString());
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }
        if (StringUtils.isNotBlank(companyNameTh)) {
            q.setParameter("companyNameTh", "%" + StringUtils.lowerCase(companyNameTh) + "%");
        }

        return ((Number) q.getSingleResult()).intValue();
    }
}
