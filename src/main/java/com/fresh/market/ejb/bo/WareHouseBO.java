package com.fresh.market.ejb.bo;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysItemCompany;
import com.fresh.market.ejb.dao.ItemCompanyDetailDAO;
import com.fresh.market.ejb.dao.ItemDetailDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn.jo
 */
@Stateless(name = "market.WareHouse")
public class WareHouseBO {

    @EJB
    private ItemDetailDAO itemDetailDAO;
    @EJB
    private ItemCompanyDetailDAO itemCompanyDetailDAO;

    public List<SysItem> findSysItemByCriteria(String itemName, String status, int[] range) throws Exception {
        return itemDetailDAO.findSysItemByCriteria(itemName, status, range);
    }

    public int countSysItemByCriteria(String itemName, String status) throws Exception {
        return itemDetailDAO.countSysItemByCriteria(itemName, status);
    }
    
    public void createSysItem(SysItem sysItem) throws Exception {
        sysItem.setItemStatus("Y");
        itemDetailDAO.create(sysItem);
    }

    public void editSysItem(SysItem sysItem) throws Exception {
        itemDetailDAO.edit(sysItem);
    }

    public void deleteSysItem(SysItem sysItem) throws Exception {
        sysItem.setItemStatus("N");
        itemDetailDAO.edit(sysItem);
    }
    
    ///SysItemCompany
    public List<SysItemCompany> findSysItemCompanyByCriteria(SysCompany sysCompany,String itemName, String status, int[] range) throws Exception {
        return itemCompanyDetailDAO.findSysItemCompanyByCriteria(sysCompany,itemName, status, range);
    }

    public int countSysItemCompanyByCriteria(SysCompany sysCompany,String itemName, String status) throws Exception {
        return itemCompanyDetailDAO.countSysItemCompanyByCriteria(sysCompany,itemName, status);
    }
    
    public void createSysItemCompany(SysItemCompany sysItemCompany) throws Exception {
        sysItemCompany.setCompanyStatus("Y");
        itemCompanyDetailDAO.create(sysItemCompany);
    }

    public void editSysItemCompany(SysItemCompany sysItemCompany) throws Exception {
        itemCompanyDetailDAO.edit(sysItemCompany);
    }

    public void deleteSysItemCompany(SysItemCompany sysItemCompany) throws Exception {
        sysItemCompany.setCompanyStatus("N");
        itemCompanyDetailDAO.edit(sysItemCompany);
    }

}
