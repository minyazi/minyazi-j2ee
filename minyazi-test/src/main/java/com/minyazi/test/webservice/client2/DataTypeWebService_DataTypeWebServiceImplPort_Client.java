
package com.minyazi.test.webservice.client2;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-15T18:04:46.847+08:00
 * Generated source version: 3.0.15
 * 
 */
public final class DataTypeWebService_DataTypeWebServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://webservice.test.minyazi.com/", "DataTypeWebServiceImplService");

    private DataTypeWebService_DataTypeWebServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = DataTypeWebServiceImplService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        DataTypeWebServiceImplService ss = new DataTypeWebServiceImplService(wsdlURL, SERVICE_NAME);
        DataTypeWebService port = ss.getDataTypeWebServiceImplPort();  
        
        {
        System.out.println("Invoking getUserById...");
        java.lang.String _getUserById_arg0 = "";
        com.minyazi.test.webservice.client2.User _getUserById__return = port.getUserById(_getUserById_arg0);
        System.out.println("getUserById.result=" + _getUserById__return);


        }
        {
        System.out.println("Invoking isExist...");
        com.minyazi.test.webservice.client2.User _isExist_arg0 = null;
        boolean _isExist__return = port.isExist(_isExist_arg0);
        System.out.println("isExist.result=" + _isExist__return);


        }
        {
        System.out.println("Invoking getUserName...");
        java.lang.String _getUserName_arg0 = "";
        java.lang.String _getUserName__return = port.getUserName(_getUserName_arg0);
        System.out.println("getUserName.result=" + _getUserName__return);


        }
        {
        System.out.println("Invoking getUsers...");
        com.minyazi.test.webservice.client2.MapConvertor _getUsers__return = port.getUsers();
        System.out.println("getUsers.result=" + _getUsers__return);


        }
        {
        System.out.println("Invoking getUsersByName...");
        java.lang.String _getUsersByName_arg0 = "";
        java.util.List<com.minyazi.test.webservice.client2.User> _getUsersByName__return = port.getUsersByName(_getUsersByName_arg0);
        System.out.println("getUsersByName.result=" + _getUsersByName__return);


        }

        System.exit(0);
    }

}
