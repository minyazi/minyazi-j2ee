package com.minyazi.j2ee.dao.springjdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.minyazi.j2ee.dao.ProcessInfoDao;
import com.minyazi.j2ee.dao.domain.ProcessInfoDO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:j2ee-dao.xml")
public class ProcessInfoDaoTest {
    @Autowired
    private ProcessInfoDao processInfoDao;
    
    @Before
    public void beforeTest() {
        ProcessInfoDO info = new ProcessInfoDO();
        info.setProcessCode("PC000000");
        info.setProcessMesg("处理成功");
        processInfoDao.insert(info);
    }
    
    @After
    public void afterTest() {
        processInfoDao.deleteAll();
    }
    
    @Test
    public void testInsert() {
        assertNull(processInfoDao.selectById("PC999999"));
        
        ProcessInfoDO info = new ProcessInfoDO();
        info.setProcessCode("PC999999");
        info.setProcessMesg("处理失败");
        processInfoDao.insert(info);
        
        assertNotNull(processInfoDao.selectById("PC999999"));
    }
    
    @Test
    public void testDelete() {
        assertNotNull(processInfoDao.selectById("PC000000"));
        processInfoDao.deleteById("PC000000");
        assertNull(processInfoDao.selectById("PC000000"));
    }
    
    @Test
    public void testUpdate() {
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
    public void testSelect() {
        ProcessInfoDO info = processInfoDao.selectById("PC000000");
        assertNotNull(info);
        assertEquals("PC000000", info.getProcessCode());
        assertEquals("处理成功", info.getProcessMesg());
    }
}
