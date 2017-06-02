package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.DateUtil;
import com.minyazi.core.util.LogUtil;

public class DateUtilTest {
    @Test
    public void testGetDate() {
        LogUtil.info(DateUtil.getDate());
    }
    
    @Test
    public void testGetISODate() {
        LogUtil.info(DateUtil.getISODate());
    }
    
    @Test
    public void testGetSpecifiedDay() {
        LogUtil.info(DateUtil.getSpecifiedDay("2016-01-01", 1));
    }
    
    @Test
    public void testGetAge() {
        LogUtil.info(DateUtil.getAge("1990-01-01") + "");
    }
}
