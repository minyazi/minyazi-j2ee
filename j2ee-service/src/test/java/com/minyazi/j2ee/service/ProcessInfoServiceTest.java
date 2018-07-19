package com.minyazi.j2ee.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.service.ProcessInfoService;

public class ProcessInfoServiceTest {
    private ApplicationContext context;
    private ProcessInfoService processInfoService;
    
    @Before
    public void init() throws Exception {
        String[] springConfigFiles = {"j2ee-dao.xml", "j2ee-service-springjdbc.xml"};
        context = new ClassPathXmlApplicationContext(springConfigFiles);
        processInfoService = context.getBean("processInfoService", ProcessInfoService.class);
    }
    
    @Test
    public void test() throws Exception {
        LogUtil.info(processInfoService.listToPaging(1, 10).toString());
        LogUtil.info(processInfoService.listToPaging(1, 10, "and processCode!='PC000000'").toString());
    }
}
