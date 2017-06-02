package com.minyazi.core.util.filter;

/**
 * HTML字符过滤器
 * 
 * @author minyazi
 */
public class HtmlStringFilter extends AbstractStringFilter {
    public HtmlStringFilter() {}
    
    @Override
    public String doFilter(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll("\\t", "    ")
            .replaceAll("&", "&amp;")
            .replaceAll(" ", "&nbsp;")
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll("\"", "&quot;");
        return value;
    }
}
