package com.fresh.market.core.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public class MessageBundleLoader {

    public static final String DEFAULT_MESSAGE_PATH = "messages";
    public static final String APP_MESSAGE_PATH = "applications";

    private MessageBundleLoader() {
    }

    public static String getMessage(String key) {
        if (key == null) {
            return null;
        }
        
        try {

            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle res = ResourceBundle.getBundle(DEFAULT_MESSAGE_PATH, locale);
            if (res == null) {
                res = ResourceBundle.getBundle(DEFAULT_MESSAGE_PATH, locale);
            }
            
            return res.getString(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMessage(String resourcePath, String key) {
        if (key == null) {
            return null;
        }
        try {
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle res = ResourceBundle.getBundle(resourcePath, locale);
            return res.getString(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMessageFormat(String key, Object... args) {
        try {
            if (key == null) {
                return null;
            }
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle messages = ResourceBundle.getBundle(DEFAULT_MESSAGE_PATH, locale);
            return MessageFormat.format(messages.getString(key), args);
        } catch (Exception e) {
            return "";
        }
    }
}
