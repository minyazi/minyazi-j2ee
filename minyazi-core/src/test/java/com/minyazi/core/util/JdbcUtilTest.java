package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.JdbcUtil;
import com.minyazi.core.util.LogUtil;

public class JdbcUtilTest {
    @Test
    public void testInit() {
        JdbcUtil.init();
        LogUtil.info(JdbcUtil.queryToMap("select * from test").toString());
    }
}
