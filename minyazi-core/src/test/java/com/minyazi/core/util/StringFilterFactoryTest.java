package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;
import com.minyazi.core.util.filter.StringFilter;
import com.minyazi.core.util.filter.StringFilterFactory;

public class StringFilterFactoryTest {
    @Test
    public void test() {
        String chain = "html,upper";
        StringFilter stringFilter = StringFilterFactory.getInstance().getStringFilterChain(chain);
        LogUtil.info(stringFilter.filter("<title>test测试<title>"));
    }
}
