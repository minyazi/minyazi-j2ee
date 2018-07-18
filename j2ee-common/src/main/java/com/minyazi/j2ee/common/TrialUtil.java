package com.minyazi.j2ee.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 试用期控制工具类
 * 
 * @author minyazi
 */
public class TrialUtil {
    private static final String OFFICIALVERSION = "OFFICIALVERSION"; // 正式版
    private static final String TRIALFILEPATH = "/etc/"; // 试用数据保存路径
    private static final String TRIALFILENAME = "J2EE.TF"; // 试用数据保存文件名
    
    /**
     * 检查试用数据是否正确
     * 
     * @param trialData 试用数据
     * @return true：正确，false：不正确
     */
    public static boolean checkTrialData(String trialData) {
        if (StringUtil.isEmptyString(trialData)) {
            System.out.println("【A】试用数据不正确");
            return false;
        } else {
            String[] temp = CiphertextUtil.decrypt(trialData).split(",");
            if ((temp.length < 2 || temp.length > 3)
                    || (StringUtil.isEmptyString(temp[0]) || temp[0].length() != 19)
                    || StringUtil.isEmptyString(temp[1])) {
                System.out.println("【B】试用数据不正确");
                return false;
            } else {
                String systemKey = temp[0]; // 系统密钥
                int i = 0;
                for (char c : systemKey.toCharArray()) {
                    if (i == 3 || i == 7 || i == 11 || i == 15) {
                        if (c != '.') {
                            System.out.println("【C】试用数据不正确");
                            return false;
                        }
                    } else {
                        if (c < '0' || c > '9') {
                            System.out.println("【D】试用数据不正确");
                            return false;
                        }
                    }
                    i++;
                }
                
                String trialDays = temp[1]; // 试用天数
                if (!OFFICIALVERSION.equals(trialDays)) {
                    for (char c : trialDays.toCharArray()) {
                        if (c < '0' || c > '9') {
                            System.out.println("【E】试用数据不正确");
                            return false;
                        }
                    }
                    
                    if (temp.length != 3 || StringUtil.isEmptyString(temp[2]) || temp[2].length() != 8) {
                        System.out.println("【F】试用数据不正确");
                        return false;
                    } else {
                        String trialDate = temp[2]; // 试用日期
                        for (char c : trialDate.toCharArray()) {
                            if (c < '0' || c > '9') {
                                System.out.println("【G】试用数据不正确");
                                return false;
                            }
                        }
                    }
                }
                
                return true;
            }
        }
    }
    
    /**
     * 获取系统密钥
     * 
     * @param trialData 试用数据
     * @return 系统密钥
     */
    public static String getSystemKey(String trialData) {
        if (checkTrialData(trialData)) {
            return CiphertextUtil.decrypt(trialData).split(",")[0];
        } else {
            return "";
        }
    }
    
    /**
     * 获取试用天数
     * 
     * @param trialData 试用数据
     * @return 试用天数
     */
    public static String getTrialDays(String trialData) {
        if (checkTrialData(trialData)) {
            return CiphertextUtil.decrypt(trialData).split(",")[1];
        } else {
            return "";
        }
    }
    
    /**
     * 获取试用日期
     * 
     * @param trialData 试用数据
     * @return 试用日期
     */
    public static String getTrialDate(String trialData) {
        if (checkTrialData(trialData)) {
            if (!OFFICIALVERSION.equals(getTrialDays(trialData))) {
                return CiphertextUtil.decrypt(trialData).split(",")[2];
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
    
    /**
     * 获取试用数据
     * 
     * @return 试用数据
     */
    public static String getTrialData() {
        BufferedReader reader = null;
        try {
            String targetFile = TRIALFILEPATH + TRIALFILENAME;
            File file = new File(targetFile);
            if (!file.exists()) {
                return "";
            }
            
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String fileContent = reader.readLine();
            
            return StringUtil.formatNullString(fileContent);
        } catch (Exception e) {
            System.out.println("【A】获取试用数据失败：" + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (Exception e) {
                System.out.println("【B】获取试用数据失败：" + e.getMessage());
            }
        }
        
        return "";
    }
    
    /**
     * 保存试用数据
     * <p>
     * 格式：系统密钥（19位，如：123.123.123.123.123）+英文逗号（,）+试用天数（最多3位，如：7）+英文逗号（,）+试用日期（8位，如：20070101）。
     * 
     * @param trialData 试用数据
     */
    public static void saveTrialData(String trialData) {
        BufferedWriter writer = null;
        try {
            String targetFile = TRIALFILEPATH + TRIALFILENAME;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            writer.write(trialData);
            writer.flush();
        } catch (Exception e) {
            System.out.println("【A】保存试用数据失败：" + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            } catch (Exception e) {
                System.out.println("【B】保存试用数据失败：" + e.getMessage());
            }
        }
    }
    
    /**
     * 获取系统试用状态
     * 
     * @return 系统试用状态（0：正式版，1：已过试用期，2：未过试用期）
     */
    public static int getSystemTrialStatus() {
        String trialData = getTrialData(); // 试用数据（系统密钥,试用天数,试用日期）
        String trialDays = getTrialDays(trialData); // 试用天数
        if (StringUtil.isEmptyString(trialDays)) {
            return 1; // 已过试用期
        } else {
            if (trialDays.equals(OFFICIALVERSION)) {
                return 0; // 正式版
            } else if (1 <= Integer.parseInt(trialDays) && Integer.parseInt(trialDays) <= SerialUtil.getMaxdays()) {
                return 2; // 未过试用期
            } else {
                return 1; // 已过试用期
            }
        }
    }
    
    /**
     * 激活系统
     * 
     * @param serial 序列号
     * @return 状态码（0：激活成功，1：无效的序列号，2：无效的试用数据）
     */
    public static int activateSystem(String serial) {
        System.out.println("序列号 = [" + serial + "]");
        
        // 检查序列号是否有效
        int flag = 0; // 无效的序列号
        String trialDays = "0"; // 试用天数
        String date = DateUtil.getDate();
        if (!StringUtil.isEmptyString(serial) && serial.length() == 29) {
            String trialData = getTrialData(); // 试用数据（系统密钥,试用天数,试用日期）
            if (!checkTrialData(trialData)) {
                String systemKey = SerialUtil.generateSystemKey(); // 系统密钥
                trialDays = "0"; // 试用天数
                
                trialData = CiphertextUtil.encrypt(systemKey + "," + trialDays + "," + date);
                saveTrialData(trialData);
                
                System.out.println("无效的试用数据");
                return 2;
            } else {
                String systemKey = getSystemKey(trialData); // 系统密钥
                if (SerialUtil.checkSerial(systemKey, serial)) {
                    trialDays = SerialUtil.getTrialDays(serial);
                    if ("999".equals(trialDays)) {
                        flag = 1; // 正式版序列号
                        trialDays = OFFICIALVERSION;
                    } else {
                        flag = -1; // 试用版序列号
                    }
                }
            }
        }
        
        if (flag == 0) {
            System.out.println("无效的序列号");
            return 1;
        } else {
            String systemKey = SerialUtil.generateSystemKey(); // 系统密钥
            
            String trialData = CiphertextUtil.encrypt(systemKey + "," + trialDays + "," + date);
            saveTrialData(trialData);
            
            if (flag == -1) {
                System.out.println("试用版激活成功");
            } else {
                System.out.println("正式版激活成功");
            }
            return 0;
        }
    }
    
    /**
     * 每日定时任务：修改试用天数
     */
    public static void modifyTrialDays() {
        if (getSystemTrialStatus() == 2) { // 未过试用期
            String date = DateUtil.getDate();
            String trialData = getTrialData(); // 试用数据（系统密钥,试用天数,试用日期）
            String trialDate = getTrialDate(trialData); // 试用日期
            
            if (!trialDate.equals(date)) {
                String systemKey = getSystemKey(trialData); // 系统密钥
                String trialDays = getTrialDays(trialData); // 试用天数
                
                trialData = CiphertextUtil.encrypt(systemKey + "," + (Integer.parseInt(trialDays) - 1) + "," + date);
                
                saveTrialData(trialData);
            }
        }
    }
    
    /**
     * 重置系统
     * 
     * @param serial 序列号
     * @return 状态码（0：重置成功，1：无效的序列号，2：无效的试用数据）
     */
    public static int resetSystem(String serial) {
        System.out.println("序列号 = [" + serial + "]");
        
        // 检查序列号是否有效
        int flag = 0; // 无效的序列号
        if (!StringUtil.isEmptyString(serial) && serial.length() == 29) {
            String trialData = getTrialData(); // 试用数据（系统密钥,试用天数,试用日期）
            if (!checkTrialData(trialData)) {
                String systemKey = SerialUtil.generateSystemKey(); // 系统密钥
                String trialDays = "0"; // 试用天数
                String trialDate = DateUtil.getDate(); // 试用日期
                trialData = CiphertextUtil.encrypt(systemKey + "," + trialDays + "," + trialDate);
                saveTrialData(trialData);
                System.out.println("无效的试用数据");
                return 2;
            } else {
                String systemKey = getSystemKey(trialData); // 系统密钥
                if (SerialUtil.checkSerial(systemKey, serial)) {
                    flag = 1; // 有效的序列号
                }
            }
        }
        
        if (flag == 0) {
            System.out.println("无效的序列号");
            return 1;
        } else {
            String systemKey = SerialUtil.generateSystemKey(); // 系统密钥
            String trialDays = "0"; // 试用天数
            String trialDate = DateUtil.getDate(); // 试用日期
            String trialData = CiphertextUtil.encrypt(systemKey + "," + trialDays + "," + trialDate);
            saveTrialData(trialData);
            System.out.println("系统重置成功");
            return 0;
        }
    }
    
    public static void main(String[] args) {
        TrialUtil.saveTrialData("GPAYBANK");
        System.out.println("试用数据：" + TrialUtil.getTrialData());
    }
}
