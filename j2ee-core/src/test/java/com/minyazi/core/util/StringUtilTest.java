package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;
import com.minyazi.core.util.StringUtil;

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
