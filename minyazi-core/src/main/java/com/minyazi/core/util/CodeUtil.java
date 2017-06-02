package com.minyazi.core.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.minyazi.core.PlatformException;

/**
 * 代码工具类
 * 
 * @author minyazi
 */
public final class CodeUtil {
    /**
     * 代码列表
     */
    private static Map<String, Map<String, String>> codes;
    /**
     * 代码配置文件列表
     */
    private static List<String> files = new ArrayList<String>();
    
    private CodeUtil() {}
    
    /**
     * 初始化代码列表
     */
    private static void init() {
        codes = new LinkedHashMap<String, Map<String, String>>();
        init("code-config-default.xml");
        init("code-config.xml");
    }
    
    /**
     * 初始化代码列表
     * 
     * @param codeConfigFileName 要初始化的代码配置文件的文件名（代码配置文件需要存放于系统的classpath路径下）
     */
    @SuppressWarnings("unchecked")
    public static void init(String codeConfigFileName) {
        if (codes == null) {
            init();
        }
        
        if (files.contains(codeConfigFileName)) {
            return;
        } else {
            files.add(codeConfigFileName);
        }
        
        String xmlString = FileUtil.readFile(Utility.getClassPath(), codeConfigFileName, "UTF-8", false, true);
        if (StringUtil.isEmptyString(xmlString)) {
            return;
        }
        
        // 从代码配置文件中解析出代码
        Document xmlDocument = XmlUtil.getXmlDocument(xmlString);
        Element rootElement = xmlDocument.getRootElement();
        List<Element> codeElements = rootElement.elements("code");
        for (Element codeElement : codeElements) {
            List<Attribute> codeAttributes = codeElement.attributes();
            String codeName = null; // 代码名称
            String codeDescription = null; // 代码描述
            for (Attribute codeAttribute : codeAttributes) {
                if (codeAttribute.getName().equals("name")) {
                    codeName = codeAttribute.getValue().trim();
                } else if (codeAttribute.getName().equals("description")) {
                    codeDescription = codeAttribute.getValue().trim();
                }
            }
            LogUtil.debug("代码名称：{}", codeName);
            LogUtil.debug("代码描述：{}", codeDescription);
            if (StringUtil.isEmptyString(codeName)) {
                PlatformException pe = new PlatformException("代码名称未配置");
                LogUtil.exception(pe);
                throw pe;
            }
            
            Map<String, String> code = null;
            if (codes.get(codeName) != null) {
                code = codes.get(codeName);
            } else {
                code = new LinkedHashMap<String, String>();
                codes.put(codeName, code);
            }
            
            List<Element> codeItems = codeElement.elements("item");
            for (Element codeItem : codeItems) {
                List<Attribute> itemAttributes = codeItem.attributes();
                String itemKey = null; // 代码项键
                String itemvalue = null; // 代码项值
                for (Attribute itemAttribute : itemAttributes) {
                    if (itemAttribute.getName().equals("key")) {
                        itemKey = itemAttribute.getValue().trim();
                    } else if (itemAttribute.getName().equals("value")) {
                        itemvalue = itemAttribute.getValue().trim();
                    }
                }
                LogUtil.debug("代码项键：{}", itemKey);
                LogUtil.debug("代码项值：{}", itemvalue);
                if (StringUtil.isEmptyString(itemKey)) {
                    PlatformException pe = new PlatformException("代码项键未配置");
                    LogUtil.exception(pe);
                    throw pe;
                }
                if (StringUtil.isEmptyString(itemvalue)) {
                    PlatformException pe = new PlatformException("代码项值未配置");
                    LogUtil.exception(pe);
                    throw pe;
                }
                
                code.put(itemKey, itemvalue);
            }
        }
    }
    
    /**
     * 获取代码列表
     * 
     * @return 代码列表
     */
    public static Map<String, Map<String, String>> getCodes() {
        if (codes == null) {
            init();
        }
        return codes;
    }
    
    /**
     * 获取指定代码的代码列表
     * 
     * @param name 代码名称
     * @return 指定代码的代码列表
     */
    public static Map<String, String> getCodeItems(String name) {
        if (codes == null) {
            init();
        }
        if (codes.get(name) == null) {
            return new LinkedHashMap<String, String>();
        } else {
            return codes.get(name);
        }
    }
    
    /**
     * 获取代码的代码项值
     * 
     * @param name 代码名称
     * @param itemKey 代码项键
     * @return 指定代码的代码列表中代码项键所对应的代码项值
     */
    public static String getCodeItemValue(String name, String itemKey) {
        if (codes == null) {
            init();
        }
        Map<String, String> items = getCodeItems(name);
        if (items != null && items.get(itemKey) != null) {
            return items.get(itemKey);
        } else {
            return "";
        }
    }
}
