package com.fresh.market.ejb.facade;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysItemCompany;
import com.fresh.market.ejb.bo.WareHouseBO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless
public class WareHouseFacade implements Serializable {

    @EJB
    private WareHouseBO wareHouseBO;

    public List<SysItem> findSysItemByCriteria(String itemName, String status, int[] range) throws Exception {
        return wareHouseBO.findSysItemByCriteria(itemName, status, range);
    }

    public int countSysItemByCriteria(String itemName, String status) throws Exception {
        return wareHouseBO.countSysItemByCriteria(itemName, status);
    }
    
    public void createSysItem(SysItem sysItem) throws Exception {
        wareHouseBO.createSysItem(sysItem);
    }

    public void editSysItem(SysItem sysItem) throws Exception {
        wareHouseBO.editSysItem(sysItem);
    }

    public void deleteSysItem(SysItem sysItem) throws Exception {
        wareHouseBO.deleteSysItem(sysItem);
    }
    
    ///SysItemCompany
    public List<SysItemCompany> findSysItemCompanyByCriteria(SysCompany sysCompany,String itemName, String status, int[] range) throws Exception {
        return wareHouseBO.findSysItemCompanyByCriteria(sysCompany,itemName, status, range);
    }

    public int countSysItemCompanyByCriteria(SysCompany sysCompany,String itemName, String status) throws Exception {
        return wareHouseBO.countSysItemCompanyByCriteria(sysCompany,itemName, status);
    }
    
    public void createSysItemCompany(SysItemCompany sysItemCompany) throws Exception {
        wareHouseBO.createSysItemCompany(sysItemCompany);
    }

    public void editSysItemCompany(SysItemCompany sysItemCompany) throws Exception {
        wareHouseBO.editSysItemCompany(sysItemCompany);
    }

    public void deleteSysItemCompany(SysItemCompany sysItemCompany) throws Exception {
        wareHouseBO.deleteSysItemCompany(sysItemCompany);
    }
}
