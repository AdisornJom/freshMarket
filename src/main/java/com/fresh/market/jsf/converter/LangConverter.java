package com.fresh.market.jsf.converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.fresh.market.core.ejb.entity.Language;
import com.fresh.market.core.util.JsfUtil;
import com.fresh.market.jsf.common.ComboController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Adisorn.jo
 */
@FacesConverter("langConverter")
public class LangConverter implements Converter {

    private final List<Language> langs;

    public LangConverter() {
        ComboController combo = (ComboController) JsfUtil.getManagedBeanValue(ComboController.CONTROLLER_NAME);
        langs = combo.getLangs();
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        for (Language l : langs) {
            if (l.getLang().equals(value)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        Language f = (Language) object;
        return f.getLang();
    }

}
