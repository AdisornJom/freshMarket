package com.fresh.market.jsf.common;

import com.fresh.market.core.util.LoadConfig;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Aekasit
 */
@SessionScoped
@Named(CommonController.CONTROLLER_NAME)
public class CommonController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
    public static final String CONTROLLER_NAME = "commonController";

    @Inject
    private UserInfoController userInfoController;

    private static Map<String, String> CONFIG;
    private String IMAGES_URL;
    private String PLATFORM_ID;
    //private String baseUrl;

    @PostConstruct
    public void init() {
        CONFIG = LoadConfig.loadFileDefault();
        IMAGES_URL = CONFIG.get(LoadConfig._IMAGES_URL);
        PLATFORM_ID = CONFIG.get(LoadConfig.PLATFORM_ID).toUpperCase();
//        try {
//            baseUrl = getBaseURL(FacesContext.getCurrentInstance());
//        } catch (MalformedURLException ex) {
//            LOG.error(ex);
//        }
    }

    /*
    private String getBaseURL(final FacesContext facesContext) throws MalformedURLException {
        return getBaseURL(facesContext.getExternalContext());
    }

    private String getBaseURL(final ExternalContext externalContext) throws MalformedURLException {
        return getBaseURL((HttpServletRequest) externalContext.getRequest());
    }

    private String getBaseURL(final HttpServletRequest request) throws MalformedURLException {
        return new URL(request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                request.getContextPath()).toString();
    }
*/

    public String getMp3() throws Exception {
        //return baseUrl + "/resources/sound/2013horn_niw17bsn.mp3";
        //return baseUrl + "/resources/sound/" + userInfoController.getAdminUser().getSound();
        //return "resources/sound/" + userInfoController.getAdminUser().getSound();
        return userInfoController.getAdminUser().getSound() == null ? "" : "resources/sound/" + userInfoController.getAdminUser().getSound();
    }

    public static Map<String, String> getCONFIG() {
        return CONFIG;
    }

    public static void setCONFIG(Map<String, String> CONFIG) {
        CommonController.CONFIG = CONFIG;
    }

    public String getIMAGES_URL() {
        return IMAGES_URL;
    }

    public void setIMAGES_URL(String IMAGES_URL) {
        this.IMAGES_URL = IMAGES_URL;
    }

    public String getPLATFORM_ID() {
        return PLATFORM_ID;
}

    public void setPLATFORM_ID(String PLATFORM_ID) {
        this.PLATFORM_ID = PLATFORM_ID;
    }
    
    
}
