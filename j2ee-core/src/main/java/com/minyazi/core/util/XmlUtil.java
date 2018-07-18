package com.minyazi.core.util;

import java.io.File;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.SAXException;

import com.minyazi.core.PlatformException;

/**
 * XML工具类
 * 
 * @author minyazi
 */
public final class XmlUtil {
    private XmlUtil() {}
    
    /**
     * 构造XML文件的Document对象
     * 
     * @param xmlString 要解析的XML文件
     * @return XML文件的Document对象
     */
    public static Document getXmlDocument(String xmlString) {
        try {
            SAXReader reader = new SAXReader();
            return reader.read(new StringReader(xmlString));
        } catch (DocumentException e) {
            PlatformException pe = new PlatformException("XML解析出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 使用Schema验证XML的合法性
     * 
     * @param xmlDocument 要验证的XML文件
     * @param xsdFilename Schema文件名
     * @return 如果验证通过，则返回true，否则返回false
     */
    @SuppressWarnings("unchecked")
    public static boolean validateXmlBySchema(Document xmlDocument, String xsdFilename) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            
            SAXParser parser = factory.newSAXParser();
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + xsdFilename);
            
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            // 错误处理器，存放验证过程中的错误信息
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            validator.setErrorHandler(errorHandler);
            // 验证XML文件的合法性
            validator.validate(xmlDocument);
            
            if (errorHandler.getErrors().hasContent()) {
                LogUtil.error("XML合法性验证失败，错误信息如下：");
                List<Element> elements = errorHandler.getErrors().elements();
                for (Element element : elements) {
                    LogUtil.error("(line {}) {}", Integer.parseInt(element.attributeValue("line")) + 1, element.getText());
                }
                /*
                StringWriter writer = new StringWriter();
                XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
                xmlWriter.write(errorHandler.getErrors());
                logger.error(writer.toString());
                writer.close();
                xmlWriter.close();
                */
                return false;
            } else {
                LogUtil.debug("XML合法性验证成功");
                return true;
            }
        } catch (ParserConfigurationException e) {
            PlatformException pe = new PlatformException("XML合法性验证出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (SAXException e) {
            PlatformException pe = new PlatformException("XML合法性验证出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 使用Schema验证XML的合法性
     * 
     * @param xmlFile 要验证的XML文件
     * @param xsdFilename Schema文件名
     * @return 如果验证通过，则返回true，否则返回false
     */
    public static boolean validateXmlBySchema(File xmlFile, String xsdFilename) {
        try {
            SAXReader reader = new SAXReader();
            Document xmlDocument = reader.read(xmlFile);
            return validateXmlBySchema(xmlDocument, xsdFilename);
        } catch (DocumentException e) {
            PlatformException pe = new PlatformException("XML合法性验证出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 使用Schema验证XML的合法性
     * 
     * @param xmlString 要验证的XML文件
     * @param xsdFilename Schema文件名
     * @return 如果验证通过，则返回true，否则返回false
     */
    public static boolean validateXmlBySchema(String xmlString, String xsdFilename) {
        try {
            SAXReader reader = new SAXReader();
            Document xmlDocument = reader.read(new StringReader(xmlString));
            return validateXmlBySchema(xmlDocument, xsdFilename);
        } catch (DocumentException e) {
            PlatformException pe = new PlatformException("XML合法性验证出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
}
