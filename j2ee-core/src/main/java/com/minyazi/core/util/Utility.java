package com.minyazi.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.minyazi.core.PlatformException;
import com.minyazi.core.util.filter.StringFilterFactory;

/**
 * 工具类
 * 
 * @author minyazi
 */
public final class Utility {
    private Utility() {}
    
    /**
     * 获取系统的classpath路径
     * 
     * @return 系统的classpath路径
     */
    public static String getClassPath() {
        return Utility.class.getResource("/").getPath();
    }
    
    /**
     * 根据元素的自然顺序对指定迭代器按升序进行排序
     * 
     * @param <E> 元素类型
     * @param iterator 要排序的迭代器
     * @return 升序排列的迭代器
     */
    public static <E> Iterator<E> sort(Iterator<E> iterator) {
        Set<E> set = new TreeSet<E>();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        return set.iterator();
    }
    
    /**
     * 在单独的进程中执行指定的字符串命令
     * 
     * @param command 要执行的系统命令
     * @param isHTML 是否以HTML的格式返回执行结果信息（true：是，false：否）
     * @return 命令执行的输出信息和错误信息（String[]{进程输出信息, 进程错误信息}）
     */
    public static String[] exec(String command, boolean isHTML) {
        try {
            LogUtil.debug("执行命令：{}", command);
            Process process = Runtime.getRuntime().exec(command);
            
            // 获取进程的输入流
            BufferedReader brI = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder resultI = new StringBuilder(500); // 进程输出信息
            String strI = null;
            while ((strI = brI.readLine()) != null) {
                LogUtil.debug("进程输出信息：{}", strI);
                if (isHTML) {
                    strI = StringFilterFactory.getInstance().getStringFilterChain("html").filter(strI);
                    resultI.append(strI).append("<br>");
                } else {
                    resultI.append(strI).append("\r\n");
                }
            }
            
            // 获取进程的错误流
            BufferedReader brE = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder resultE = new StringBuilder(500); // 进程错误信息
            String strE = null;
            while ((strE = brE.readLine()) != null) {
                LogUtil.debug("进程错误信息：{}", strE);
                if (isHTML) {
                    strE = StringFilterFactory.getInstance().getStringFilterChain("html").filter(strE);
                    resultE.append(strE).append("<br>");
                } else {
                    resultE.append(strE).append("\r\n");
                }
            }
            
            String[] result = new String[2];
            result[0] = resultI.toString();
            result[1] = resultE.toString();
            
            process.waitFor();
            process.destroy();
            
            return result;
        } catch (IOException e) {
            PlatformException pe = new PlatformException("命令执行出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        } catch (InterruptedException e) {
            PlatformException pe = new PlatformException("命令执行出错：" + e.getMessage(), e);
            LogUtil.exception(pe);
            throw pe;
        }
    }
}
