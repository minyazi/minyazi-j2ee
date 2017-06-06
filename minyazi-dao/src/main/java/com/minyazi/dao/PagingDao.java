package com.minyazi.dao;

import java.util.List;

/**
 * 分页DAO
 * 
 * @author minyazi
 */
public interface PagingDao {
    /**
     * 查询总记录数
     * 
     * @return 总记录数
     * @throws DaoException DAO异常
     */
    int getTotalNumber() throws DaoException;
    
    /**
     * 分页查询数据
     * 
     * @param <T> 类型参数
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @return 查询结果
     * @throws DaoException DAO异常
     */
    <T> List<T> selectToPaging(int offset, int pageSize) throws DaoException;
    
    /**
     * 查询总记录数
     * 
     * @param queryCondition 查询条件
     * @return 总记录数
     * @throws DaoException DAO异常
     */
    int getTotalNumber(String queryCondition) throws DaoException;
    
    /**
     * 分页查询数据
     * 
     * @param <T> 类型参数
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param queryCondition 查询条件
     * @return 查询结果
     * @throws DaoException DAO异常
     */
    <T> List<T> selectToPaging(int offset, int pageSize, String queryCondition) throws DaoException;
}
