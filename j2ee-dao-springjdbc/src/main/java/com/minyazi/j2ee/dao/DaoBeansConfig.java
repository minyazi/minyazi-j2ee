package com.minyazi.j2ee.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * DAO Bean配置
 * 
 * @author minyazi
 */
@Configuration
@ComponentScan
public class DaoBeansConfig {
    @Bean(name="dataSource", destroyMethod="close")
    public DataSource dataSource() {
        return new ComboPooledDataSource("testdb");
    }
    
    @Bean(name="jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
