package com.minyazi.service;

import com.minyazi.service.domain.PagingDTO;

/**
 * 分页Service适配器
 * 
 * @author minyazi
 */
public abstract class PagingServiceAdapter implements PagingService {
    public PagingServiceAdapter() {}
    
    @Override
    public <T> PagingDTO<T> listToPaging(int page, int pageSize) throws ServiceException {
        return null;
    }
    
    @Override
    public <T> PagingDTO<T> listToPaging(int page, int pageSize, String queryCondition) throws ServiceException {
        return null;
    }
}
