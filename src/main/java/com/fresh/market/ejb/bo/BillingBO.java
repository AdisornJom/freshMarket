package com.fresh.market.ejb.bo;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysBilling;
import com.fresh.market.ejb.dao.SysBillingDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless(name = "market.Billing")
public class BillingBO {

    @EJB
    private SysBillingDAO sysBillingDAO;
    
    
    public List<SysBilling> findSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate,int[] range) throws Exception {
        return sysBillingDAO.findSysBillingByCriteria(sysCompany,documentno, status,startDate,toDate,range);
    }

    public int countSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) throws Exception {
        return sysBillingDAO.countSysBillingByCriteria(sysCompany,documentno, status,startDate,toDate);
    }
    
    public void createSysBilling(SysBilling sysItemCompany) throws Exception {
        sysBillingDAO.create(sysItemCompany);
    }

    public void editSysBilling(SysBilling sysItemCompany) throws Exception {
        sysBillingDAO.edit(sysItemCompany);
    }

    public void deleteSysBilling(SysBilling sysItemCompany) throws Exception {
        sysBillingDAO.remove(sysItemCompany);
    }
    
    public void deleteBillingIdOnDetail(Integer billingId) throws Exception {
        sysBillingDAO.deleteBillingIdOnDetail(billingId);
    }

}
