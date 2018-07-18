package com.minyazi.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommonServiceTest {
    private ApplicationContext context;
    private CommonService commonService;
    
    @Before
    public void init() throws Exception {
        String[] springConfigFiles = {"minyazi-dao-springjdbc.xml", "minyazi-service-springjdbc.xml"};
        context = new ClassPathXmlApplicationContext(springConfigFiles);
        commonService = context.getBean("commonService", CommonService.class);
    }
    
    @Test
    public void testTask() throws Exception {
        commonService.testTask();
    }
}
