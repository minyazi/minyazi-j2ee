package com.minyazi.test.webservice;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class TestWebService {
    @Test
    public void testWebService() {
        IpAddressSearchWebService service2 = new IpAddressSearchWebService();
        IpAddressSearchWebServiceSoap serviceSoap = service2.getIpAddressSearchWebServiceSoap();
        ArrayOfString cityByip = serviceSoap.getCountryCityByIp("58.60.251.125");
        List<String> ips = cityByip.getString();
        for (Iterator<String> ite = ips.iterator(); ite.hasNext();) {
            String ip = (String) ite.next();
            System.out.println(ip);
        }
    }
}
