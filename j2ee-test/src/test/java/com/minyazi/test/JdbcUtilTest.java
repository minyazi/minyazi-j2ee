package com.minyazi.test;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;
import com.minyazi.test.JdbcUtil;

public class JdbcUtilTest {
    @Test
    public void testInit() {
        JdbcUtil.init();
        LogUtil.info(JdbcUtil.queryToMap("select * from test").toString());
    }
}
