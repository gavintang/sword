/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gavin
 */
final public class DateFormatUtil {
    
    private static String BLANK_STRING = "N/A";
    
    private static DateFormat SHORT_DAY = new SimpleDateFormat("MMM d");
    private static DateFormat DAY = new SimpleDateFormat("yyyy-MM-dd");
    
    private static DateFormat DAYTIME = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
    
    private static DateFormat SHORT_TIME = new SimpleDateFormat("hh:mm a");
    private static DateFormat TIME = new SimpleDateFormat("hh:mm:ss a");
    
    /**
     * @param date to be formatted
     * @return String value of "MMM d", i.e. Jul 1
     */
    public static String toShortDayFormat(Date date) {
        if(date == null) {
            return BLANK_STRING;
        } else {
            return SHORT_DAY.format(date);
        }
    }
    
    /**
     * @param date to be formatted
     * @return String value of "yyyy-MM-dd", i.e. 2014-04-12
     */
    public static String toDayFormat(Date date) {
        if(date == null) {
            return BLANK_STRING;
        } else {
            return DAY.format(date);
        }
    }
    
    /**
     * @param date to be formatted
     * @return String value of "yyyy-MM-dd hh:mm a", i.e. 2014-04-12 12:40 pm
     */
    public static String toDaytimeFormat(Date date) {
        if(date == null) {
            return BLANK_STRING;
        } else {
            return DAYTIME.format(date);
        }
    }
    
    /**
     * @param date to be formatted
     * @return String value of "hh:mm:ss a", i.e. 12:40:30 pm
     */
    public static String toTimeFormat(Date date) {
        if(date == null) {
            return BLANK_STRING;
        } else {
            return TIME.format(date);
        }
    }
    
    /**
     * @param date to be formatted
     * @return String value of "hh:mm a", i.e. 12:40 pm
     */
    public static String toShorttimeFormat(Date date) {
        if(date == null) {
            return BLANK_STRING;
        } else {
            return SHORT_TIME.format(date);
        }
    }
    
}
