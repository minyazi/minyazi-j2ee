package com.minyazi.j2ee.dao;

import java.util.List;

/**
 * 分页DAO适配器
 * 
 * @author minyazi
 */
public abstract class PagingDaoAdapter<T> implements PagingDao<T> {
    public PagingDaoAdapter() {}
    
    @Override
    public int getTotalNumber() throws DaoException {
        return 0;
    }
    
    @Override
    public List<T> selectToPaging(int offset, int pageSize) throws DaoException {
        return null;
    }
    
    @Override
    public int getTotalNumber(String queryCondition) throws DaoException {
        return 0;
    }
    
    @Override
    public List<T> selectToPaging(int offset, int pageSize, String queryCondition) throws DaoException {
        return null;
    }
}