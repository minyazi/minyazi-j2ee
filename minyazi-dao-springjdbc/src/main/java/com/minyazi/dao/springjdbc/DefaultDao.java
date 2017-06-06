package com.minyazi.dao.springjdbc;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.minyazi.dao.BaseDao;

/**
 * 默认DAO
 * 
 * @author minyazi
 */
public class DefaultDao extends BaseDao {
    private CommonDaoImpl commonDao;
    
    public DefaultDao() {}
    
    public CommonDaoImpl getCommonDao() {
        return commonDao;
    }
    
    @Required
    @Resource
    public void setCommonDao(CommonDaoImpl commonDao) {
        this.commonDao = commonDao;
    }
}
