package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.FileUtil;
import com.minyazi.core.util.LogUtil;
import com.minyazi.core.util.Utility;

public class FileUtilTest {
    @Test
    public void testReadFile() {
        LogUtil.info(FileUtil.readFile(Utility.getClassPath(), "code-config.xml", "UTF-8", false, true));
    }
}
