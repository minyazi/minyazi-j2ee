
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.minyazi.test.webservice.client3;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-16T17:04:34.622+08:00
 * Generated source version: 3.0.15
 * 
 */

@javax.jws.WebService(
                      serviceName = "HelloWorldWebServiceImplService",
                      portName = "HelloWorldWebServiceImplPort",
                      targetNamespace = "http://impl.webservice.service.minyazi.com/",
                      wsdlLocation = "http://localhost:8080/minyazi-web/ws/helloWorldWS?wsdl",
                      endpointInterface = "com.minyazi.test.webservice.client3.HelloWorldWebService")
                      
public class HelloWorldWebServiceImplPortImpl implements HelloWorldWebService {

    private static final Logger LOG = Logger.getLogger(HelloWorldWebServiceImplPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.minyazi.test.webservice.client3.HelloWorldWebService#sayHelloWorld(java.lang.String arg0)*
     */
    public java.lang.String sayHelloWorld(java.lang.String arg0) { 
        LOG.info("Executing operation sayHelloWorld");
        System.out.println(arg0);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
