package com.minyazi.core.util.filter;

/**
 * Empty字符过滤器
 * 
 * @author minyazi
 */
public class EmptyStringFilter extends AbstractStringFilter {
    public EmptyStringFilter() {}
    
    @Override
    public String doFilter(String value) {
        return value;
    }
}
