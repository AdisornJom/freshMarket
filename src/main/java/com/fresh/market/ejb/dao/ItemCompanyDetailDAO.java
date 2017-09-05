package com.fresh.market.ejb.dao;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysItemCompany;
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
public class ItemCompanyDetailDAO extends AbstractDAO<SysItemCompany> {

    @PersistenceContext(unitName = Persistence.MARKET)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemCompanyDetailDAO() {
        super(SysItemCompany.class);
    }

    public List<SysItemCompany> findSysItemCompanyList() {
        Query q = em.createQuery("select o from SysItemCompany o where o.companyStatus ='Y' order by  o.itemCompanyId asc");
        return q.getResultList();
    }
    
    public List<SysItemCompany> findSysItemCompanyListByCompanyId(Integer companyId) {
        Query q = em.createQuery("select o from SysItemCompany o where o.companyStatus ='Y' and o.companyId.companyId=:companyId order by  o.itemCompanyId asc");
        q.setParameter("companyId", companyId);
        
        return q.getResultList();
    }
    
    public SysItemCompany findSysItemCompanyByById(SysItemCompany sysItemCompany) {
        Query q = em.createQuery("select o from SysItemCompany o where o.itemCompanyId =:itemCompanyId order by  o.itemCompanyId asc ");
        q.setParameter("itemCompanyId", sysItemCompany.getItemCompanyId());

        return (SysItemCompany) q.getSingleResult();
    }
    
    public List<SysItemCompany> findSysItemCompanyByCriteria(SysCompany sysCompany,SysItem sysItem, String status) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT u FROM SysItemCompany u WHERE 1=1");

        if (null != sysCompany) {
            sb.append("AND u.companyId.companyId = :companyId ");
        }
        if (null != sysItem) {
            sb.append("AND u.itemId.itemId =:itemId ");
        }
        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.companyStatus = :status ");
        }

        sb.append("ORDER BY u.createdDt DESC ");

        Query q = em.createQuery(sb.toString());
        if (null != sysCompany) {
            q.setParameter("companyId", sysCompany.getCompanyId());
        }
        if (null != sysItem) {
            q.setParameter("itemId", sysItem.getItemId());
        }
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }

        return q.getResultList();
    }
    
    public List<SysItemCompany> findSysItemCompanyByCriteria(SysCompany sysCompany,String itemName, String status, int[] range) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT u FROM SysItemCompany u WHERE 1=1");

        if (null != sysCompany) {
            sb.append("AND u.companyId.companyId = :companyId ");
        }
        if (StringUtils.isNotBlank(itemName)) {
            sb.append("AND lower(u.itemId.itemTh) LIKE :itemName ");
        }
        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.companyStatus = :status ");
        }

        sb.append("ORDER BY u.createdDt DESC ");

        Query q = em.createQuery(sb.toString());
        if (null != sysCompany) {
            q.setParameter("companyId", sysCompany.getCompanyId());
        }
        if (StringUtils.isNotBlank(itemName)) {
            q.setParameter("itemName", "%" + StringUtils.lowerCase(itemName) + "%");
        }
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }
        

        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countSysItemCompanyByCriteria(SysCompany sysCompany,String itemName, String status) {
        StringBuilder sb = new StringBuilder("SELECT count(u) FROM SysItemCompany u WHERE 1=1 ");

        if (null != sysCompany) {
            sb.append("AND u.companyId.companyId = :companyId ");
        }
        if (StringUtils.isNotBlank(itemName)) {
            sb.append("AND lower(u.itemId.itemTh) LIKE :itemName ");
        }
        if (StringUtils.isNotBlank(status)) {
            sb.append("AND u.companyStatus = :status ");
        }

        Query q = em.createQuery(sb.toString());
        if (null != sysCompany) {
            q.setParameter("companyId", sysCompany.getCompanyId());
        }
        if (StringUtils.isNotBlank(itemName)) {
            q.setParameter("itemName", "%" + StringUtils.lowerCase(itemName) + "%");
        }
        if (StringUtils.isNotBlank(status)) {
            q.setParameter("status", status);
        }

        return ((Number) q.getSingleResult()).intValue();
    }
}
