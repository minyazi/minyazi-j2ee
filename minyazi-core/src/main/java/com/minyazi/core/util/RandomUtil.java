package com.minyazi.core.util;

import java.util.Random;

/**
 * 随机数工具类
 * 
 * @author minyazi
 */
public final class RandomUtil {
    private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASECHAR = "abcdefghijkllmnopqrstuvwxyz";
    private static final String UPPERCASECHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERCHAR = "0123456789";

    private RandomUtil() {}

    /**
     * 返回一个指定长度的随机字符串（只包含大小写字母和数字）
     * 
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个指定长度的随机字母字符串（只包含大小写字母）
     * 
     * @param length 随机字母字符串长度
     * @return 随机字母字符串
     */
    public static String randomLetterString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }
    
    /**
     * 返回一个指定长度的随机小写字母字符串（只包含小写字母）
     * 
     * @param length 随机小写字母字符串长度
     * @return 随机小写字母字符串
     */
    public static String randomLowerCaseString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LOWERCASECHAR.charAt(random.nextInt(LOWERCASECHAR.length())));
        }
        return sb.toString();
    }
    
    /**
     * 返回一个指定长度的随机大写字母字符串（只包含大写字母）
     * 
     * @param length 随机大写字母字符串长度
     * @return 随机大写字母字符串
     */
    public static String randomUpperCaseString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(UPPERCASECHAR.charAt(random.nextInt(UPPERCASECHAR.length())));
        }
        return sb.toString();
    }
    
    /**
     * 返回一个指定长度的随机数字字符串（只包含数字）
     * 
     * @param length 随机数字字符串长度
     * @return 随机数字字符串
     */
    public static String randomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个指定长度的随机数字
     * 
     * @param length 随机数字长度
     * @return 随机数字
     */
    public static int randomNumber(int length) {
        int[] number = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        for (int i = number.length; i > 1; i--) {
            int index = random.nextInt(i);
            int temp = number[index];
            number[index] = number[i - 1];
            number[i - 1] = temp;
        }
        int result = 0;
        for (int i = 0; i < length; i++) {
            result = result * 10 + number[i];
        }
        return result;
    }

}
