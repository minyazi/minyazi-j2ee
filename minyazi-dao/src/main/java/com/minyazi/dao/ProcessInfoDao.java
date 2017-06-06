package com.minyazi.dao;

import java.util.List;

import com.minyazi.dao.domain.ProcessInfoDO;

/**
 * 处理信息DAO
 * 
 * @author minyazi
 */
public interface ProcessInfoDao {
    /**
     * 新增处理信息
     * 
     * @param info 待新增的处理信息
     * @return 新增的处理信息
     * @throws DaoException DAO异常
     */
    ProcessInfoDO insert(ProcessInfoDO info) throws DaoException;
    
    /**
     * 删除处理信息
     * 
     * @param processCode 待删除的处理信息的处理码
     * @throws DaoException DAO异常
     */
    void deleteById(String processCode) throws DaoException;
    
    /**
     * 删除全部处理信息
     * 
     * @throws DaoException DAO异常
     */
    void deleteAll() throws DaoException;
    
    /**
     * 修改处理信息
     * 
     * @param info 待修改的处理信息
     * @return 修改后的处理信息
     * @throws DaoException DAO异常
     */
    ProcessInfoDO update(ProcessInfoDO info) throws DaoException;
    
    /**
     * 查询处理信息
     * 
     * @param processCode 待查询的处理信息的处理码
     * @return 如果待查询的处理信息不存在，则返回null，否则返回查询结果
     * @throws DaoException DAO异常
     */
    ProcessInfoDO selectById(String processCode) throws DaoException;
    
    /**
     * 查询全部处理信息
     * 
     * @return 查询结果
     * @throws DaoException DAO异常
     */
    List<ProcessInfoDO> selectAll() throws DaoException;
}
