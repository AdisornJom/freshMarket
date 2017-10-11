package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysBilling;
import com.fresh.market.core.util.Persistence;
import java.util.Date;
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
public class SysReportDAO extends AbstractDAO<SysBilling> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysReportDAO() {
        super(SysBilling.class);
    }

    public List<SysBilling> findSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate, int[] range) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT u FROM SysBilling u WHERE 1=1");

        if (null != sysCompany) {
            sb.append("AND u.companyId.companyId = :companyId ");
        }
        if (StringUtils.isNotBlank(documentno)) {
            sb.append("AND u.documentNo = :documentno ");
        }
        if (null !=status) {
            sb.append("AND u.status = :status ");
        }
        if (null != startDate) {
            sb.append("and u.sendDate >= :startDate ");
        }
        if (null != toDate) {
            sb.append("and u.sendDate <= :toDate ");
        }

        sb.append("ORDER BY u.billingId DESC ");

        Query q = em.createQuery(sb.toString());
        if (null != sysCompany) {
            q.setParameter("companyId", sysCompany.getCompanyId());
        }
        if (null != documentno && documentno.length() > 0) {
            q.setParameter("documentno", "%" + documentno + "%");
        }
        if (null !=status) {
            q.setParameter("status", status);
        }
         if (null != startDate) {
            q.setParameter("startDate", startDate);
        }
        if (null != toDate) {
            q.setParameter("toDate", toDate);
        }

        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
}
