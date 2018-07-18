package com.minyazi.j2ee.web;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.web.HttpRequestSimulator;

public class HttpRequestSimulatorTest {
    @Test
    public void testInit() {
        String urlStr = "http://www.baidu.com/";
        Map<String, String> params = new HashMap<String, String>();
        //params.put("Test", "测试");
        LogUtil.info(HttpRequestSimulator.doPost(urlStr, params));
    }
}
