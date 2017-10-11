package com.fresh.market.ejb.bo;

import com.fresh.market.core.ejb.entity.SysOrganization;
import com.fresh.market.ejb.dao.SysOrganizationDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn j.
 */
@Stateless(name = "finework.OrganizationBO")
public class OrganizationBO {

    @EJB
    private SysOrganizationDAO sysOrganizationDAO;

   
    public SysOrganization findSysOrganizationByStatus(String status) throws Exception {
       return sysOrganizationDAO.findSysOrganizationByStatus(status);
    }
    
  
    
    
}
