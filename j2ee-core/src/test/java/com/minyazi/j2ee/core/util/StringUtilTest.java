package com.minyazi.j2ee.core.util;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.core.util.StringUtil;

public class StringUtilTest {
    @Test
    public void testGetUUID() {
        LogUtil.info(StringUtil.getUUID());
    }
    
    @Test
    public void testFill() {
        LogUtil.info(StringUtil.fill("1", "3", 5));
    }
}
