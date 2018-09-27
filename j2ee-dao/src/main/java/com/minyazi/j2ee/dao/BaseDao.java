package com.minyazi.j2ee.dao;

import java.util.List;

/**
 * Base DAO
 * 
 * @author minyazi
 */
public interface BaseDao<T> extends Dao {
    /**
     * 新增数据
     * 
     * @param data 待新增的数据
     * @return 新增的数据
     * @throws DaoException 新增数据失败时抛出
     */
    T insert(T data) throws DaoException;
    
    /**
     * 删除数据
     * 
     * @param id 待删除的数据的ID
     * @return 删除的数据
     * @throws DaoException 待删除的数据不存在或删除数据失败时抛出
     */
    T deleteById(String id) throws DaoException;
    
    /**
     * 删除全部数据
     * 
     * @return 删除的记录数
     */
    int deleteAll();
    
    /**
     * 修改数据
     * 
     * @param data 待修改的数据
     * @return 修改后的数据
     * @throws DaoException 待修改的数据不存在或修改数据失败时抛出
     */
    T update(T data) throws DaoException;
    
    /**
     * 查询数据
     * 
     * @param id 待查询的数据的ID
     * @return 如果待查询的数据不存在，则返回null，否则返回查询结果
     */
    T selectById(String id);
    
    /**
     * 查询全部数据
     * 
     * @return 查询结果
     */
    List<T> selectAll();
}
