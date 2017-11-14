package com.minyazi.test.webservice.client2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.15
 * 2017-11-14T17:20:11.019+08:00
 * Generated source version: 3.0.15
 * 
 */
@WebServiceClient(name = "DataTypeWebServiceImplService", 
                  wsdlLocation = "http://localhost:9000/ws/DataTypeWS?wsdl",
                  targetNamespace = "http://webservice.test.minyazi.com/") 
public class DataTypeWebServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservice.test.minyazi.com/", "DataTypeWebServiceImplService");
    public final static QName DataTypeWebServiceImplPort = new QName("http://webservice.test.minyazi.com/", "DataTypeWebServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9000/ws/DataTypeWS?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DataTypeWebServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:9000/ws/DataTypeWS?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DataTypeWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DataTypeWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DataTypeWebServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns DataTypeWebService
     */
    @WebEndpoint(name = "DataTypeWebServiceImplPort")
    public DataTypeWebService getDataTypeWebServiceImplPort() {
        return super.getPort(DataTypeWebServiceImplPort, DataTypeWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DataTypeWebService
     */
    @WebEndpoint(name = "DataTypeWebServiceImplPort")
    public DataTypeWebService getDataTypeWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(DataTypeWebServiceImplPort, DataTypeWebService.class, features);
    }

}
