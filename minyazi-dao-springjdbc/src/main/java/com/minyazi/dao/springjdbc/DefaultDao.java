package com.minyazi.dao.springjdbc;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

import com.minyazi.dao.PagingDaoAdapter;

/**
 * 默认DAO
 * 
 * @author minyazi
 */
public class DefaultDao extends PagingDaoAdapter {
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
