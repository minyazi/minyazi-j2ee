package com.minyazi.test.webservice.client1;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;

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
