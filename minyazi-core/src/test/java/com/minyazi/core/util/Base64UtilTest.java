package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.Base64Util;
import com.minyazi.core.util.LogUtil;

public class Base64UtilTest {
    @Test
    public void test() {
        LogUtil.info(Base64Util.encodeString("test"));
        LogUtil.info(Base64Util.decodeString("dGVzdA=="));
        LogUtil.info(Base64Util.encodeFile("D:/SFTP", "README.md"));
        String base64String = "IyBtaW55YXppdXRpbHMNCuaVj+S8ouWtkOeahOW3peWFt+exu+W6kw0KDQojI1YxLjPniYjmnKzlj5jmm7Tor7TmmI7vvIgyMDE2L3h4L3h4KQ0KKiDlop7liqBNRDXlt6XlhbfnsbvvvIhNRDVVdGls77yJ77yM5o+Q5L6b6I635Y+WTUQ15Yqg5a+G5Liy55qE5bi455So5pa55rOV44CCDQoNCg0KDQojI1YxLjLniYjmnKzlj5jmm7Tor7TmmI7vvIgyMDE2LzA5LzI4KQ0KKiDlop7liqBFeGNlbOW3peWFt+exu++8iEV4Y2VsVXRpbO+8ie+8jOaPkOS+m+mSiOWvuUV4Y2Vs5paH5Lu25pON5L2c55qE5bi455So5pa55rOV77yM6ZyA6KaB5L6d6LWWYXBhY2hlLXBvaS0zLjEwLjHnsbvlupPjgIINCiog5aKe5YqgSkRCQ+W3peWFt+exu++8iEpkYmNVdGls77yJ77yM5o+Q5L6b6ZKI5a+5SkRCQ+aTjeS9nOeahOW4uOeUqOaWueazle+8jOmcgOimgeS+nei1lmMzcDAtMC45LjUuMeOAgWFwYWNoZS1jb21tb25zLWRidXRpbHMtMS425ZKMbXlzcWwtZHJpdmVyLTUuMS4zOeexu+W6k+OAgg0KDQojI1YxLjHniYjmnKzlj5jmm7Tor7TmmI7vvIgyMDE2LzA5LzI2KQ0KKiDlop7liqDku6PnoIHlt6XlhbfnsbvvvIhDb2RlVXRpbO+8ie+8jOeUqOS6juS7juS7o+eggemFjee9ruaWh+S7tuS4reiOt+WPluS7o+eggeOAgg0KKiDlop7liqDlrZfnrKbov4fmu6TlmajvvIhTdHJpbmdGaWx0ZXLvvInvvIznlKjkuo7lrp7njrDlrZfnrKbkuLLov4fmu6TjgIINCg0KIyNWMS4w54mI5pys6K+05piO77yIMjAxNi8wOS8yMe+8iQ0KKiBEYXRlVXRpbO+8muaXpeacn+W3peWFt+exu++8jOaPkOS+m+mSiOWvueaXpeacn+aTjeS9nOeahOW4uOeUqOaWueazleOAgg0KKiBGaWxlVXRpbO+8muaWh+S7tuW3peWFt+exu++8jOaPkOS+m+mSiOWvueaWh+S7tuaTjeS9nOeahOW4uOeUqOaWueazleOAgg0KKiBMb2dVdGls77ya5pel5b+X5bel5YW357G777yM5o+Q5L6b6ZKI5a+55pel5b+X5pON5L2c55qE5bi455So5pa55rOV77yM6ZyA6KaB5L6d6LWWYXBhY2hlLWxvZzRqLTEuMi4xN+exu+W6k+OAgg0KKiBTdHJpbmdVdGls77ya5a2X56ym5Liy5bel5YW357G777yM5o+Q5L6b6ZKI5a+55a2X56ym5Liy55qE5bi455So5pa55rOV44CCDQoqIFV0aWxpdHnvvJrlt6XlhbfnsbvvvIzmj5DkvpvkuIDkupvlhbbku5bnmoTluLjnlKjmlrnms5XjgIINCiogWG1sVXRpbO+8mlhNTOW3peWFt+exu++8jOaPkOS+m+mSiOWvuVhNTOaWh+S7tuaTjeS9nOeahOW4uOeUqOaWueazle+8jOmcgOimgeS+nei1lmRvbTRqLTEuNi4x57G75bqT44CCDQo=";
        Base64Util.decodeFile(base64String, "D:/SFTP", "README.md.cmd");
    }
}
