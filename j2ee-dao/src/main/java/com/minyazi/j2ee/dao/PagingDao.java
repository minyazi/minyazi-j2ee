package com.minyazi.j2ee.dao;

import java.util.List;

/**
 * 分页DAO
 * 
 * @author minyazi
 */
public interface PagingDao<T> extends BaseDao<T> {
    /**
     * 查询总记录数
     * 
     * @return 总记录数
     */
    int getTotalNumber();
    
    /**
     * 分页查询数据
     * 
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @return 查询结果
     */
    List<T> selectToPaging(int offset, int pageSize);
    
    /**
     * 查询总记录数
     * 
     * @param queryCondition 查询条件
     * @return 总记录数
     */
    int getTotalNumber(String queryCondition);
    
    /**
     * 分页查询数据
     * 
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param queryCondition 查询条件
     * @return 查询结果
     */
    List<T> selectToPaging(int offset, int pageSize, String queryCondition);
}
