package com.fresh.market.ejb.facade;

import com.fresh.market.core.ejb.entity.SysOrganization;
import com.fresh.market.ejb.bo.OrganizationBO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn J.
 */
@Stateless
public class OrganizationFacade {

    @EJB
    private OrganizationBO organizationBO;

    
    public SysOrganization findSysOrganizationByStatus(String status) throws Exception {
       return organizationBO.findSysOrganizationByStatus(status);
    }
   

}
