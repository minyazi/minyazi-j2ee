
package com.minyazi.test.webservice;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-13T15:06:15.924+08:00
 * Generated source version: 3.0.15
 * 
 */
 
public class IpAddressSearchWebServiceSoap_IpAddressSearchWebServiceSoap12_Server{

    protected IpAddressSearchWebServiceSoap_IpAddressSearchWebServiceSoap12_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new IpAddressSearchWebServiceSoap12Impl();
        String address = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new IpAddressSearchWebServiceSoap_IpAddressSearchWebServiceSoap12_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
