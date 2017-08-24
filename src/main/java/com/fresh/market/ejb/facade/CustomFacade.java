package com.fresh.market.ejb.facade;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.ejb.bo.CustomBO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class CustomFacade implements Serializable {

    @EJB
    private CustomBO customBO;

     public List<SysCompany> findSysCompanyList() throws Exception {
        return customBO.findSysCompanyList();
    }
     
    public SysCompany findSysCompanyById(SysCompany sysCompany) throws Exception {
        return customBO.findSysCompanyById(sysCompany);
    }
     
    public List<SysCompany> findSysCompanyByCriteria(String companyNameTh, String status, int[] range) throws Exception {
        return customBO.findSysCompanyByCriteria(companyNameTh, status, range);
    }

    public int countSysCompanyByCriteria(String companyNameTh, String status) throws Exception {
        return customBO.countSysCompanyByCriteria(companyNameTh, status);
    }
    
    public void createSysCompany(SysCompany sysCompany) throws Exception {
        customBO.createSysCompany(sysCompany);
    }

    public void editSysCompany(SysCompany sysCompany) throws Exception {
        customBO.editSysCompany(sysCompany);
    }

    public void deleteSysCompany(SysCompany sysCompany) throws Exception {
        customBO.deleteSysCompany(sysCompany);
    }
}
