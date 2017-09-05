/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fresh.market.jsf.converter;

import com.fresh.market.core.ejb.entity.SysItem;
import com.fresh.market.core.ejb.entity.SysItemCompany;
import com.fresh.market.ejb.facade.WareHouseFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import org.apache.log4j.Logger;
 
 
@ManagedBean(name = ItemCompanyConverter.CONTROLLER_NAME)
@RequestScoped
public class ItemCompanyConverter implements Converter {
    private static final Logger LOG = Logger.getLogger(ItemCompanyConverter.class);
    public static final String CONTROLLER_NAME = "itemCompanyConverter";
    
    @Inject
    private WareHouseFacade facade;
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((SysItemCompany) object).getItemCompanyId());
        }
        else {
            return null;
        }
    }   

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        SysItemCompany sysItemCompany = null;
        try {
            if(value != null &&  value.trim().length() > 0  && !"null".equals(value)) {
                 SysItemCompany criteria=new SysItemCompany();
                 criteria.setItemCompanyId(Integer.parseInt(value));
                 sysItemCompany=facade.findSysItemCompanyByById(criteria);
            }
         } catch (Exception ex) {
              LOG.error(ex);
         }
        return sysItemCompany;
    }
}     
