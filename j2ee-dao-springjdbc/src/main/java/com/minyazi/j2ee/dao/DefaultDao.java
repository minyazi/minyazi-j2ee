package com.minyazi.j2ee.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.minyazi.j2ee.dao.springjdbc.CommonDaoImpl;

/**
 * Default DAO
 * 
 * @author minyazi
 */
public abstract class DefaultDao implements Dao {
    @Autowired
    protected CommonDaoImpl commonDao;
    
    public DefaultDao() {}
}
