package com.minyazi.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.minyazi.dao.ProcessInfoDao;
import com.minyazi.dao.domain.ProcessInfoDO;

public class ProcessInfoDaoTest {
    private ApplicationContext context;
    private ProcessInfoDao processInfoDao;
    
    @Before
    public void init() throws Exception {
        context = new ClassPathXmlApplicationContext("minyazi-dao-hibernate.xml");
        processInfoDao = context.getBean("processInfoDao", ProcessInfoDao.class);
        
        processInfoDao.deleteAll();
        
        ProcessInfoDO info = new ProcessInfoDO();
        info.setProcessCode("PC000000");
        info.setProcessMesg("处理成功");
        processInfoDao.insert(info);
    }
    
    @Test
    public void testInsert() throws Exception {
        assertNull(processInfoDao.selectById("PC999999"));
        
        ProcessInfoDO info = new ProcessInfoDO();
        info.setProcessCode("PC999999");
        info.setProcessMesg("处理失败");
        processInfoDao.insert(info);
        
        assertNotNull(processInfoDao.selectById("PC999999"));
    }
    
    @Test
    public void testDelete() throws Exception {
        assertNotNull(processInfoDao.selectById("PC000000"));
        processInfoDao.deleteById("PC000000");
        assertNull(processInfoDao.selectById("PC000000"));
    }
    
    @Test
    public void testUpdate() throws Exception {
        ProcessInfoDO info = processInfoDao.selectById("PC000000");
        assertNotNull(info);
        info.setProcessMesg("处理失败");
        processInfoDao.update(info);
        
        info = processInfoDao.selectById("PC000000");
        assertNotNull(info);
        assertEquals("PC000000", info.getProcessCode());
        assertEquals("处理失败", info.getProcessMesg());
    }
    
    @Test
    public void testSelect() throws Exception {
        ProcessInfoDO info = processInfoDao.selectById("PC000000");
        assertNotNull(info);
        assertEquals("PC000000", info.getProcessCode());
        assertEquals("处理成功", info.getProcessMesg());
    }
}
