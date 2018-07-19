package com.minyazi.j2ee.service;

import com.minyazi.j2ee.service.dto.PagingDTO;

/**
 * 分页Service
 * 
 * @author minyazi
 */
public interface PagingService<T> {
    /**
     * 获取分页数据
     * 
     * @param page 页码
     * @param pageSize 每页记录数
     * @return 分页数据
     * @throws ServiceException Service异常
     */
    PagingDTO<T> listToPaging(int page, int pageSize) throws ServiceException;
    
    /**
     * 获取分页数据
     * 
     * @param page 页码
     * @param pageSize 每页记录数
     * @param queryCondition 查询条件
     * @return 分页数据
     * @throws ServiceException Service异常
     */
    PagingDTO<T> listToPaging(int page, int pageSize, String queryCondition) throws ServiceException;
}
