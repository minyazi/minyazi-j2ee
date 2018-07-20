package com.minyazi.j2ee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minyazi.j2ee.dao.DaoException;
import com.minyazi.j2ee.dao.ProcessInfoDao;
import com.minyazi.j2ee.dao.domain.ProcessInfoDO;
import com.minyazi.j2ee.service.DefaultService;
import com.minyazi.j2ee.service.ProcessInfoService;
import com.minyazi.j2ee.service.ServiceException;
import com.minyazi.j2ee.service.dto.PagingDTO;

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
    
    @Autowired
    public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
        this.processInfoDao = processInfoDao;
    }
    
    @Override
    public PagingDTO<ProcessInfoDO> listToPaging(int page, int pageSize) throws ServiceException {
        try {
            int totalNumber = getProcessInfoDao().getTotalNumber();
            PagingDTO<ProcessInfoDO> result = new PagingDTO<ProcessInfoDO>(page, pageSize, totalNumber);
            result.setData(getProcessInfoDao().selectToPaging(result.getOffset(), pageSize));
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
            result.setData(getProcessInfoDao().selectToPaging(result.getOffset(), pageSize, queryCondition));
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
