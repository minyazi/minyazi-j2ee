package com.minyazi.j2ee.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceBeansConfig.class)
public class CommonServiceTest {
    @Autowired
    private CommonService commonService;
    
    @Before
    public void beforeTest() {}
    
    @After
    public void afterTest() {}
    
    @Test
    public void testTask() {
        commonService.testTask();
    }
}
