package com.minyazi.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.minyazi.core.PlatformException;
import com.minyazi.core.util.filter.StringFilterFactory;

/**
 * 文件工具类
 * 
 * @author minyazi
 */
public final class FileUtil {
    private FileUtil() {}
    
    /**
     * 保存文件
     * 
     * @param fileText 要保存的文件文本
     * @param filePath 保存路径
     * @param fileName 保存文件名
     * @param encoding 字符编码
     */
    public static void saveFile(String fileText, String filePath, String fileName, String encoding) {
        try {
            String path = filePath + "/" + fileName;
            BufferedWriter bw = null;
            if (StringUtil.isEmptyString(encoding)) {
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
            } else {
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), encoding));
            }
            bw.write(fileText);
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            PlatformException pe = new PlatformException("保存文件出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (IOException e) {
            PlatformException pe = new PlatformException("保存文件出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 保存文件
     * 
     * @param file 要保存的文件
     * @param filePath 保存路径
     * @param fileName 保存文件名
     */
    public static void saveFile(File file, String filePath, String fileName) {
        try {
            String path = filePath + "/" + fileName;
            FileOutputStream out = new FileOutputStream(path);
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            PlatformException pe = new PlatformException("保存文件出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (IOException e) {
            PlatformException pe = new PlatformException("保存文件出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 删除文件
     * 
     * @param filePath 要删除的文件的所在路径
     * @param fileName 要删除的文件的文件名
     */
    public static void deleteFile(String filePath, String fileName) {
        String path = filePath + "/" + fileName;
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.delete()) {
            PlatformException pe = new PlatformException("删除文件出错");
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 读取文件内容
     * 
     * @param file 要读取的文件
     * @param encoding 字符编码
     * @param isHTML 是否以HTML的格式读取文件内容（true：是，false：否）
     * @param addCRLF 行尾是否添加回车换行符（true：是，false：否）
     * @return 读取的文件内容
     */
    public static String readFile(File file, String encoding, boolean isHTML, boolean addCRLF) {
        try {
            if (!file.exists()) {
                return "";
            }
            BufferedReader reader = null;
            if (StringUtil.isEmptyString(encoding)) {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } else {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            }
            StringBuilder fileContent = new StringBuilder(500); // 文件内容
            String buffer = null;
            while ((buffer = reader.readLine()) != null) {
                if (isHTML) {
                    buffer = StringFilterFactory.getInstance().getStringFilterChain("html").filter(buffer);
                    fileContent.append(buffer);
                    if (addCRLF) {
                        fileContent.append("<br>");
                    }
                } else {
                    fileContent.append(buffer);
                    if (addCRLF) {
                        fileContent.append("\r\n");
                    }
                }
            }
            reader.close();
            return fileContent.toString();
        } catch (FileNotFoundException e) {
            PlatformException pe = new PlatformException("读取文件内容出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (UnsupportedEncodingException e) {
            PlatformException pe = new PlatformException("读取文件内容出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (IOException e) {
            PlatformException pe = new PlatformException("读取文件内容出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
    
    /**
     * 读取文件内容
     * 
     * @param filePath 要读取的文件的所在路径
     * @param fileName 要读取的文件的文件名
     * @param encoding 字符编码
     * @param isHTML 是否以HTML的格式读取文件内容（true：是，false：否）
     * @param addCRLF 行尾是否添加回车换行符（true：是，false：否）
     * @return 读取的文件内容
     */
    public static String readFile(String filePath, String fileName, String encoding, boolean isHTML, boolean addCRLF) {
        String path = filePath + "/" + fileName;
        File file = new File(path);
        return readFile(file, encoding, isHTML, addCRLF);
    }
}
