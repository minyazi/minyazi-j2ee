package com.minyazi.j2ee.service;

import com.minyazi.j2ee.service.dto.PagingDTO;

/**
 * 分页Service适配器
 * 
 * @author minyazi
 */
public abstract class PagingServiceAdapter<T> implements PagingService<T> {
    public PagingServiceAdapter() {}
    
    @Override
    public PagingDTO<T> listToPaging(int page, int pageSize) throws ServiceException {
        return null;
    }
    
    @Override
    public PagingDTO<T> listToPaging(int page, int pageSize, String queryCondition) throws ServiceException {
        return null;
    }
}
