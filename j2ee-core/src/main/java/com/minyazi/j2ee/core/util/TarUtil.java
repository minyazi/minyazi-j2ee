package com.minyazi.j2ee.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import com.minyazi.j2ee.core.PlatformException;

/**
 * Tar归档工具类
 * 
 * @author minyazi
 */
public final class TarUtil {
    private TarUtil() {}
    
    /**
     * 归档
     * 
     * @param source 要归档的文件或目录
     */
    public static void archive(String source) {
        archive(new File(source));
    }
    
    /**
     * 归档
     * 
     * @param source 要归档的文件或目录
     * @param targetFile 归档后的文件
     */
    public static void archive(String source, String targetFile) {
        archive(new File(source), targetFile);
    }
    
    /**
     * 归档
     * 
     * @param source 要归档的文件或目录
     */
    public static void archive(File source) {
        String targetFile = source.getPath() + ".tar";
        archive(source, targetFile);
    }
    
    /**
     * 归档
     * 
     * @param source 要归档的文件或目录
     * @param targetFile 归档后的文件
     */
    public static void archive(File source, String targetFile) {
        archive(source, new File(targetFile));
    }
    
    /**
     * 归档
     * 
     * @param source 要归档的文件或目录
     * @param targetFile 归档后的文件
     */
    public static void archive(File source, File targetFile) {
        TarArchiveOutputStream tar = null;
        try {
            tar = new TarArchiveOutputStream(new FileOutputStream(targetFile));
            archive(source, tar, "");
            tar.finish();
            tar.flush();
        } catch (IOException e) {
            throw new PlatformException("归档文件或目录出错：" + e.getMessage(), e);
        } finally {
            try {
                if (tar != null) {
                    tar.close();
                    tar = null;
                }
            } catch (IOException e) {
                throw new PlatformException("归档文件或目录出错：" + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 归档
     * 
     * @param source 要归档的文件或目录
     * @param tar Tar归档输出流
     * @param basePath 归档包内相对路径
     * @throws IOException
     */
    private static void archive(File source, TarArchiveOutputStream tar, String basePath) throws IOException  {
        if (source.isDirectory()) {
            archiveDirectory(source, tar, basePath);
        } else {
            archiveFile(source, tar, basePath);
        }
    }
    
    /**
     * 归档目录
     * 
     * @param directory 要归档的目录
     * @param tar Tar归档输出流
     * @param basePath 归档包内相对路径
     * @throws IOException
     */
    private static void archiveDirectory(File directory, TarArchiveOutputStream tar, String basePath) throws IOException {
        File[] files = directory.listFiles();
        
        if (files.length < 1) {
            TarArchiveEntry entry = new TarArchiveEntry(basePath + directory.getName() + File.separator);
            tar.putArchiveEntry(entry);
            tar.closeArchiveEntry();
        }
        
        for (File file : files) {
            // 递归归档
            archive(file, tar, basePath + directory.getName() + File.separator);
        }
    }
    
    /**
     * 归档文件
     * 
     * @param file 要归档的文件
     * @param tar Tar归档输出流
     * @param basePath 归档包内相对路径
     * @throws IOException
     */
    private static void archiveFile(File file, TarArchiveOutputStream tar, String basePath) throws IOException {
        TarArchiveEntry entry = new TarArchiveEntry(basePath + file.getName());
        entry.setSize(file.length());
        tar.putArchiveEntry(entry);
        
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = in.read(buffer, 0, 1024)) != -1) {
                tar.write(buffer, 0, count);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                throw e;
            }
        }
        
        tar.closeArchiveEntry();
    }
    
    /**
     * 解归档
     * 
     * @param sourceFile 要解归档的文件
     */
    public static void unArchive(String sourceFile) {
        unArchive(new File(sourceFile));
    }
    
    /**
     * 解归档
     * 
     * @param sourceFile 要解归档的文件
     * @param targetPath 解归档目标路径
     */
    public static void unArchive(String sourceFile, String targetPath) {
        unArchive(new File(sourceFile), targetPath);
    }
    
    /**
     * 解归档
     * 
     * @param sourceFile 要解归档的文件
     */
    public static void unArchive(File sourceFile) {
        String targetPath = sourceFile.getParent();
        unArchive(sourceFile, targetPath);
    }
    
    /**
     * 解归档
     * 
     * @param sourceFile 要解归档的文件
     * @param targetPath 解归档目标路径
     */
    public static void unArchive(File sourceFile, String targetPath) {
        unArchive(sourceFile, new File(targetPath));
    }
    
    /**
     * 解归档
     * 
     * @param sourceFile 要解归档的文件
     * @param targetPath 解归档目标路径
     */
    public static void unArchive(File sourceFile, File targetPath) {
        TarArchiveInputStream tar = null;
        try {
            tar = new TarArchiveInputStream(new FileInputStream(sourceFile));
            unArchive(targetPath, tar);
        } catch (IOException e) {
            throw new PlatformException("解归档文件出错：" + e.getMessage(), e);
        } finally {
            try {
                if (tar != null) {
                    tar.close();
                    tar = null;
                }
                
            } catch (IOException e) {
                throw new PlatformException("解归档文件出错：" + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 解归档
     * 
     * @param targetPath 解归档目标路径
     * @param tar Tar归档输入流
     * @throws IOException
     */
    private static void unArchive(File targetPath, TarArchiveInputStream tar) throws IOException {
        TarArchiveEntry entry = null;
        while ((entry = tar.getNextTarEntry()) != null) {
            File targetFile = new File(targetPath.getPath() + File.separator + entry.getName());
            
            fileProber(targetFile);
            
            if (entry.isDirectory()) {
                targetFile.mkdirs();
            } else {
                unArchiveFile(targetFile, tar);
            }
        }
    }
    
    /**
     * 解归档
     * 
     * @param targetFile 解归档目标文件
     * @param tar Tar归档输入流
     * @throws IOException
     */
    private static void unArchiveFile(File targetFile, TarArchiveInputStream tar) throws IOException {
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = tar.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, count);
            }
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }
    
    /**
     * 文件探测器
     * 
     * @param targetFile 目标文件
     */
    private static void fileProber(File targetFile) {
        File parentFile = targetFile.getParentFile();
        if (!parentFile.exists()) {
            // 递归寻找上级目录
            fileProber(parentFile);
            parentFile.mkdir();
        }
    }
}
