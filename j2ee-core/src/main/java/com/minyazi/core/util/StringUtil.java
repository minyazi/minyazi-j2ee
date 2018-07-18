package com.minyazi.core.util;

import java.util.Locale;
import java.util.UUID;

import com.minyazi.core.PlatformException;

/**
 * 字符串工具类
 * 
 * @author minyazi
 */
public final class StringUtil {
    private StringUtil() {}
    
    /**
     * 转换为小写字符串
     * 
     * @param value 要转换的字符串
     * @return 转换为小写的字符串
     */
    public static String toLowerCase(String value) {
        return value == null ? null : value.toLowerCase(Locale.ENGLISH);
    }
    
    /**
     * 转换为大写字符串
     * 
     * @param value 要转换的字符串
     * @return 转换为大写的字符串
     */
    public static String toUpperCase(String value) {
        return value == null ? null : value.toUpperCase(Locale.ENGLISH);
    }
    
    /**
     * 获取UUID
     * 
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * 获取小写的UUID
     * 
     * @return 转换为小写的UUID
     */
    public static String getLowerUUID() {
        return toLowerCase(getUUID());
    }
    
    /**
     * 获取大写的UUID
     * 
     * @return 转换为大写的UUID
     */
    public static String getUpperUUID() {
        return toUpperCase(getUUID());
    }
    
    /**
     * 格式化null字符串
     * 
     * @param value 要格式化的String
     * @return 如果String等于null，则返回空字符串，否则返回String本身
     */
    public static String formatNullString(String value) {
        return value == null ? "" : value;
    }
    
    /**
     * 格式化null金额
     * 
     * @param value 要格式化的Amount
     * @return 如果Amount等于null或空字符串，则返回null，否则返回Amount本身
     */
    public static String formatNullAmount(String value) {
        return (value == null || value.trim().equals("")) ? null : value;
    }
    
    /**
     * 验证是否是空字符串
     * 
     * @param value 要验证的String
     * @return 如果String等于null或空字符串，则返回true，否则返回false
     */
    public static boolean isEmptyString(String value) {
        return formatNullString(value).trim().equals("");
    }
    
    /**
     * 字符串补位（不足补位长度的按补位类型补位，超过补位长度的截掉超过部分）
     * 
     * @param value 要补位的字符串
     * @param fillType 补位类型（0：不补位，1：前补空格，2：后补空格，3：前补0，4：后补0）
     * @param fillLength 补位长度
     * @return 补位后的字符串
     */
    public static String fill(String value, String fillType, Integer fillLength) {
        value = formatNullString(value);
        int valueLength = value.length();
        if (isEmptyString(fillType)){
            fillType = "0";
        }
        if ("01234".indexOf(fillType) == -1) {
            PlatformException pe = new PlatformException("补位类型错误");
            LogUtil.exception(pe);
            throw pe;
        }
        if (fillLength == null) {
            fillLength = valueLength;
        }
        
        if (valueLength > fillLength) {
            value = value.substring(0, fillLength);
        } else if (valueLength < fillLength) {
            if (!fillType.equals("0")) {
                StringBuilder temp = new StringBuilder(value);
                int j = fillLength - valueLength;
                for (int i = 0; i < j; i++) {
                    if (fillType.equals("1")) { // 前补空格
                        temp.insert(0, " ");
                    } else if (fillType.equals("2")) { // 后补空格
                        temp.append(" ");
                    } else if (fillType.equals("3")) { // 前补0
                        temp.insert(0, "0");
                    } else if (fillType.equals("4")) { // 后补0
                        temp.append("0");
                    }
                }
                value = temp.toString();
            }
        }
        
        return value;
    }
}
