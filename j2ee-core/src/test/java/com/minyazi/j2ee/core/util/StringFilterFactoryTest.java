package com.minyazi.j2ee.core.util;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.core.util.filter.StringFilter;
import com.minyazi.j2ee.core.util.filter.StringFilterFactory;

public class StringFilterFactoryTest {
    @Test
    public void test() {
        String chain = "html,upper";
        StringFilter stringFilter = StringFilterFactory.getInstance().getStringFilterChain(chain);
        LogUtil.info(stringFilter.filter("<title>test测试<title>"));
    }
}
