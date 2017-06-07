package com.minyazi.dao.mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.minyazi.core.util.LogUtil;
import com.minyazi.dao.ProcessInfoDao;
import com.minyazi.dao.domain.ProcessInfoDO;

public class MyBatisTest {
    private static SqlSessionFactory sessionFactory;
    
    @BeforeClass
    public static void init() {
        InputStream is = MyBatisTest.class.getClassLoader().getResourceAsStream("mybatis.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        
//        Reader reader = Resources.getResourceAsReader("mybatis.xml");
//        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
    
    @Test
    public void testInsert() throws Exception {
        SqlSession session = sessionFactory.openSession();
        try {
            ProcessInfoDao dao = session.getMapper(ProcessInfoDao.class);
            
            dao.deleteAll();
            session.commit();
            
            ProcessInfoDO info = new ProcessInfoDO();
            info.setProcessCode("PC000000");
            info.setProcessMesg("处理成功");
            LogUtil.info(dao.insert(info) + "");
            session.commit();
            
            info = new ProcessInfoDO();
            info.setProcessCode("PC999999");
            info.setProcessMesg("处理失败");
            LogUtil.info(dao.insert(info) + "");
            session.commit();
        } finally {
            session.close();
        }
    }
    
    @Test
    public void testDelete() throws Exception {
        SqlSession session = sessionFactory.openSession();
        try {
            ProcessInfoDao dao = session.getMapper(ProcessInfoDao.class);
            dao.deleteById("PC000000");
            session.commit();
        } finally {
            session.close();
        }
    }
    
    @Test
    public void testUpdate() throws Exception {
        SqlSession session = sessionFactory.openSession();
        try {
            ProcessInfoDao dao = session.getMapper(ProcessInfoDao.class);
            
            ProcessInfoDO info = new ProcessInfoDO();
            info.setProcessCode("PC999999");
            info.setProcessMesg("处理成功");
            LogUtil.info(dao.update(info) + "");
            session.commit();
        } finally {
            session.close();
        }
    }
    
    @Test
    public void testSelect() throws Exception {
        SqlSession session = sessionFactory.openSession();
        try {
            ProcessInfoDao dao = session.getMapper(ProcessInfoDao.class);
            
            ProcessInfoDO info = dao.selectById("PC999999");
            LogUtil.info(info.toString());
//            LogUtil.info(new String(info.getProcessMesg().getBytes("ISO-8859-1"), "GB2312"));
            
            List<ProcessInfoDO> infos = dao.selectAll();
            LogUtil.info(infos.toString());
        } finally {
            session.close();
        }
    }
}
