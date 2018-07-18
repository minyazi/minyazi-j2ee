package com.minyazi.j2ee.test;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.test.JdbcUtil;

public class JdbcUtilTest {
    @Test
    public void testInit() {
        JdbcUtil.init();
        LogUtil.info(JdbcUtil.queryToMap("select * from test").toString());
    }
}
