package com.minyazi.j2ee.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 应用监听器（初始化数据库连接池）
 * 
 * @author minyazi
 */
public class DataSourceInitApplicationListener implements ServletContextListener {
    public DataSourceInitApplicationListener() {}
    
    /**
     * 应用启动时调用
     */
    public void contextInitialized(ServletContextEvent sce) {
        /*
        // 初始化数据库连接池
        JdbcUtil.init();
        
        ServletContext application = sce.getServletContext();
        application.setAttribute("DS", JdbcUtil.getDataSource());
        */
    }
    
    /**
     * 应用关闭时调用
     */
    public void contextDestroyed(ServletContextEvent sce) {}
}
