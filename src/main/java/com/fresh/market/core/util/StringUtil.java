package com.fresh.market.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Administrator
 */
public class StringUtil {
//    String pattern2 = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}";

    /**
     * Validate the username string with the following business logic: 1) User
     * name must be between 6 to 15 characters. 2) User name can have lowercase
     * and uppercase characters. 3) User name can be alpha-numeric. 4) No
     * special character allowed.
     */
    private static final String USERNAME_FRONT = "[a-z0-9]{6,14}";
    private static final String USERNAME_BACK = "[.a-z0-9]{3,14}";
    private static final String ACCOUNT_NBR = "[0-9]{5,20}";

    /*  for user */
    //private static final String PASSWORD1 = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}";
    private static final String PASSWORD1 = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    /*  for admin */
    private static final String PASSWORD2 = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

    private static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String IPADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    private static final String VALIDATE_URL="((https?):((//)|(\\\\\\\\))+[\\\\w\\\\d:#@%/;$()~_?\\\\+-=\\\\\\\\\\\\.&]*)";

    /*
     The password supplied must be meet the minimum complexity requirements.  
     Please enter password that meets all of the following criteria: 

     1.is at least 8 characters
     2. has not been used in the previous 5 passwords (DB)
     3. does not contain your account or full name (DB)
     4. does not contain space
     5. must contains three character groups
     6. English uppercase charecters (A through Z)
     7. English lowercase charecters (a through z)
     8. Numerals (0 through 9)
     */
    public static boolean validateEmail(String email) {
        return email.matches(EMAIL);
    }

    public static boolean validateBankAccountNbr(String acc) {
        acc = StringUtils.trimToEmpty(acc).replaceAll("-", "");
        return acc.matches(ACCOUNT_NBR);
    }

    public static boolean validatePasswd1(String passwd) {
        return passwd.matches(PASSWORD1);
    }

    public static boolean validatePasswd2(String passwd) {
        return passwd.matches(PASSWORD2);
    }

    public static boolean validateIPAddress(final String ip) {
//	  matcher = pattern.matcher(ip);
//	  return matcher.matches();	
        return ip.matches(IPADDRESS_PATTERN);
    }

//    public static Boolean validatePasswd(User member, String passwd) throws Exception {
//
//        String passwdEncypt = MD5Generator.md5(passwd);
//        String txt = member.getUsername();
//        String username = MD5Generator.md5(txt);
//        String email = MD5Generator.md5(member.getEmail());
//
//        if (passwdEncypt.equals(username)) {
//            return false;
//        } else if (passwdEncypt.equals(email)) {
//            return false;
//        }
//
//        return true;
//    }
    public static boolean validateUserName(String username) {
        return username.matches(USERNAME_FRONT);
    }

    public static boolean validateUserName_back(String username) {
        if (username.matches(USERNAME_BACK)) {
            if (StringUtils.countMatches(username, ".") > 1) {
                return false;
            } else if (username.indexOf(".") != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private StringUtil() {
    }

    public static String replaceStringNull(String field) {
        if (field == null) {
            return null;
        } else if (field.equals("(null)")) {
            return null;
        }
        return field;
    }

    public static String numberFormat(long id, String patt) {
        NumberFormat formatter = new DecimalFormat(patt);
        return formatter.format(id);
    }

    public static String numberFormat(BigDecimal value, String patt) {
        DecimalFormat formatter = new DecimalFormat(patt);
        return formatter.format(value);
    }

    public static boolean haveData(String param) {
        if (null == param || param.trim().length() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public static String getMyRealIp(String ip) {
        if (null != ip) {
            ip = ip.trim();
            String[] ips = ip.split(",");
            String finalIp = ips[0];
            return finalIp;
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(numberFormat(new BigDecimal(1000.54).multiply(new BigDecimal(100)), "###000"));
    }

    public static String bankNumberFormat(String number) {
        if (StringUtils.isBlank(number)) {
            return number;
        }
//        ddd-d-ddddd-d
        if (StringUtils.length(StringUtils.replace(number, "-", "")) == 10) {
            number = StringUtils.replace(number, "-", "");
            return number.substring(0, 3) + "-" + number.substring(3, 4) + "-" + number.substring(4, 9) + "-" + number.substring(9, StringUtils.length(number));
        }
        return number;
    }
    
     public static boolean isUrl(String str) {
        Pattern urlPattern = Pattern.compile(VALIDATE_URL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = urlPattern.matcher(str);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
     
     public static String customFormat(String pattern, int value ) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      return  myFormatter.format(value);
   }
}
