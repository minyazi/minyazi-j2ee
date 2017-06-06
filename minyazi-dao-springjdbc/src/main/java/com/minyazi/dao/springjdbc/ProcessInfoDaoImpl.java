package com.minyazi.dao.springjdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.minyazi.dao.DaoException;
import com.minyazi.dao.ProcessInfoDao;
import com.minyazi.dao.domain.ProcessInfoDO;

/**
 * 处理信息DAO
 * 
 * @author minyazi
 */
@Repository("processInfoDao")
public class ProcessInfoDaoImpl extends DefaultDao implements ProcessInfoDao {
    public ProcessInfoDaoImpl() {}
    
    @Override
    public ProcessInfoDO insert(ProcessInfoDO info) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("insert into ProcessInfo (");
        sql.append("processCode,");
        sql.append("processMesg");
        sql.append(") values (?,?)");
        
        Object[] params = {info.getProcessCode(),
                info.getProcessMesg()};
        
        int result = this.getCommonDao().update(sql.toString(), params);
        if (result != 1) {
            throw new DaoException("新增处理信息失败");
        }
        
        return info;
    }
    
    @Override
    public void deleteById(String processCode) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("delete from ProcessInfo where processCode=?");
        
        Object[] params = {processCode};
        
        this.getCommonDao().update(sql.toString(), params);
    }
    
    @Override
    public void deleteAll() throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("delete from ProcessInfo");
        this.getCommonDao().update(sql.toString());
    }
    
    @Override
    public ProcessInfoDO update(ProcessInfoDO info) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("update ProcessInfo set ");
        sql.append("processMesg=?");
        sql.append(" where processCode=?");
        
        Object[] params = {info.getProcessMesg(),
                info.getProcessCode()};
        
        int result = this.getCommonDao().update(sql.toString(), params);
        if (result != 1) {
            throw new DaoException("修改处理信息失败");
        }
        
        return this.selectById(info.getProcessCode());
    }
    
    @Override
    public ProcessInfoDO selectById(String processCode) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo where processCode=?");
        
        Object[] params = {processCode};
        
        return this.getCommonDao().selectToBean(ProcessInfoDO.class, sql.toString(), params);
    }
    
    @Override
    public List<ProcessInfoDO> selectAll() throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo order by processCode");
        return this.getCommonDao().selectToList(ProcessInfoDO.class, sql.toString());
    }
    
    @Override
    public int getTotalNumber() throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo");
        return this.getCommonDao().getTotalNumber(sql.toString());
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessInfoDO> selectToPaging(int offset, int pageSize) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo order by processCode");
        return this.getCommonDao().selectToPaging(ProcessInfoDO.class, offset, pageSize, sql.toString());
    }
    
    @Override
    public int getTotalNumber(String queryCondition) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo where 1=1 ").append(queryCondition);
        return this.getCommonDao().getTotalNumber(sql.toString());
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessInfoDO> selectToPaging(int offset, int pageSize, String queryCondition) throws DaoException {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ProcessInfo where 1=1 ").append(queryCondition).append(" order by processCode");
        return this.getCommonDao().selectToPaging(ProcessInfoDO.class, offset, pageSize, sql.toString());
    }
}