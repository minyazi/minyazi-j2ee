package com.minyazi.j2ee.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.minyazi.j2ee.dao.DaoBeansConfig;

/**
 * Service Bean配置
 * 
 * @author minyazi
 */
@Configuration
@Import(DaoBeansConfig.class)
@ComponentScan
public class ServiceBeansConfig {
    // 数据源事务管理器
    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    
    
}
