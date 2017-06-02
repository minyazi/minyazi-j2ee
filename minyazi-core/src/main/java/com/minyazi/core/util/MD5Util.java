package com.minyazi.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

import com.minyazi.core.PlatformException;

/**
 * MD5工具类
 * 
 * @author minyazi
 */
public final class MD5Util {
    private MD5Util() {}
    
    /**
     * 使用MD5加密字符串
     * 
     * @param value 要加密的字符串
     * @return 加密后的结果
     */
    public static String encodeString(String value) {
        return DigestUtils.md5Hex(value);
    }
    
    /**
     * 使用MD5加密文件
     * 
     * @param file 要加密的文件
     * @return 加密后的结果
     */
    public static String encodeFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            return DigestUtils.md5Hex(fis);
        } catch (FileNotFoundException e) {
            PlatformException pe = new PlatformException("MD5加密文件出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (IOException e) {
            PlatformException pe = new PlatformException("MD5加密文件出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 使用MD5加密文件
     * 
     * @param filePath 要加密的文件所在路径
     * @param fileName 要加密的文件的文件名
     * @return 加密后的结果
     */
    public static String encodeFile(String filePath, String fileName) {
        String path = filePath + "/" + fileName;
        File file = new File(path);
        return encodeFile(file);
    }
    
    /**
     * MD5加密
     * 
     * @param message 要加密的信息
     * @return 存放哈希值结果的byte数组
     */
    public static byte[] getMD5Bytes(String message) {
        try {
            // 获取MD5加密算法的信息摘要
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 添加要加密的信息
            md5.update(message.getBytes());
            return md5.digest();
        } catch (NoSuchAlgorithmException e) {
            PlatformException pe = new PlatformException("MD5加密出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 获取32位小写的MD5加密串
     * 
     * @param message 要加密的信息
     * @return 32位小写的MD5加密串
     */
    public static String get32LowerMD5(String message) {
        byte[] md5Bytes = getMD5Bytes(message);
        StringBuilder result = new StringBuilder(500);
        for (byte md5Byte : md5Bytes) {
            String value = Integer.toHexString(0xff & md5Byte);
            if (value.length() == 1) {
                result.append("0" + value);
            } else {
                result.append(value);
            }
        }
        return result.toString();
    }
    
    /**
     * 获取32位大写的MD5加密串
     * 
     * @param message 要加密的信息
     * @return 32位大写的MD5加密串
     */
    public static String get32UpperMD5(String message) {
        byte[] md5Bytes = getMD5Bytes(message);
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] result = new char[md5Bytes.length * 2];
        int j = 0;
        for (int i = 0; i < md5Bytes.length; i++) {
            byte value = md5Bytes[i];
            result[j++] = hexChars[value >>> 4 & 0xf];
            result[j++] = hexChars[value & 0xf];
        }
        return new String(result);
    }
    
    /**
     * 获取16位小写的MD5加密串
     * 
     * @param message 要加密的信息
     * @return 16位小写的MD5加密串
     */
    public static String get16LowerMD5(String message) {
        byte[] md5Bytes = getMD5Bytes(message);
        StringBuilder result = new StringBuilder(500);
        for (int i = 0; i < md5Bytes.length; i++) {
            int value = ((int) md5Bytes[i]) & 0xff;
            if (value < 16) {
                result.append("0");
            }
            result.append(Integer.toHexString(value));
        }
        return result.toString().substring(8, 24);
    }
    
    /**
     * 获取16位大写的MD5加密串
     * 
     * @param message 要加密的信息
     * @return 16位大写的MD5加密串
     */
    public static String get16UpperMD5(String message) {
        byte[] md5Bytes = getMD5Bytes(message);
        StringBuilder result = new StringBuilder(500);
        for (int i = 0; i < md5Bytes.length; i++) {
            int value = (int) md5Bytes[i];
            if (value < 0) {
                value += 256;
            }
            if (value < 16) {
                result.append("0");
            }
            result.append(Integer.toHexString(value));
        }
        return StringUtil.toUpperCase(result.toString().substring(8, 24));
    }
    
    /**
     * MD5加密
     * 
     * @param message 要加密的信息
     * @param encoding 字符编码
     * @return 存放哈希值结果的byte数组
     */
    public static byte[] getMD5Bytes(String message, String encoding) {
        try {
            return DigestUtils.md5(message.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            PlatformException pe = new PlatformException("MD5加密出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 获取32位小写的MD5加密串
     * 
     * @param message 要加密的信息
     * @param encoding 字符编码
     * @return 32位小写的MD5加密串
     */
    public static String get32LowerMD5(String message, String encoding) {
        try {
            return DigestUtils.md5Hex(message.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            PlatformException pe = new PlatformException("MD5加密出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 获取32位大写的MD5加密串
     * 
     * @param message 要加密的信息
     * @param encoding 字符编码
     * @return 32位大写的MD5加密串
     */
    public static String get32UpperMD5(String message, String encoding) {
        return StringUtil.toUpperCase(get32LowerMD5(message, encoding));
    }
    
    /**
     * 获取16位小写的MD5加密串
     * 
     * @param message 要加密的信息
     * @param encoding 字符编码
     * @return 16位小写的MD5加密串
     */
    public static String get16LowerMD5(String message, String encoding) {
        return get32LowerMD5(message, encoding).substring(8, 24);
    }
    
    /**
     * 获取16位大写的MD5加密串
     * 
     * @param message 要加密的信息
     * @param encoding 字符编码
     * @return 16位大写的MD5加密串
     */
    public static String get16UpperMD5(String message, String encoding) {
        return get32UpperMD5(message, encoding).substring(8, 24);
    }
    
    /**
     * 加密/解密算法（执行一次加密，执行两次解密）
     * 
     * @param message 要加密/解密的信息
     * @return 加密/解密后的信息
     */
    public static String convert(String message) {
        char[] value = message.toCharArray();
        for (int i = 0; i < value.length; i++) {
            value[i] = (char) (value[i] ^ 't');
        }
        return new String(value);
    }
}
