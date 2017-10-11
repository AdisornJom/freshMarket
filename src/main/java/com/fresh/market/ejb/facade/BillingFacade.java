package com.fresh.market.ejb.facade;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysBilling;
import com.fresh.market.core.ejb.entity.SysItemTO;
import com.fresh.market.ejb.bo.BillingBO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class BillingFacade implements Serializable {

    @EJB
    private BillingBO billingBO;
    
    public SysBilling  findByPK(Integer id){
       return  billingBO.findByPK(id);
    }
     
    public List<SysBilling> findSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) throws Exception {
        return billingBO.findSysBillingByCriteria(sysCompany,documentno, status,startDate,toDate);
    }
    
    public List<SysItemTO> detailBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) throws Exception {
        return billingBO.detailBillingByCriteria(sysCompany,documentno, status,startDate,toDate);
    }
    
    public int volumnDetailBillingByCriteria(Integer billing_id,Integer item_id, Integer status, Date startDate, Date toDate) throws Exception {
        return billingBO.volumnDetailBillingByCriteria(billing_id,item_id, status,startDate,toDate);
    }
    
    public List<SysBilling> findSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate, int[] range) throws Exception {
        return billingBO.findSysBillingByCriteria(sysCompany,documentno, status,startDate,toDate,range);
    }

    public int countSysBillingByCriteria(SysCompany sysCompany,String documentno, Integer status, Date startDate, Date toDate) throws Exception {
        return billingBO.countSysBillingByCriteria(sysCompany,documentno, status,startDate,toDate);
    }
    
    public void createSysBilling(SysBilling sysBilling) throws Exception {
        billingBO.createSysBilling(sysBilling);
    }

    public void editSysBilling(SysBilling sysBilling) throws Exception {
        billingBO.editSysBilling(sysBilling);
    }

    public void deleteSysBilling(SysBilling sysBilling) throws Exception {
        billingBO.deleteSysBilling(sysBilling);
    }
    
     public void deleteBillingIdOnDetail(Integer billingId) throws Exception {
        billingBO.deleteBillingIdOnDetail(billingId);
    }
     
     
}
