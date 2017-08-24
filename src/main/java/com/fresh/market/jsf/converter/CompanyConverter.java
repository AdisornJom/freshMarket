/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fresh.market.jsf.converter;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.ejb.facade.CustomFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import org.apache.log4j.Logger;
 
 
@ManagedBean(name = CompanyConverter.CONTROLLER_NAME)
@RequestScoped
public class CompanyConverter implements Converter {
    private static final Logger LOG = Logger.getLogger(CompanyConverter.class);
    public static final String CONTROLLER_NAME = "companyConverter";
    
    @Inject
    private CustomFacade facade;
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((SysCompany) object).getCompanyId());
        }
        else {
            return null;
        }
    }   

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        SysCompany sysCompany = null;
        try {
            if(value != null &&  value.trim().length() > 0  && !"null".equals(value)) {
                 SysCompany criteria=new SysCompany();
                 criteria.setCompanyId(Integer.parseInt(value));
                 sysCompany=facade.findSysCompanyById(criteria);
            }
         } catch (Exception ex) {
              LOG.error(ex);
         }
        return sysCompany;
    }
}     
