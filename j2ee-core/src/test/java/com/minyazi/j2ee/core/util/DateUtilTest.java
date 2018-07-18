package com.minyazi.j2ee.core.util;

import org.junit.Test;

import com.minyazi.j2ee.core.util.DateUtil;
import com.minyazi.j2ee.core.util.LogUtil;

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
    
    @Test
    public void testGetIntervalDays() {
        LogUtil.info("间隔天数：" + DateUtil.getIntervalDays("2017-01-01", "2016-11-30"));
        LogUtil.info("间隔天数：" + DateUtil.getIntervalDays("2017-01-01", "2016-12-31"));
        LogUtil.info("间隔天数：" + DateUtil.getIntervalDays("2017-01-01", "2017-01-01"));
        LogUtil.info("间隔天数：" + DateUtil.getIntervalDays("2017-01-01", "2017-01-02"));
        LogUtil.info("间隔天数：" + DateUtil.getIntervalDays("2017-01-01", "2017-02-01"));
        LogUtil.info("间隔天数：" + DateUtil.getIntervalDays("2017-01-01", "2017-03-01"));
    }
}
