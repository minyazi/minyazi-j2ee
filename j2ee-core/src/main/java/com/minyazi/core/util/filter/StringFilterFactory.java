package com.minyazi.core.util.filter;

import java.util.Map;

import com.minyazi.core.PlatformException;
import com.minyazi.core.util.CodeUtil;
import com.minyazi.core.util.LogUtil;
import com.minyazi.core.util.StringUtil;

/**
 * 字符过滤器Factory
 * 
 * @author minyazi
 */
public class StringFilterFactory {
    private static final StringFilterFactory FACTORY = new StringFilterFactory();
    private Map<String, String> filterCodes;
    
    private StringFilterFactory() {
        filterCodes = CodeUtil.getCodeItems("filter");
    }
    
    public static StringFilterFactory getInstance() {
        return FACTORY;
    }
    
    /**
     * 通过反射机制构造字符过滤器的实例
     * 
     * @param code 字符过滤器代码
     * @return 构造的字符过滤器的实例
     */
    public StringFilter getStringFilter(String code) {
        if (StringUtil.isEmptyString(code)) {
            return new EmptyStringFilter();
        } else {
            try {
                Class<?> c = Class.forName(filterCodes.get(code));
                StringFilter stringFilter = (StringFilter) c.newInstance();
                return stringFilter;
            } catch (ClassNotFoundException e) {
                PlatformException pe = new PlatformException("构造实例出错：" + e.getMessage(), e);
                LogUtil.exception(pe);
                throw pe;
            } catch (InstantiationException e) {
                PlatformException pe = new PlatformException("构造实例出错：" + e.getMessage(), e);
                LogUtil.exception(pe);
                throw pe;
            } catch (IllegalAccessException e) {
                PlatformException pe = new PlatformException("构造实例出错：" + e.getMessage(), e);
                LogUtil.exception(pe);
                throw pe;
            }
        }
    }
    
    /**
     * 构造字符过滤器的链表结构
     * 
     * @param filters 字符过滤器链
     * @return 链表结构的第一个节点的引用
     */
    public StringFilter getStringFilterChain(String[] filters) {
        if (filters == null || filters.length == 0) {
            return new EmptyStringFilter();
        }
        
        StringFilter[] stringFilters = new StringFilter[filters.length];
        for (int i = filters.length - 1; i >= 0; i--) {
            stringFilters[i] = getStringFilter(filters[i]);
            if (i != filters.length - 1) {
                stringFilters[i].setNextStringFilter(stringFilters[i + 1]);
            } else {
                stringFilters[i].setNextStringFilter(null);
            }
        }
        
        return stringFilters[0];
    }
    
    /**
     * 获取所有字符过滤器的链表结构
     * 
     * @return 链表结构的第一个节点的引用
     */
    public StringFilter getAllStringFilterChain() {
        String[] filters = (String[]) filterCodes.values().toArray();
        return getStringFilterChain(filters);
    }
    
    /**
     * 获取字符过滤器的链表结构
     * 
     * @param chain 字符过滤器链（用英文逗号作为分隔符）
     * @return 链表结构的第一个节点的引用
     */
    public StringFilter getStringFilterChain(String chain) {
        if (StringUtil.isEmptyString(chain)) {
            return new EmptyStringFilter();
        }
        
        if ("all".equals(chain)) {
            return getAllStringFilterChain();
        } else {
            return getStringFilterChain(chain.split(","));
        }
    }
}
