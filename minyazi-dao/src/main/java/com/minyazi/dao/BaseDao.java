package com.minyazi.dao;

import java.util.List;

/**
 * 基础DAO
 * 
 * @author minyazi
 */
public abstract class BaseDao implements PagingDao {
    public BaseDao() {}
    
    @Override
    public int getTotalNumber() throws DaoException {
        return 0;
    }
    
    @Override
    public <T> List<T> selectToPaging(int offset, int pageSize) throws DaoException {
        return null;
    }
    
    @Override
    public int getTotalNumber(String queryCondition) throws DaoException {
        return 0;
    }
    
    @Override
    public <T> List<T> selectToPaging(int offset, int pageSize, String queryCondition) throws DaoException {
        return null;
    }
}
