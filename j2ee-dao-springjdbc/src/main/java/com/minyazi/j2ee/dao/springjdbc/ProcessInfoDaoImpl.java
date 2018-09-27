package com.minyazi.j2ee.dao.springjdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.minyazi.j2ee.dao.DaoException;
import com.minyazi.j2ee.dao.DefaultDao;
import com.minyazi.j2ee.dao.ProcessInfoDao;
import com.minyazi.j2ee.dao.domain.ProcessInfoDO;

/**
 * 处理信息DAO
 * 
 * @author minyazi
 */
@Repository("processInfoDao")
public class ProcessInfoDaoImpl extends DefaultDao implements ProcessInfoDao {
    public ProcessInfoDaoImpl() {}
    
    @Override
    public ProcessInfoDO insert(ProcessInfoDO data) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("insert into ProcessInfo (");
        sql.append("processCode,");
        sql.append("processMesg");
        sql.append(") values (?,?)");
        
        Object[] params = {data.getProcessCode(),
                data.getProcessMesg()};
        
        int result = commonDao.update(sql.toString(), params);
        if (result != 1) {
            throw new DaoException("新增处理信息失败");
        }
        
        return data;
    }
    
    @Override
    public ProcessInfoDO deleteById(String id) throws DaoException {
        ProcessInfoDO data = selectById(id);
        if (data == null) {
            throw new DaoException("待删除的处理信息不存在");
        }
        
        StringBuilder sql = new StringBuilder(500);
        sql.append("delete from ProcessInfo where processCode=?");
        
        Object[] params = {id};
        
        int result = commonDao.update(sql.toString(), params);
        if (result != 1) {
            throw new DaoException("删除处理信息失败");
        }
        
        return data;
    }
    
    @Override
    public int deleteAll() {
        StringBuilder sql = new StringBuilder(500);
        sql.append("delete from ProcessInfo");
        int result = commonDao.update(sql.toString());
        return result;
    }
    
    @Override
    public ProcessInfoDO update(ProcessInfoDO data) throws DaoException {
        if (selectById(data.getProcessCode()) == null) {
            throw new DaoException("待修改的处理信息不存在");
        }
        
        StringBuilder sql = new StringBuilder(500);
        sql.append("update ProcessInfo set ");
        sql.append("processMesg=?");
        sql.append(" where processCode=?");
        
        Object[] params = {data.getProcessMesg(),
                data.getProcessCode()};
        
        int result = commonDao.update(sql.toString(), params);
        if (result != 1) {
            throw new DaoException("修改处理信息失败");
        }
        
        return selectById(data.getProcessCode());
    }
    
    @Override
    public ProcessInfoDO selectById(String id) {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo where processCode=?");
        
        Object[] params = {id};
        
        return commonDao.selectToBean(ProcessInfoDO.class, sql.toString(), params);
    }
    
    @Override
    public List<ProcessInfoDO> selectAll() {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo order by processCode");
        return commonDao.selectToList(ProcessInfoDO.class, sql.toString());
    }
    
    @Override
    public int getTotalNumber() {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo");
        return commonDao.getTotalNumber(sql.toString());
    }
    
    @Override
    public List<ProcessInfoDO> selectToPaging(int offset, int pageSize) {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo order by processCode");
        return commonDao.selectToPaging(ProcessInfoDO.class, offset, pageSize, sql.toString());
    }
    
    @Override
    public int getTotalNumber(String queryCondition) {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo where 1=1 ").append(queryCondition);
        return commonDao.getTotalNumber(sql.toString());
    }
    
    @Override
    public List<ProcessInfoDO> selectToPaging(int offset, int pageSize, String queryCondition) {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo where 1=1 ").append(queryCondition).append(" order by processCode");
        return commonDao.selectToPaging(ProcessInfoDO.class, offset, pageSize, sql.toString());
    }
}
