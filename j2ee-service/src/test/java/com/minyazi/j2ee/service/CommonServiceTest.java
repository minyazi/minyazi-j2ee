package com.minyazi.j2ee.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CommonServiceTest {
    private ApplicationContext context;
    private CommonService commonService;
    
    @Before
    public void init() throws Exception {
        context = new AnnotationConfigApplicationContext(ServiceBeansConfig.class);
        commonService = context.getBean("commonService", CommonService.class);
    }
    
    @Test
    public void testTask() throws Exception {
        commonService.testTask();
    }
}
