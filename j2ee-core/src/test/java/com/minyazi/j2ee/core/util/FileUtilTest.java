package com.minyazi.j2ee.core.util;

import org.junit.Test;

import com.minyazi.j2ee.core.util.FileUtil;
import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.core.util.Utility;

public class FileUtilTest {
    @Test
    public void testReadFile() {
        LogUtil.info(FileUtil.readFile(Utility.getClassPath(), "code-config.xml", "UTF-8", false, true));
    }
}
