package com.minyazi.j2ee.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 序列号工具类
 * 
 * @author minyazi
 */
public final class SerialUtil {
    private static final Map<Character, Character> PASSWORDBOOK; // 密码本
    private static final int[] FACTORS = {101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
        151, 157, 163, 167, 173, 179, 181, 191, 193, 197,
        199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
        263, 269, 271, 277, 281, 283, 293};// 校验因子
    private static final int MAXDAYS = 365; // 最大使用天数
    
    static {
        PASSWORDBOOK = new HashMap<Character, Character>();
        PASSWORDBOOK.put('0', 'H');
        PASSWORDBOOK.put('1', 'P');
        PASSWORDBOOK.put('2', 'U');
        PASSWORDBOOK.put('3', 'A');
        PASSWORDBOOK.put('4', 'K');
        PASSWORDBOOK.put('5', 'F');
        PASSWORDBOOK.put('6', 'R');
        PASSWORDBOOK.put('7', 'Z');
        PASSWORDBOOK.put('8', 'M');
        PASSWORDBOOK.put('9', 'G');
    }
    
    private SerialUtil() {}
    
    /**
     * 获取本地IP地址
     * 
     * @return 本地IP地址
     */
    public static String getLocalIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("获取本地IP地址失败：" + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * 获取最大使用天数
     * 
     * @return 最大使用天数
     */
    public static int getMaxdays() {
        return MAXDAYS;
    }
    
    /**
     * 转码
     * 
     * @param source 要转码的字符
     * @return 转码结果（如果返回空字符则表示转码失败）
     */
    private static Character encode(Character source) {
        if (source != null) {
            if ('0' <= source && source <= '9') {
                for (Character key : PASSWORDBOOK.keySet()) {
                    if (key == source) {
                        return PASSWORDBOOK.get(key);
                    }
                }
            } else if ('A' <= source && source <= 'Z') {
                for (Character key : PASSWORDBOOK.keySet()) {
                    if (PASSWORDBOOK.get(key) == source) {
                        return key;
                    }
                }
            }
        }
        return ' ';
    }
    
    /**
     * 转码
     * 
     * @param source 要转码的字符串
     * @return 转码结果（如果返回空字符串则表示转码失败）
     */
    static String encode(String source) {
        if (!StringUtil.isEmptyString(source)) {
            StringBuilder result = new StringBuilder(50);
            for (Character c : source.toCharArray()) {
                char _c = encode(c);
                if (_c == ' ') {
                    return "";
                } else {
                    result.append(_c);
                }
            }
            return result.toString();
        }
        return "";
    }
    
    /**
     * 解码
     * 
     * @param source 要解码的字符
     * @return 解码结果（如果返回空字符则表示解码失败）
     */
    private static Character decode(Character source) {
        return encode(source);
    }
    
    /**
     * 解码
     * 
     * @param source 要解码的字符串
     * @return 解码结果（如果返回空字符串则表示解码失败）
     */
    static String decode(String source) {
        if (!StringUtil.isEmptyString(source)) {
            StringBuilder result = new StringBuilder(50);
            for (Character c : source.toCharArray()) {
                char _c = decode(c);
                if (_c == ' ') {
                    return "";
                } else {
                    result.append(_c);
                }
            }
            return result.toString();
        }
        return "";
    }
    
    /**
     * 生成系统密钥
     * 
     * @return 系统密钥
     */
    public static String generateSystemKey() {
        String numberChars = "0123456789";
        StringBuilder result = new StringBuilder(50);
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            result.append(numberChars.charAt(random.nextInt(numberChars.length())));
            if (i == 2 || i == 5 || i == 8 || i == 11) {
                result.append(".");
            }
        }
        return result.toString();
    }
    
    /**
     * 生成序列号
     * <p>
     * 说明：使用天数等于999天时表示生成正式版序列号。
     * 
     * @param systemKey 系统密钥
     * @param trialDays 使用天数
     * @return 序列号
     */
    public static String generateSerial(String systemKey, String trialDays) {
        /*
         * 序列号：系统密钥（012.345.678.901.234），使用天数（7），随机5位字符（*****），校验码1（?），校验码2（?）
         * 
         * 系统密钥：012.345.678.901.234 -> 012345678901234 -> HPUAKFRZMGHPUAK
         * 使用天数：7 -> 007 -> HHZ
         * 随机5位字符：*****
         * 校验码1：?
         * 校验码2：?
         * 
         * HPUAKFRZMGHPUAK                  -> HPU[-]AKF[-]RZM[-]GHP[-]UAK
         * HPU-AKF-RZM-GHP-UAK + HHZ      -> HPU-A[H]KF-R[H]ZM-G[Z]HP-UAK
         * HPU-AHKF-RHZM-GZHP-UAK + *****  -> [*]HPU-[*]AHKF-[*]RHZM-[*]GZHP-[*]UAK
         * *HPU-*AHKF-*RHZM-*GZHP-*UAK + ? -> *H[?]PU-*AHKF-*RHZM-*GZHP-*UAK
         * *H?PU-*AHKF-*RHZM-*GZHP-*UAK + ? -> *H?PU-*AHKF-*RHZM-*GZHP-*U[?]AK
         * 
         * 序列号：*H?PU-*AHKF-*RHZM-*GZHP-*U?AK
         */
        // 系统密钥转码
        systemKey = encode(systemKey.replaceAll("\\.", ""));
        if (StringUtil.isEmptyString(systemKey) || systemKey.length() != 15) {
            System.out.println("【A】序列号生成失败");
            return "";
        }
        
        // 使用天数转码
        trialDays = encode(StringUtil.fill(trialDays, "3", 3));
        if (StringUtil.isEmptyString(trialDays) || trialDays.length() != 3) {
            System.out.println("【B】序列号生成失败");
            return "";
        }
        
        StringBuilder serial = new StringBuilder(50);
        serial.append(systemKey).insert(3, "-").insert(7, "-").insert(11, "-").insert(15, "-");
        serial.insert(5, trialDays.charAt(0)).insert(10, trialDays.charAt(1)).insert(15, trialDays.charAt(2));
        
        Random random = new Random();
        serial.insert(0, random.nextInt(10)).insert(5, random.nextInt(10)).insert(11, random.nextInt(10))
            .insert(17, random.nextInt(10)).insert(23, random.nextInt(10));
        
        // 校验码1
        int sum1 = 0;
        int i1 = 0;
        for (char c : serial.toString().toCharArray()) {
            if (c != '-') {
                sum1 += c * FACTORS[i1];
                i1++;
            }
        }
        serial.insert(2, sum1 % 10);
        
        // 校验码2
        int sum2 = 0;
        int i2 = 0;
        for (char c : serial.toString().toCharArray()) {
            if (c != '-') {
                sum2 += c * FACTORS[i2];
                i2++;
            }
        }
        serial.insert(26, sum2 % 10);
        
        return serial.toString();
    }
    
    /**
     * 校验序列号
     * 
     * @param systemKey 系统密钥
     * @param serial 序列号
     * @return true：有效的序列号，false：无效的序列号
     */
    public static boolean checkSerial(String systemKey, String serial) {
        if (StringUtil.isEmptyString(serial) || serial.length() != 29) {
            System.out.println("【A】无效的序列号");
            return false;
        } else {
            // 校验校验码2
            String checkCode2 = serial.substring(26, 27); // 校验码2
            for (char c : checkCode2.toCharArray()) {
                if (c < '0' || c > '9') {
                    System.out.println("【B】无效的序列号");
                    return false;
                }
            }
            String temp = serial.substring(0, 26) + serial.substring(27);
            int sum2 = 0;
            int i2 = 0;
            for (char c : temp.toCharArray()) {
                if (c != '-') {
                    sum2 += c * FACTORS[i2];
                    i2++;
                }
            }
            if ((sum2 % 10) != Integer.parseInt(checkCode2)) {
                System.out.println("【C】无效的序列号");
                return false;
            }
            
            // 校验校验码1
            String checkCode1 = temp.substring(2, 3); // 校验码1
            for (char c : checkCode1.toCharArray()) {
                if (c < '0' || c > '9') {
                    System.out.println("【D】无效的序列号");
                    return false;
                }
            }
            temp = temp.substring(0, 2) + temp.substring(3);
            int sum1 = 0;
            int i1 = 0;
            for (char c : temp.toCharArray()) {
                if (c != '-') {
                    sum1 += c * FACTORS[i1];
                    i1++;
                }
            }
            if ((sum1 % 10) != Integer.parseInt(checkCode1)) {
                System.out.println("【E】无效的序列号");
                return false;
            }
            
            // 校验使用天数
            String trialDays = getTrialDays(serial); // 使用天数
            for (char c : trialDays.toCharArray()) {
                if (c < '0' || c > '9') {
                    System.out.println("【F】无效的序列号");
                    return false;
                }
            }
            if (Integer.parseInt(trialDays) > MAXDAYS && Integer.parseInt(trialDays) != 999) {
                // 试用版序列号的使用天数不能超过最大使用天数
                System.out.println("【G】无效的序列号");
                return false;
            }
            
            // 校验系统密钥
            String _systemKey = decode(serial.substring(1, 2) + serial.substring(3, 5)) + "."
                    + decode(serial.substring(7, 8) + serial.substring(9, 11)) + "."
                    + decode(serial.substring(13, 14) + serial.substring(15, 17)) + "."
                    + decode(serial.substring(19, 20) + serial.substring(21, 23)) + "."
                    + decode(serial.substring(25, 26) + serial.substring(27, 29)); // 系统密钥
            if (!_systemKey.equals(systemKey)) {
                System.out.println("【H】无效的序列号");
                return false;
            }
            
            return true;
        }
    }
    
    /**
     * 获取使用天数
     * 
     * @param serial 序列号
     * @return 使用天数
     */
    public static String getTrialDays(String serial) {
        return StringUtil.trim(decode(serial.substring(8, 9) + serial.substring(14, 15) + serial.substring(20, 21)), "0", '0');
    }
    
    public static void main(String[] args) {
        String systemKey = SerialUtil.generateSystemKey();
        System.out.println("系统密钥：" + systemKey);
        String trialDays = "7";
        System.out.println("使用天数：" + trialDays);
        String serial = SerialUtil.generateSerial(systemKey, trialDays);
        System.out.println("序列号：" + serial);
        boolean result = SerialUtil.checkSerial(systemKey, serial);
        System.out.println("校验结果：" + result);
        if (result) {
            if ("999".equals(SerialUtil.getTrialDays(serial))) {
                System.out.println("正式版序列号");
            } else {
                System.out.println("试用版序列号，使用天数：" + SerialUtil.getTrialDays(serial));
            }
        }
    }
}
