package com.minyazi.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * 
 * @author minyazi
 */
public final class LogUtil {
    private static final Logger BASE_LOGGER = LoggerFactory.getLogger(LogUtil.class);
    
    private LogUtil() {}
    
    /**
     * 获取日志记录器
     * 
     * @return 日志记录器
     */
    public static Logger getLogger() {
        return BASE_LOGGER;
    }
    
    /**
     * 获取日志记录器
     * 
     * @param clazz 日志记录器的命名Class对象
     * @return 日志记录器
     */
    public static Logger getLogger(final Class<?> clazz) {
        if (clazz == null || StringUtil.isEmptyString(clazz.getName())) {
            return BASE_LOGGER;
        } else {
            return LoggerFactory.getLogger(clazz);
        }
    }
    
    /**
     * 获取日志记录器
     * 
     * @param name 日志记录器的名称
     * @return 日志记录器
     */
    public static Logger getLogger(final String name) {
        if (StringUtil.isEmptyString(name)) {
            return BASE_LOGGER;
        } else {
            return LoggerFactory.getLogger(name);
        }
    }
    
    /**
     * 记录TRACE日志
     * 
     * @param message 日志信息
     * @param params 占位符参数
     */
    public static void trace(String message, Object... params) {
        BASE_LOGGER.trace(message, params);
    }
    
    /**
     * 记录DEBUG日志
     * 
     * @param message 日志信息
     * @param params 占位符参数
     */
    public static void debug(String message, Object... params) {
        BASE_LOGGER.debug(message, params);
    }
    
    /**
     * 记录INFO日志
     * 
     * @param message 日志信息
     * @param params 占位符参数
     */
    public static void info(String message, Object... params) {
        BASE_LOGGER.info(message, params);
    }
    
    /**
     * 记录WARN日志
     * 
     * @param message 日志信息
     * @param params 占位符参数
     */
    public static void warn(String message, Object... params) {
        BASE_LOGGER.info(message, params);
    }
    
    /**
     * 记录ERROR日志
     * 
     * @param message 日志信息
     * @param params 占位符参数
     */
    public static void error(String message, Object... params) {
        BASE_LOGGER.error(message, params);
    }
    
    /**
     * 记录异常日志
     * 
     * @param cause 异常
     */
    public static void exception(Throwable cause) {
        BASE_LOGGER.error(cause.getMessage(), cause);
    }
}
