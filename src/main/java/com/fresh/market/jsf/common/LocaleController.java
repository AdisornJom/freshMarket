package com.fresh.market.jsf.common;

import com.fresh.market.core.ejb.entity.Language;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MR Aekasit Sengnualnim (Aek) System Analyst
 *
 * Progress Software Co.,Ltd Fl. 6-7 306 Supha Road, Phomphlab
 * Phomphlabsattupai, Bangkok 10100 Thailand Tel :+66 (0) 2867 5020 Mobile : +66
 * 91004 1009 Skype : s.aekasit MSN : aekasit.se@gmail.com Email :
 * aekasit.s@kasikornbank.com Website http://www.kasikornbank.com
 *
 * @create 01-10-2014 4:12:30 PM
 */
@SessionScoped
@Named(LocaleController.CONTROLLER_NAME)
public class LocaleController extends BaseController implements  Serializable{
    private static final Logger LOG = LoggerFactory.getLogger(LocaleController.class);
    public static final String CONTROLLER_NAME = "localeController";
    private Locale locale;
    private Language lang;
    private List<Language> langs;

    @Inject
    private ComboController combo;
//    @Inject
//    private BindingController bindingController;

    @PostConstruct
    @Override
    public void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        //bindingController.getCalendar().setLocale(locale);
        langs = combo.getLangs();

        for (Language l : langs) {
            if (l.getLang().equals(locale.getLanguage())) {
                lang = l;
            }
        }
    }

    public String getLangTeamLeague() {
        if (StringUtils.equals(locale.getLanguage(), "en")) {
            return "en-US";
        } else if (StringUtils.equals(locale.getLanguage(), "th")) {
            return "th-TH";
        } else if (StringUtils.equals(locale.getLanguage(), "vi")) {
            return "vi-VN";
        } else {
            return "en-US";
        }
    }

    public void changeLocale() {
        String l = lang.getLocale();
        locale = new Locale(l.substring(0, 2), l.substring(3, l.length()));
        //bindingController.getCalendar().setLocale(locale);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return locale.toString();
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public List<Language> getLangs() {
        return langs;
    }

    public void setLangs(List<Language> langs) {
        this.langs = langs;
    }

}
