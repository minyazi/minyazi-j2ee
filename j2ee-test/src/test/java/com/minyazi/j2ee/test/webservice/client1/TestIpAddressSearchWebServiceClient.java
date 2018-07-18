package com.minyazi.j2ee.test.webservice.client1;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.test.webservice.client1.ArrayOfString;
import com.minyazi.j2ee.test.webservice.client1.IpAddressSearchWebService;
import com.minyazi.j2ee.test.webservice.client1.IpAddressSearchWebServiceSoap;

public class TestIpAddressSearchWebServiceClient {
    @Test
    public void test() {
        IpAddressSearchWebService service = new IpAddressSearchWebService();
        IpAddressSearchWebServiceSoap serviceSoap = service.getIpAddressSearchWebServiceSoap();
        ArrayOfString result = serviceSoap.getCountryCityByIp("58.60.251.125");
        for (String _result : result.getString()) {
            LogUtil.info(_result);
        }
    }
}
