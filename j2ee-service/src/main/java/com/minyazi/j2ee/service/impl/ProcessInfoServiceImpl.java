package com.minyazi.j2ee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minyazi.j2ee.dao.ProcessInfoDao;
import com.minyazi.j2ee.dao.domain.ProcessInfoDO;
import com.minyazi.j2ee.service.AbstractService;
import com.minyazi.j2ee.service.ProcessInfoService;
import com.minyazi.j2ee.service.dto.PagingDTO;

/**
 * 处理信息Service
 * 
 * @author minyazi
 */
@Service("processInfoService")
public class ProcessInfoServiceImpl extends AbstractService implements ProcessInfoService {
    @Autowired
    private ProcessInfoDao processInfoDao;
    
    public ProcessInfoServiceImpl() {}
    
    @Override
    public PagingDTO<ProcessInfoDO> listToPaging(int page, int pageSize) {
        int totalNumber = processInfoDao.getTotalNumber();
        PagingDTO<ProcessInfoDO> result = new PagingDTO<ProcessInfoDO>(page, pageSize, totalNumber);
        result.setData(processInfoDao.selectToPaging(result.getOffset(), pageSize));
        return result;
    }
    
    @Override
    public PagingDTO<ProcessInfoDO> listToPaging(int page, int pageSize, String queryCondition) {
        int totalNumber = processInfoDao.getTotalNumber(queryCondition);
        PagingDTO<ProcessInfoDO> result = new PagingDTO<ProcessInfoDO>(page, pageSize, totalNumber);
        result.setData(processInfoDao.selectToPaging(result.getOffset(), pageSize, queryCondition));
        return result;
    }
}
