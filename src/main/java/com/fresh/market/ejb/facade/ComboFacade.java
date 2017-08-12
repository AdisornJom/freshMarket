package com.fresh.market.ejb.facade;

import com.fresh.market.core.ejb.entity.AdminUserRole;
import com.fresh.market.core.ejb.entity.Language;
import com.fresh.market.ejb.bo.CommonBO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Aekasit
 */
@Stateless
public class ComboFacade {

    @EJB
    private CommonBO commonBO;

    public List<AdminUserRole> findAdminUserRole() throws Exception {
        return commonBO.findAdminUserRoleList();
    }

    public List<Language> findLanguageList() throws Exception {
        return commonBO.findLanguageList();
    }

}
