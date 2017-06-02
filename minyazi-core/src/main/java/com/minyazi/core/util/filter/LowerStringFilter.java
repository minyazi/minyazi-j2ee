package com.minyazi.core.util.filter;

import java.util.Locale;

/**
 * 小写字符过滤器
 * 
 * @author minyazi
 */
public class LowerStringFilter extends AbstractStringFilter {
    public LowerStringFilter() {}
    
    @Override
    public String doFilter(String value) {
        if (value == null) {
            return null;
        }
        return value.toLowerCase(Locale.ENGLISH);
    }
}
