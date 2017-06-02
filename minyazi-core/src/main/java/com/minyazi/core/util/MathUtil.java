package com.minyazi.core.util;

import com.minyazi.core.PlatformException;

/**
 * 数学工具类
 * 
 * @author minyazi
 */
public final class MathUtil {
    private MathUtil() {}
    
    /**
     * 获取数字的二进制字符串
     * 
     * @param number 要转换的数字
     * @return 数字的二进制字符串
     */
    public static String toBinaryString(int number) {
        StringBuilder sb = new StringBuilder();
        if (number == 0) {
            sb.append(0);
        } else {
            while (number != 0) {
                int temp = number & 1;
                sb.insert(0, temp);
                number = number >>> 1;
            }
        }
        return sb.toString();
    }
    
    /**
     * 验证数字是否是素数
     * 
     * @param number 要验证的数字
     * @return 如果验证的数字是素数，则返回true，否则返回false
     */
    public static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        if (number ==2) {
            return true;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 获取指定长度的斐波那契数列
     * 
     * @param length 斐波那契数列的长度
     * @return 斐波那契数列
     */
    public static int[] getFibonacciSequence(int length) {
        if (length < 1) {
            PlatformException pe = new PlatformException("斐波那契数列的长度必须为正整数");
            LogUtil.exception(pe);
            throw pe;
        }
        int[] fs = new int[length];
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == 1) {
                fs[i] = 1;
            } else {
                fs[i] = fs[i -1] + fs[i - 2];
            }
        }
        return fs;
    }
    
    /**
     * 验证数字是否是斐波那契数
     * 
     * @param number 要验证的数字
     * @return 如果验证的数字是斐波那契数，则返回true，否则返回false
     */
    public static boolean isFibonacciNumber(int number) {
        if (number < 1) {
            return false;
        } else if (number == 1) {
            return true;
        } else {
            int number1 = 1;
            int number2 = 1;
            while (true) {
                int result = number1 + number2;
                if (result > number) {
                    return false;
                } else if (result == number) {
                    return true;
                } else {
                    number1 = number2;
                    number2 = result;
                }
            }
        }
    }
}
