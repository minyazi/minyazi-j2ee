package com.minyazi.j2ee.core.util;

import org.junit.Test;

import com.minyazi.j2ee.core.util.CodeUtil;
import com.minyazi.j2ee.core.util.LogUtil;

public class CodeUtilTest {
    @Test
    public void testInit() {
        LogUtil.info(CodeUtil.getCodes().toString());
        LogUtil.info(CodeUtil.getCodeItemValue("filter", "html"));
    }
}
