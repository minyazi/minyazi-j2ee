package com.minyazi.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.minyazi.core.PlatformException;

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
        LogUtil.debug("日期模式：{}", pattern);
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
     * 在给定的日期的基础上增加或减少一定的日期变化量
     * 
     * @param date 日期
     * @param amount 日期变化量（正数表示增加，负数表示减少）
     * @return 指定日期增加或减少指定日期变化量后所对应的日期
     */
    public static String getSpecifiedDay(String date, int amount) {
        LogUtil.debug("日期：{}，日期变化量：{}", date, amount);
        try {
            SimpleDateFormat sdf = getSimpleDateFormatByDate(date);
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            c.add(Calendar.DAY_OF_MONTH, amount);
            return sdf.format(c.getTime());
        } catch (ParseException e) {
            PlatformException pe = new PlatformException("日期解析出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 在给定的日期的基础上增加或减少一定的日期变化量
     * 
     * @param date 日期
     * @param field 日历字段
     * @param amount 日期变化量（正数表示增加，负数表示减少）
     * @return 指定日期增加或减少指定日期变化量后所对应的日期
     */
    public static String getSpecifiedDay(String date, int field, int amount) {
        try {
            SimpleDateFormat sdf = getSimpleDateFormatByDate(date);
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            c.add(field, amount);
            return sdf.format(c.getTime());
        } catch (ParseException e) {
            PlatformException pe = new PlatformException("日期解析出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 获取给定的日期的前一天
     * 
     * @param date 日期
     * @return 指定日期的前一天
     */
    public static String getPreviousDay(String date) {
        return getSpecifiedDay(date, -1);
    }
    
    /**
     * 获取给定的日期的后一天
     * 
     * @param date 日期
     * @return 指定日期的后一天
     */
    public static String getNextDay(String date) {
        return getSpecifiedDay(date, 1);
    }
    
    /**
     * 判断给定的日期是否是节假日
     * 
     * @param date 日期
     * @return 如果指定日期是节假日，则返回true，否则返回false
     */
    public static boolean isHoliday(String date) {
        try {
            SimpleDateFormat sdf = getSimpleDateFormatByDate(date);
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                return true; // 节假日
            } else {
                return false; // 非节假日
            }
        } catch (ParseException e) {
            PlatformException pe = new PlatformException("日期解析出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 获取给定的日期的下一个工作日（节假日顺延）
     * 
     * @param date 日期
     * @return 指定日期的下一个工作日（节假日顺延）
     */
    public static String getNextWorkday(String date) {
        String nextDay = getNextDay(date);
        while (true) {
            if (isHoliday(nextDay)) {
                nextDay = getNextDay(nextDay);
                continue;
            } else {
                break;
            }
        }
        return nextDay;
    }
    
    /**
     * 获取给定的日期的星期
     * 
     * @param date 日期
     * @return 指定日期的星期
     */
    public static String getDayOfWeek(String date) {
        try {
            SimpleDateFormat sdf = getSimpleDateFormat(date);
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(date));
            String week = ""; // 星期
            switch (c.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    week = "星期日";
                    break;
                case 2:
                    week = "星期一";
                    break;
                case 3:
                    week = "星期二";
                    break;
                case 4:
                    week = "星期三";
                    break;
                case 5:
                    week = "星期四";
                    break;
                case 6:
                    week = "星期五";
                    break;
                case 7:
                    week = "星期六";
                    break;
            }
            return week;
        } catch (ParseException e) {
            PlatformException pe = new PlatformException("日期解析出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 获取给定的出生日期所对应的年龄
     * 
     * @param birthday 出生日期
     * @return 指定出生日期所对应的年龄
     */
    public static int getAge(String birthday) {
        try {
            SimpleDateFormat sdf = getSimpleDateFormatByDate(birthday);
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(birthday));
            sdf = getSimpleDateFormat("yyyy");
            return Integer.parseInt(sdf.format(new Date())) - Integer.parseInt(sdf.format(c.getTime()));
        } catch (ParseException e) {
            PlatformException pe = new PlatformException("日期解析出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
}
