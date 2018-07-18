package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;
import com.minyazi.core.util.Utility;

public class UtilityTest {
    @Test
    public void testGetClassPath() {
        LogUtil.info(Utility.getClassPath());
    }
}
