package com.minyazi.j2ee.dao.springjdbc;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

import com.minyazi.j2ee.dao.PagingDaoAdapter;

/**
 * 默认DAO
 * 
 * @author minyazi
 */
public class DefaultDao<T> extends PagingDaoAdapter<T> {
    private JdbcTemplate jdbcTemplate;
    private CommonDaoImpl commonDao;
    
    public DefaultDao() {}
    
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    @Required
    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public CommonDaoImpl getCommonDao() {
        return commonDao;
    }
    
    @Required
    @Resource
    public void setCommonDao(CommonDaoImpl commonDao) {
        this.commonDao = commonDao;
    }
}
