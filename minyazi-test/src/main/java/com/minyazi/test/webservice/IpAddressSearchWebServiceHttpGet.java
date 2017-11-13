package com.minyazi.test.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-13T15:06:15.902+08:00
 * Generated source version: 3.0.15
 * 
 */
@WebService(targetNamespace = "http://WebXml.com.cn/", name = "IpAddressSearchWebServiceHttpGet")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface IpAddressSearchWebServiceHttpGet {

    /**
     * <br /><h3>通过输入IP地址查询国家、城市、所有者等信息。没有注明国家的为中国</h3><p>输入参数：IP地址（自动替换 " 。" 为 "."），返回数据： 一个一维字符串数组String(1)，String(0) = IP地址；String(1) = 查询结果或提示信息</p><br />
     */
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getCountryCityByIp(
        @WebParam(partName = "theIpAddress", name = "theIpAddress", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theIpAddress
    );

    /**
     * <br /><h3>获得本IP地址搜索 WEB 服务的数据库版本更新时间</h3><p>输入参数：无，输出参数 String</p><br />
     */
    @WebResult(name = "string", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public java.lang.String getVersionTime();

    /**
     * <br /><h3>获得您的IP地址和地址信息</h3><p>输入参数：无，返回数据： 一个一维字符串数组String(1)，String(0) = IP地址；String(1) = 地址信息</p><br />
     */
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getGeoIPContext();
}
