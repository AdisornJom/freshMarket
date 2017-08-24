package com.fresh.market.ejb.bo;


import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.ejb.dao.SysCompanyDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless(name = "market.Custom")
public class CustomBO {

    @EJB
    private SysCompanyDAO sysCompanyDAO;

        
    public List<SysCompany> findSysCompanyList() throws Exception {
        return sysCompanyDAO.findSysCompanyList();
    }
    
    public SysCompany findSysCompanyById(SysCompany sysCompany) throws Exception {
        return sysCompanyDAO.findSysCompanyById(sysCompany);
    }
     
    public List<SysCompany> findSysCompanyByCriteria(String companyNameTh, String status, int[] range) throws Exception {
        return sysCompanyDAO.findSysCompanyByCriteria(companyNameTh, status, range);
    }

    public int countSysCompanyByCriteria(String companyNameTh, String status) throws Exception {
        return sysCompanyDAO.countSysCompanyByCriteria(companyNameTh, status);
    }
    
    public void createSysCompany(SysCompany sysCompany) throws Exception {
        sysCompany.setStatus("Y");
        sysCompanyDAO.create(sysCompany);
    }

    public void editSysCompany(SysCompany sysCompany) throws Exception {
        sysCompanyDAO.edit(sysCompany);
    }

    public void deleteSysCompany(SysCompany sysCompany) throws Exception {
        sysCompany.setStatus("N");
        sysCompanyDAO.edit(sysCompany);
    }

}
