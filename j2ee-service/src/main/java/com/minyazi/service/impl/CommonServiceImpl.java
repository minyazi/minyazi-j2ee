package com.minyazi.service.impl;

import org.springframework.stereotype.Service;

import com.minyazi.core.util.DateUtil;
import com.minyazi.core.util.LogUtil;
import com.minyazi.service.CommonService;

/**
 * 公共Service
 * 
 * @author minyazi
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
    public CommonServiceImpl() {}
    
    /**
     * 测试定时任务
     */
    public void testTask() {
        LogUtil.info(DateUtil.getISODateTime());
    }
}
