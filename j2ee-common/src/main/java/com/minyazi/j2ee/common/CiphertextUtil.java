package com.minyazi.j2ee.common;

/**
 * 加解密工具类
 * 
 * @author minyazi
 */
public class CiphertextUtil {
    /**
     * 加密
     * 
     * @param source 要加密的字符串
     * @return 加密结果（如果返回空字符串则表示加密失败）
     */
    public static String encrypt(String source) {
        if (StringUtil.isEmptyString(source)) {
            System.out.println("【A】加密失败");
            return "";
        } else {
            for (char c : source.toCharArray()) {
                if (c < 10 || c > 99) {
                    System.out.println("【B】加密失败");
                    return "";
                }
            }
            
            byte[] temp = source.getBytes();
            StringBuilder result = new StringBuilder(500);
            for (byte b : temp) {
                result.append(SerialUtil.encode(b + ""));
            }
            return result.toString();
        }
    }
    
    /**
     * 解密
     * 
     * @param source 要解密的字符串
     * @return 解密结果（如果返回空字符串则表示解密失败）
     */
    public static String decrypt(String source) {
        if (StringUtil.isEmptyString(source)) {
            System.out.println("【A】解密失败");
            return "";
        } else {
            String temp = SerialUtil.decode(source);
            if (StringUtil.isEmptyString(temp) || temp.length() % 2 != 0) {
                System.out.println("【B】解密失败");
                return "";
            }
            for (char c : temp.toCharArray()) {
                if (c < '0' || c > '9') {
                    System.out.println("【C】解密失败");
                    return "";
                }
            }
            
            StringBuilder result = new StringBuilder(500);
            while (!StringUtil.isEmptyString(temp)) {
                char c = (char) Integer.parseInt(temp.substring(0, 2));
                result.append(c);
                temp = temp.substring(2);
            }
            return result.toString();
        }
    }
    
    public static void main(String[] args) {
        String ciphertext = CiphertextUtil.encrypt("777.081.112.244.534,7");
        System.out.println("加密：" + ciphertext);
        System.out.println("解密：" + CiphertextUtil.decrypt(ciphertext));
    }
}
