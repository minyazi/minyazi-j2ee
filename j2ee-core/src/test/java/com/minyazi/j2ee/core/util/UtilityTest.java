package com.minyazi.j2ee.core.util;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.core.util.Utility;

public class UtilityTest {
    @Test
    public void testGetClassPath() {
        LogUtil.info(Utility.getClassPath());
    }
}
