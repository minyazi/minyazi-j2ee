package com.minyazi.j2ee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.minyazi.j2ee.dao.PagingDaoAdapter;
import com.minyazi.j2ee.dao.springjdbc.CommonDaoImpl;

/**
 * 默认DAO
 * 
 * @author minyazi
 */
public abstract class DefaultDao<T> extends PagingDaoAdapter<T> {
    protected JdbcTemplate jdbcTemplate;
    protected CommonDaoImpl commonDao;
    
    public DefaultDao() {}
    
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public CommonDaoImpl getCommonDao() {
        return commonDao;
    }
    
    @Autowired
    public void setCommonDao(CommonDaoImpl commonDao) {
        this.commonDao = commonDao;
    }
}
