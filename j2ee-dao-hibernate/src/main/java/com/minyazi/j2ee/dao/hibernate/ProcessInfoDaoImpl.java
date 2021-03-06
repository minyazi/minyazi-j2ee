package com.minyazi.j2ee.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.minyazi.j2ee.dao.DaoException;
import com.minyazi.j2ee.dao.ProcessInfoDao;
import com.minyazi.j2ee.dao.domain.ProcessInfoDO;

/**
 * 处理信息DAO
 * 
 * @author minyazi
 */
@Repository("processInfoDao")
public class ProcessInfoDaoImpl extends DefaultDao<ProcessInfoDO> implements ProcessInfoDao {
    public ProcessInfoDaoImpl() {}
    
    @Override
    public ProcessInfoDO insert(ProcessInfoDO info) throws DaoException {
        getHibernateTemplate().save(info);
        return info;
    }
    
    @Override
    public void deleteById(final String processCode) throws DaoException {
        getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery("delete from ProcessInfoDO where processCode=:processCode")
                    .setString("processCode", processCode)
                    .executeUpdate();
            }
        });
    }
    
    @Override
    public void deleteAll() throws DaoException {
        getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery("delete from ProcessInfoDO")
                    .executeUpdate();
            }
        });
    }
    
    @Override
    public ProcessInfoDO update(ProcessInfoDO info) throws DaoException {
        getHibernateTemplate().update(info);
        return selectById(info.getProcessCode());
    }
    
    @Override
    public ProcessInfoDO selectById(final String processCode) throws DaoException {
        ProcessInfoDO info = getHibernateTemplate().execute(new HibernateCallback<ProcessInfoDO>() {
            public ProcessInfoDO doInHibernate(Session session) throws HibernateException, SQLException {
                return (ProcessInfoDO) session.createQuery("from ProcessInfoDO where processCode=:processCode")
                    .setString("processCode", processCode)
                    .uniqueResult();
            }
        });
        return info;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessInfoDO> selectAll() throws DaoException {
        return (List<ProcessInfoDO>) getHibernateTemplate().find("from ProcessInfoDO order by processCode");
    }
    
    @Override
    public int getTotalNumber() throws DaoException {
        Long totalNumber = getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                return (Long) session.createQuery("select count(*) from ProcessInfoDO")
                    .uniqueResult();
            }
        });
        return totalNumber.intValue();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessInfoDO> selectToPaging(final int offset, final int pageSize) throws DaoException {
        return getHibernateTemplate().execute(new HibernateCallback<List<ProcessInfoDO>>() {
            public List<ProcessInfoDO> doInHibernate(Session session) throws HibernateException, SQLException {
                return (List<ProcessInfoDO>) session.createQuery("from ProcessInfoDO order by processCode")
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();
            }
        });
    }
    
    @Override
    public int getTotalNumber(final String queryCondition) throws DaoException {
        Long totalNumber = getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                return (Long) session.createQuery("select count(*) from ProcessInfoDO where 1=1 :queryCondition")
                    .setString("queryCondition", queryCondition)
                    .uniqueResult();
            }
        });
        return totalNumber.intValue();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessInfoDO> selectToPaging(final int offset, final int pageSize, final String queryCondition) throws DaoException {
        return getHibernateTemplate().execute(new HibernateCallback<List<ProcessInfoDO>>() {
            public List<ProcessInfoDO> doInHibernate(Session session) throws HibernateException, SQLException {
                return (List<ProcessInfoDO>) session.createQuery("from ProcessInfoDO where 1=1 and :queryCondition order by processCode")
                    .setString("queryCondition", queryCondition)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();
            }
        });
    }
}
