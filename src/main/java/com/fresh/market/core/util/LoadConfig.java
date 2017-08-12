/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author Aekasit
 */
public class LoadConfig {

    private static final Logger LOG = Logger.getLogger(LoadConfig.class);
    private static Map<String, String> FILE_CONFIG;
    private static Map<String, String> DB_CONFIG;

    public static final String MA_STATUS = "MA_STATUS";
    public static final String MA_DESC = "MA_DESC";
    public static final String MA_START = "MA_START";
    public static final String MA_END = "MA_END";

    public static final String SETTING_TITLE = "SET_TITLE";
    public static final String SETTING_EMAIL = "SET_EMAIL";
    public static final String SETTING_TEL = "SET_TEL";
    public static final String SETTING_TEL1 = "SET_TEL1";
    public static final String SETTING_TEL2 = "SET_TEL2";
    public static final String SETTING_TEL3 = "SET_TEL3";
    public static final String SETTING_TEL4 = "SET_TEL4";
    public static final String SETTING_LINE_ID = "SET_LINEID";

    /*--------------------------------------------------------------*/
    public static final String RESEND_EMAIL = "platform.domain";

    public static final String _IMAGES_URL = "images.url";
    public static final String _SMTP_HOST = "smtp.host";
    public static final String _SMTP_PORT = "smtp.port";
    public static final String _SMTP_USER = "smtp.user";
    public static final String _SMTP_PASS = "smtp.pass";
    public static final String _FTP_HOST = "ftp.host";
    public static final String _FTP_USER = "ftp.user";
    public static final String _FTP_PASS = "ftp.pass";
    public static final String _FTP_PORT = "ftp.port";
    public static final String FILE_DEFAULT = "config.properties";

    public static final String LOTTO_URL = "lotto.url";
    public static final String LOTTO_AGENT = "lotto.agent";
    public static final String LOTTO_TOKEN = "lotto.token";
    public static final String LOTTO_HOST = "lotto.host";
    public static final String LOTTO_MINB = "lotto.minb";
    public static final String LOTTO_MAXB = "lotto.maxb";
    public static final String LOTTO_COMM = "lotto.comm";
    public static final String LOTTO_PAY = "lotto.pay";

    public static final String RCGAME_HOST = "rcgame.host";
    public static final String RCGAME_HASH = "rcgame.hash";
    public static final String RCGAME_UID = "rcgame.uid";
    public static final String RCGAME_KEY = "rcgame.key";
    public static final String RCGAME_URL = "rcgame.url";

    public static final String STAR_URL = "star.url";
    public static final String STAR_AGENT = "star.agent";
    public static final String STAR_SECRET = "star.secret";

    public static final String TOP_URL = "top.url";
    public static final String TOP_AGENT = "top.agent";
    public static final String TOP_SECRET = "top.secret";
    public static final String TOP_HOST = "top.host";
    public static final String TOP_PATH = "top.path";

    public static final String BET168_URL = "bet168.url";
    public static final String BET168_AGENT = "bet168.agent";
    public static final String BET168_SECRET = "bet168.secret";
    public static final String BET168_HOST = "bet168.host";
    public static final String BET168_PATH = "bet168.path";

    public static final String BET125_URL = "bet125.url";
    public static final String BET125_AGENT = "bet125.agent";
    public static final String BET125_SECRET = "bet125.secret";
    public static final String BET125_HOST = "bet125.host";
    public static final String BET125_PATH = "bet125.path";

    public static final String VEN_HOST = "ven.host";
    public static final String VEN_URL = "ven.url";
    public static final String VEN_MERCHANT = "ven.merchant";
    public static final String VEN_HASH = "ven.hash";

    public static final String WYNN_URL = "wynn.url";
    public static final String WYNN_HOST = "wynn.host";
    public static final String WYNN_CONSUMER_ID = "wynn.consumer_id";
    public static final String WYNN_CONSUMER_PASSWORD = "wynn.consumer_password";
    public static final String WYNN_AGENT = "wynn.agent";
    
    public static final String N2LIVE_WEB_URL="n2live.web.url";
    public static final String N2LIVE_MOBILE_URL="n2live.mobile.url";
    public static final String N2LIVE_MERCHANT="n2live.merchant_code";
    public static final String N2LIVE_MERCHANT_PASSWORD="n2live.merchant_password";

    public static final String COCK_URL = "cock.url";
    public static final String COCK_HOST = "cock.host";
    public static final String COCK_API_KEY = "cock.api_key";
    public static final String COCK_AGENT_CODE = "cock.agent_code";

    public static final String IDNPOKER_URL = "idnpoker.url";
    public static final String IDNPOKER_HOST = "idnpoker.host";
    public static final String IDNPOKER_ID = "idnpoker.id";
    public static final String IDNPOKER_TOKEN = "idnpoker.token";

    public static final String LIVESPORT_URL = "livesport.url";

    public static final String LOG_IP = "log.ip";
    public static final String LOG_PORT = "log.port";
    public static final String LOG_DB = "log.db";
    public static final String LOG_USER = "log.user";
    public static final String LOG_PASS = "log.pass";
    public static final String LOG_ENABLE = "log.enable";

    public static final String GGAMING_URL = "ggaming.url";
    public static final String GGAMING_CAGENT = "ggaming.cagent";
    public static final String GGAMING_DES_KEY = "ggaming.des_key";
    public static final String GGAMING_MD5_KEY = "ggaming.md5_key";

    public static final String ALLBET_URL = "allbet.url";
    public static final String ALLBET_AGENT = "allbet.agent";
    public static final String ALLBET_PREFIX = "allbet.prefix";
    public static final String ALLBET_PROPERTY_ID = "allbet.property_id";
    public static final String ALLBET_DES_KEY = "allbet.des_key";
    public static final String ALLBET_MD5_KEY = "allbet.md5_key";
    public static final String ALLBET_HOST = "allbet.host";
    public static final String ALLBET_HOST_H5 = "allbet.host.h5";

    public static final String SPADEGAMING_URL_API = "spadegaming.url_api";
    public static final String SPADEGAMING_URL_PLAYGAME = "spadegaming.url_playgame";
    public static final String SPADEGAMING_MERCHANT_CODE = "spadegaming.merchant_code";

    public static final String IS_DG_WEB = "is.dg.web";
    public static final String KICK_URL = "platform.domain";
    public static final String LOGINBY = "loginBy";
    public static final String PLATFORM_ID = "platform.id";

    public static final String TEMPLATE_EMAIL_TH = "template.email.th";
    public static final String TEMPLATE_EMAIL_EN = "template.email.en";

    public static Map<String, String> loadFileDefault() {
        try {

            if (FILE_CONFIG == null) {
                FILE_CONFIG = new HashMap<>();
                Properties p = new Properties();
                p.load(LoadConfig.class.getClassLoader().getResourceAsStream(FILE_DEFAULT));
                Set keySet = p.keySet();
                for (Object key : keySet) {
                    FILE_CONFIG.put((String) key, (String) p.get(key));
                }
            }

            return FILE_CONFIG;
        } catch (IOException ex) {
            LOG.error(ex);
            return null;
        }
    }

    public static Map<String, String> loadFile(String path) {
        try {
            if (FILE_CONFIG == null) {
                FILE_CONFIG = new HashMap<>();
                Properties p = new Properties();
                p.load(LoadConfig.class.getClassLoader().getResourceAsStream(path));
                Set keySet = p.keySet();
                for (Object key : keySet) {
                    FILE_CONFIG.put((String) key, (String) p.get(key));
                }
            }
            return FILE_CONFIG;
        } catch (IOException ex) {
            LOG.error(ex);
            return null;
        }
    }

    public static void main(String[] args) {
        LoadConfig config = new LoadConfig();
        LoadConfig.loadFile("top-sport.properties");
    }
}
