/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author s.Adisorn.jo
 */
public class DateTimeUtil {
    
    public final static String pattern = "dd-MMM-yyyy";
    public final static String PATTERN_DB = "yyyy-MM-dd";
    public final static String PATTERN_DT_DB = "yyyy-MM-dd HH:mm:ss";
    public final static String SELECT_FROM_TIME = " 00:00:00";
    public final static String SELECT_TO_TIME = " 23:59:59";

    public static String dateToString(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "dd-MM-yyyy";
        }
        return date != null ? (new SimpleDateFormat(pattern, Locale.US)).format(date) : null;
    }

    public static Date stringToDate(String date, String pattern) {
        try {
            if (StringUtils.isBlank(pattern)) {
                pattern = "dd-MM-yyyy";
            }
            return date != null ? (new SimpleDateFormat(pattern, Locale.US)).parse(date.trim()) : null;
        } catch (ParseException ex) {
        }
        return null;
    }

    public static String convertLongToDateStr(long l) {
        DateTime dt = new DateTime(l, DateTimeZone.UTC);
        String h = dt.getHourOfDay() == 0 ? "" : String.valueOf(dt.getHourOfDay() + "h ");
        String m = dt.getMinuteOfHour() == 0 ? "" : String.valueOf(dt.getMinuteOfHour() + "m ");
        String s = dt.getSecondOfMinute() == 0 ? "" : String.valueOf(dt.getSecondOfMinute() + "s ");
        return h + m + s + dt.getMillisOfSecond() + "ms";
    }

    public static String cvtDateForShow(Date sDateValue, String pattern, Locale locale) {
        if (sDateValue == null) {
            return "";
        }
        //Locale.setDefault(Locale.US);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        String strDateLocal = formatter.format(sDateValue);
        return strDateLocal;
    }
    
    public static Date getSystemDate() {
        Calendar c = new GregorianCalendar(Locale.US);
        return c.getTime();
    }
    
    public static Date currentDate() {
        Calendar c = new GregorianCalendar(new Locale("th", "TH"));
        c.setTimeInMillis(System.currentTimeMillis());
        return c.getTime();
    }
    
    public static String strCurrentDateOnPatternDate(String patternDate) {
        Calendar c = new GregorianCalendar(Locale.US);
        c.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(patternDate, Locale.US);
        return sdf.format(c.getTime());
    }

    public static Date getDateLong(String l) {
        if (l == null) {
            return null;
        }
        return new Date(Long.parseLong(l));
    }

    public static Date getDateLong(long l) {
        if (l == 0 || l < 0) {
            return null;
        }

        return new Date(l);
    }

    public static void main(String[] args) {
        
//        System.out.println("1." + getDateLong("1442854203"));
        
        long l = 1442854203 * 1000;
        
    }
}
