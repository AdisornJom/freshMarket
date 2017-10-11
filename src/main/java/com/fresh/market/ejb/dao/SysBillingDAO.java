package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysBilling;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysItemTO;
import com.fresh.market.core.util.DateTimeUtil;
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
public class SysBillingDAO extends AbstractDAO<SysBilling> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysBillingDAO() {
        super(SysBilling.class);
    }
    
    public List<SysBilling> findSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) throws Exception {
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
        return q.getResultList();
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

    public int countSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) {
        StringBuilder sb = new StringBuilder("SELECT count(u) FROM SysBilling u WHERE 1=1 ");

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

        return ((Number) q.getSingleResult()).intValue();
    }
    
    public void deleteBillingIdOnDetail(Integer billingId) throws Exception {
        Query q = em.createQuery("delete from SysBillingDetail o where o.billingId.billingId = :id");
        q.setParameter("id", billingId);
        q.executeUpdate();
    }
    
     public List<SysItemTO> detailBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) {
        StringBuilder sb = new StringBuilder("select DISTINCT(it.item_id),stm.item_th from sys_billing a,sys_billing_detail b , sys_item_company it,sys_item stm " +
            "where " +
            "b.billing_id=a.billing_id " +
            "and b.item_company_id=it.item_company_id " +
            "and it.item_id=stm.item_id ");
          //  "and a.send_date='2017-10-03'");

        if (startDate != null) {
            sb.append(" and a.send_date >= '").append(DateTimeUtil.dateToString(startDate, DateTimeUtil.PATTERN_DT_DB)).append("'");
        }
        if (toDate != null) {
            sb.append(" and a.send_date <= '").append(DateTimeUtil.dateToString(toDate, DateTimeUtil.PATTERN_DT_DB)).append("'");
        }
        Query q = em.createNativeQuery(sb.toString(), SysItemTO.class);
        
        return q.getResultList();
    }

     public int volumnDetailBillingByCriteria(Integer billing_id,Integer item_id, Integer status, Date startDate, Date toDate) {
        StringBuilder sb = new StringBuilder("select coalesce(sum(b.volume),0) from sys_billing a,sys_billing_detail b , sys_item_company it,sys_item stm " +
                        " where  " +
                        " b.billing_id=a.billing_id " +
                        " and b.item_company_id=it.item_company_id " +
                        " and it.item_id=stm.item_id ");
                       // "and a.billing_id=2 " +
                      //  "and it.item_id=238 " +
                      //  "and a.billing_date='2017-10-03'");
         if (status != null) {
             sb.append(" and a.status = ").append(status).append("");
         }
         if (billing_id != null) {
             sb.append(" and a.billing_id = ").append(billing_id).append("");
         }
         if (item_id != null) {
             sb.append(" and it.item_id = ").append(item_id).append("");
         }
         if (startDate != null) {
             sb.append(" and a.send_date >= '").append(DateTimeUtil.dateToString(startDate, DateTimeUtil.PATTERN_DT_DB)).append("'");
         }
         if (toDate != null) {
             sb.append(" and a.send_date <= '").append(DateTimeUtil.dateToString(toDate, DateTimeUtil.PATTERN_DT_DB)).append("'");
         }
        Query q = em.createNativeQuery(sb.toString());

        return ((Number) q.getSingleResult()).intValue();
    }
    
}
