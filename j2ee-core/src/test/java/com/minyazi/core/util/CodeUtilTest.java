package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.CodeUtil;
import com.minyazi.core.util.LogUtil;

public class CodeUtilTest {
    @Test
    public void testInit() {
        LogUtil.info(CodeUtil.getCodes().toString());
        LogUtil.info(CodeUtil.getCodeItemValue("filter", "html"));
    }
}
