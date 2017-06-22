package com.minyazi.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.minyazi.dao.DaoException;
import com.minyazi.dao.ProcessInfoDao;
import com.minyazi.dao.domain.ProcessInfoDO;
import com.minyazi.service.ProcessInfoService;
import com.minyazi.service.ServiceException;
import com.minyazi.service.domain.PagingDTO;

/**
 * 处理信息Service
 * 
 * @author minyazi
 */
@Service("processInfoService")
public class ProcessInfoServiceImpl extends DefaultService<ProcessInfoDO> implements ProcessInfoService {
    private ProcessInfoDao processInfoDao;
    
    public ProcessInfoServiceImpl() {}
    
    public ProcessInfoDao getProcessInfoDao() {
        return processInfoDao;
    }
    
    @Required
    @Resource
    public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
        this.processInfoDao = processInfoDao;
    }
    
    @Override
    public PagingDTO<ProcessInfoDO> listToPaging(int page, int pageSize) throws ServiceException {
        try {
            int totalNumber = getProcessInfoDao().getTotalNumber();
            PagingDTO<ProcessInfoDO> result = new PagingDTO<ProcessInfoDO>(page, pageSize, totalNumber);
            if (totalNumber > 0) {
                result.setData(getProcessInfoDao().selectToPaging(result.getOffset(), pageSize));
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    
    @Override
    public PagingDTO<ProcessInfoDO> listToPaging(int page, int pageSize, String queryCondition) throws ServiceException {
        try {
            int totalNumber = getProcessInfoDao().getTotalNumber(queryCondition);
            PagingDTO<ProcessInfoDO> result = new PagingDTO<ProcessInfoDO>(page, pageSize, totalNumber);
            if (totalNumber > 0) {
                result.setData(getProcessInfoDao().selectToPaging(result.getOffset(), pageSize, queryCondition));
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
