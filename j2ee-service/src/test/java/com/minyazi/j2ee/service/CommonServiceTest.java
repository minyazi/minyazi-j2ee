package com.minyazi.j2ee.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.minyazi.j2ee.service.CommonService;

public class CommonServiceTest {
    private ApplicationContext context;
    private CommonService commonService;
    
    @Before
    public void init() throws Exception {
        String[] springConfigFiles = {"j2ee-dao-springjdbc.xml", "j2ee-service-springjdbc.xml"};
        context = new ClassPathXmlApplicationContext(springConfigFiles);
        commonService = context.getBean("commonService", CommonService.class);
    }
    
    @Test
    public void testTask() throws Exception {
        commonService.testTask();
    }
}
