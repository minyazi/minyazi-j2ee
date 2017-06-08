package com.minyazi.service;

import com.minyazi.service.domain.PagingDTO;

/**
 * 分页Service
 * 
 * @author minyazi
 */
public interface PagingService {
    /**
     * 获取分页数据
     * 
     * @param <T> 类型参数
     * @param page 页码
     * @param pageSize 每页记录数
     * @return 分页数据
     * @throws ServiceException Service异常
     */
    <T> PagingDTO<T> listToPaging(int page, int pageSize) throws ServiceException;
    
    /**
     * 获取分页数据
     * 
     * @param <T> 类型参数
     * @param page 页码
     * @param pageSize 每页记录数
     * @param queryCondition 查询条件
     * @return 分页数据
     * @throws ServiceException Service异常
     */
    <T> PagingDTO<T> listToPaging(int page, int pageSize, String queryCondition) throws ServiceException;
}
