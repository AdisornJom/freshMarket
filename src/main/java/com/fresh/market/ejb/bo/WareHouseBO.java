package com.fresh.market.ejb.bo;

import com.fresh.market.core.ejb.entity.SysItem;
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

}
