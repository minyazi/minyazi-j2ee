package com.minyazi.j2ee.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.minyazi.j2ee.dao.springjdbc.CommonDaoImpl;

/**
 * Abstract DAO
 * 
 * @author minyazi
 */
public abstract class AbstractDao implements Dao {
    @Autowired
    protected CommonDaoImpl commonDao;
    
    public AbstractDao() {}
}
