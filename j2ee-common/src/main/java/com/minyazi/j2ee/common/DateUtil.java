package com.minyazi.j2ee.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * 
 * @author minyazi
 */
public final class DateUtil {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    
    private DateUtil() {}
    
    /**
     * 用给定的日期模式构造SimpleDateFormat对象
     * 
     * @param pattern 日期模式
     * @return 指定日期模式的SimpleDateFormat对象
     */
    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        if (StringUtil.isEmptyString(pattern)) {
            return new SimpleDateFormat(DEFAULT_PATTERN, Locale.ENGLISH);
        } else {
            return new SimpleDateFormat(pattern, Locale.ENGLISH);
        }
    }
    
    /**
     * 获取日期
     * 
     * @return yyyyMMdd，如:20160101
     */
    public static String getDate() {
        return getSimpleDateFormat("yyyyMMdd").format(new Date());
    }
    
    /**
     * 获取时间
     * 
     * @return HHmmss，如：010101
     */
    public static String getTime() {
        return getSimpleDateFormat("HHmmss").format(new Date());
    }
    
    /**
     * 获取日期时间
     * 
     * @return yyyyMMddHHmmss，如：20160101010101
     */
    public static String getDateTime()  {
        return getSimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    
    /**
     * 获取完整的日期时间
     * 
     * @return yyyyMMddHHmmssSSS，如：20130101010101001
     */
    public static String getFullDateTime() {
        return getSimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
    
    /**
     * 获取ISO日期
     * 
     * @return yyyy-MM-dd，如:2013-01-01
     */
    public static String getISODate() {
        return getSimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
    /**
     * 获取ISO时间
     * 
     * @return HH:mm:ss，如：01:01:01
     */
    public static String getISOTime() {
        return getSimpleDateFormat("HH:mm:ss").format(new Date());
    }
    
    /**
     * 获取ISO日期时间
     * 
     * @return yyyy-MM-dd'T'HH:mm:ss，如：2013-01-01T01:01:01
     */
    public static String getISODateTime() {
        return getSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
    }
    
    /**
     * 获取完整的ISO日期时间
     * 
     * @return yyyy-MM-dd'T'HH:mm:ss.SSS，如：2013-01-01T01:01:01.001
     */
    public static String getFullISODateTime() {
        return getSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
    }
    
    /**
     * 用给定的日期模式格式化日期
     * 
     * @param pattern 日期模式
     * @return 指定日期模式的日期
     */
    public static String getSpecifiedDate(String pattern) {
        return getSimpleDateFormat(pattern).format(new Date());
    }
    
    /**
     * 用给定的日期构造SimpleDateFormat对象
     * 
     * @param date 日期
     * @return 指定日期所对应的SimpleDateFormat对象
     */
    public static SimpleDateFormat getSimpleDateFormatByDate(String date) {
        String pattern = "";
        if (!StringUtil.isEmptyString(date)) {
            if (date.indexOf("/") != -1) {
                pattern = "yyyy/MM/dd";
            } else if (date.indexOf("-") != -1) {
                pattern = "yyyy-MM-dd";
            } else {
                pattern = "yyyyMMdd";
            }
        }
        return getSimpleDateFormat(pattern);
    }
    
    /**
     * 获取两个日期的间隔天数
     * 
     * @param date1 日期1
     * @param date2 日期2
     * @return 日期1和日期2的间隔天数
     * @throws ParseException 
     */
    public static int getIntervalDays(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf1 = getSimpleDateFormatByDate(date1);
        Date d1 = sdf1.parse(date1);
        
        SimpleDateFormat sdf2 = getSimpleDateFormatByDate(date2);
        Date d2 = sdf2.parse(date2);
        
        if (d1.compareTo(d2) > 0) {
            Date temp = d2;
            d2 = d1;
            d1 = temp;
        }
        
        Calendar c = Calendar.getInstance();
        
        c.setTime(d1);
        long time1 = c.getTimeInMillis();
        
        c.setTime(d2);
        long time2 = c.getTimeInMillis();
        
        return (int) ((time2 - time1) / (1000 * 60 * 60 * 24));
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("间隔天数：" + getIntervalDays("2018-05-16", "2018-12-20"));
    }
}
