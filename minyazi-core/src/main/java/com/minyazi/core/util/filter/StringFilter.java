package com.minyazi.core.util.filter;

/**
 * 字符过滤器
 * 
 * @author minyazi
 */
public interface StringFilter {
    /**
     * 设置下一个字符过滤器
     * 
     * @param filter 字符过滤器
     */
    void setNextStringFilter(StringFilter filter);
    
    /**
     * 字符过滤
     * 
     * @param value 要过滤的字符
     * @return 过滤后的字符
     */
    String filter(String value);
}
