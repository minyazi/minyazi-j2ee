package com.minyazi.j2ee.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.minyazi.j2ee.core.PlatformException;

/**
 * GZIP压缩/解压缩工具类
 * 
 * @author minyazi
 */
public final class GZipUtil {
    private GZipUtil() {}
    
    /**
     * 压缩字节数组
     * 
     * @param sourceBytes 要压缩的字节数组
     * @return 压缩后的字节数组
     */
    public static byte[] gzip(byte[] sourceBytes) {
        ByteArrayOutputStream out = null;
        GZIPOutputStream gzipout = null;
        try {
            out = new ByteArrayOutputStream(sourceBytes.length);
            gzipout = new GZIPOutputStream(out);
            gzipout.write(sourceBytes, 0, sourceBytes.length);
            gzipout.finish();
            gzipout.flush();
            out.flush();
            byte[] targetBytes = out.toByteArray();
            return targetBytes;
        } catch (IOException e) {
            throw new PlatformException("压缩字节数组出错：" + e.getMessage(), e);
        } finally {
            try {
                if (gzipout != null) {
                    gzipout.close();
                    gzipout = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                throw new PlatformException("压缩字节数组出错：" + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 解压缩字节数组
     * 
     * @param sourceBytes 要解压缩的字节数组
     * @return 解压缩后的字节数组
     */
    public static byte[] unGzip(byte[] sourceBytes) {
        GZIPInputStream gzipin = null;
        ByteArrayOutputStream out = null;
        try {
            gzipin = new GZIPInputStream(new ByteArrayInputStream(sourceBytes));
            out = new ByteArrayOutputStream(sourceBytes.length);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = gzipin.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            out.flush();
            byte[] targetBytes = out.toByteArray();
            return targetBytes;
        } catch (IOException e) {
            throw new PlatformException("解压缩字节数组出错：" + e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (gzipin != null) {
                    gzipin.close();
                    gzipin = null;
                }
                
            } catch (IOException e) {
                throw new PlatformException("解压缩字节数组出错：" + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 压缩文件
     * 
     * @param sourceFile 要压缩的文件
     * @param targetFile 压缩后的文件
     */
    public static void gzip(String sourceFile, String targetFile) {
        FileInputStream in = null;
        GZIPOutputStream gzipout = null;
        try {
            in = new FileInputStream(sourceFile);
            gzipout = new GZIPOutputStream(new FileOutputStream(targetFile));
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                gzipout.write(buffer, 0, length);
            }
            gzipout.finish();
            gzipout.flush();
        } catch (IOException e) {
            throw new PlatformException("压缩文件出错：" + e.getMessage(), e);
        } finally {
            try {
                if (gzipout != null) {
                    gzipout.close();
                    gzipout = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                throw new PlatformException("压缩文件出错：" + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 解压缩文件
     * 
     * @param sourceFile 要解压缩的文件
     * @param targetFile 解压缩后的文件
     */
    public static void unGzip(String sourceFile, String targetFile) {
        GZIPInputStream gzipin = null;
        FileOutputStream out = null;
        try {
            gzipin = new GZIPInputStream(new FileInputStream(sourceFile));
            out = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = gzipin.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } catch (IOException e) {
            throw new PlatformException("解压缩文件出错：" + e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (gzipin != null) {
                    gzipin.close();
                    gzipin = null;
                }
                
            } catch (IOException e) {
                throw new PlatformException("解压缩文件出错：" + e.getMessage(), e);
            }
        }
    }
}
