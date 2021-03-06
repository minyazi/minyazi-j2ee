package com.minyazi.j2ee.dao.hibernate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.minyazi.j2ee.dao.PagingDaoAdapter;

/**
 * 默认DAO
 * 
 * @author minyazi
 */
public class DefaultDao<T> extends PagingDaoAdapter<T> {
    private HibernateTemplate hibernateTemplate;
    private CommonDaoImpl commonDao;
    
    public DefaultDao() {}
    
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    
    @Required
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
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
