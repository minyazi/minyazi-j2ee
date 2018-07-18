package com.minyazi.j2ee.dao.springjdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.core.util.StringUtil;
import com.minyazi.j2ee.dao.CommonDao;

/**
 * 公共DAO
 * 
 * @author minyazi
 */
@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
    private JdbcTemplate jdbcTemplate;
    
    public CommonDaoImpl() {}
    
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    @Required
    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * 查询数据
     * 
     * @param sql SQL语句
     * @return 查询结果
     */
    public List<Map<String, Object>> selectToList(String sql) {
        List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result.size());
        
        return result;
    }
    
    /**
     * 查询数据
     * 
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public List<Map<String, Object>> selectToList(String sql, Object... params) {
        List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql, params);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result.size());
        LogUtil.info("（SQL参数）{}", Arrays.toString(params));
        
        return result;
    }
    
    /**
     * 查询数据
     * 
     * @param sql SQL语句
     * @return 查询结果
     */
    public List<Map<String, String>> selectToList2(String sql) {
        List<Map<String, String>> result = getJdbcTemplate().query(sql, new ResultSetExtractor<List<Map<String, String>>>() {
            @Override
            public List<Map<String, String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    Map<String, String> _result = new HashMap<String, String>();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnName = rsmd.getColumnLabel(i);
                        _result.put(columnName, rs.getString(columnName));
                    }
                    result.add(_result);
                }
                return result;
            }
        });
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result.size());
        
        return result;
    }
    
    /**
     * 查询数据
     * 
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public List<Map<String, String>> selectToList2(String sql, Object... params) {
        List<Map<String, String>> result = getJdbcTemplate().query(sql, new ResultSetExtractor<List<Map<String, String>>>() {
            @Override
            public List<Map<String, String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    Map<String, String> _result = new HashMap<String, String>();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnName = rsmd.getColumnLabel(i);
                        _result.put(columnName, rs.getString(columnName));
                    }
                    result.add(_result);
                }
                return result;
            }
        }, params);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result.size());
        LogUtil.info("（SQL参数）{}", Arrays.toString(params));
        
        return result;
    }
    
    /**
     * 查询数据
     * 
     * @param <T> 类型参数
     * @param clazz 类型参数
     * @param sql SQL语句
     * @return 查询结果
     */
    public <T> List<T> selectToList(Class<T> clazz, String sql) {
        List<T> result = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<T>(clazz));
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result.size());
        
        return result;
    }
    
    /**
     * 查询数据
     * 
     * @param <T> 类型参数
     * @param clazz 类型参数
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public <T> List<T> selectToList(Class<T> clazz, String sql, Object... params) {
        List<T> result = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<T>(clazz), params);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result.size());
        LogUtil.info("（SQL参数）{}", Arrays.toString(params));
        
        return result;
    }
    
    /**
     * 查询数据
     * 
     * @param <T> 类型参数
     * @param clazz 类型参数
     * @param sql SQL语句
     * @return 查询结果
     */
    public <T> T selectToBean(Class<T> clazz, String sql) {
        List<T> result = selectToList(clazz, sql);
        
        T t = null;
        if (result.size() != 0) {
            t = result.get(0);
        }
        
        return t;
    }
    
    /**
     * 查询数据
     * 
     * @param <T> 类型参数
     * @param clazz 类型参数
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public <T> T selectToBean(Class<T> clazz, String sql, Object... params) {
        List<T> result = selectToList(clazz, sql, params);
        
        T t = null;
        if (result.size() != 0) {
            t = result.get(0);
        }
        
        return t;
    }
    
    /**
     * 更新数据
     * 
     * @param sql SQL语句
     * @return 更新结果
     */
    public int update(String sql) {
        int result = getJdbcTemplate().update(sql);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result);
        
        return result;
    }
    
    /**
     * 更新数据
     * 
     * @param sql SQL语句
     * @param params 参数集
     * @return 更新结果
     */
    public int update(String sql, Object... params) {
        int result = getJdbcTemplate().update(sql, params);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", sql, result);
        LogUtil.info("（SQL参数）{}", Arrays.toString(params));
        
        return result;
    }
    
    /**
     * 保存数据
     * 
     * @param tableName 数据表名
     * @param data 要保存的数据
     * @return 保存结果
     */
    public int insert(String tableName, Map<String, String> data) {
        StringBuilder sql = new StringBuilder(500);
        sql.append("select * from ").append(tableName).append(" limit 0");
        SqlRowSet srs = getJdbcTemplate().queryForRowSet(sql.toString());
        SqlRowSetMetaData srsmd = srs.getMetaData();
        int columnCount = srsmd.getColumnCount();
        
        StringBuilder fields = new StringBuilder(500);
        StringBuilder values = new StringBuilder(500);
        Object[] params = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            String columnName = srsmd.getColumnName(i);
            String columnClassName = srsmd.getColumnClassName(i);
            String columnValue = data.get(columnName);
            if ("java.lang.String".equals(columnClassName)) {
                columnValue = StringUtil.formatNullString(columnValue);
            } else {
                columnValue = StringUtil.formatNullAmount(columnValue);
            }
            
            if (i != columnCount) {
                fields.append(columnName).append(",");
                values.append("?,");
            } else {
                fields.append(columnName);
                values.append("?");
            }
            params[i - 1] = columnValue;
        }
        
        if (columnCount > 0) {
            sql = new StringBuilder(500);
            sql.append("insert into ").append(tableName).append(" (").append(fields).append(")");
            sql.append(" values (").append(values).append(")");
            return update(sql.toString(), params);
        } else {
            return 0;
        }
    }
    
    /**
     * 查询总记录数
     * @param sql SQL语句
     * @return 总记录数
     */
    public int getTotalNumber(String sql) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append("select count(*) from (").append(sql).append(") as T");
        int result = getJdbcTemplate().queryForObject(_sql.toString(), Integer.class);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", _sql.toString(), result);
        
        return result;
    }
    
    /**
     * 查询总记录数
     * @param sql SQL语句
     * @param params 参数集
     * @return 总记录数
     */
    public int getTotalNumber(String sql, Object... params) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append("select count(*) from (").append(sql).append(") as T");
        int result = getJdbcTemplate().queryForObject(_sql.toString(), Integer.class, params);
        
        LogUtil.info("（执行SQL）{}，（执行结果）{}", _sql.toString(), result);
        LogUtil.info("（SQL参数）{}", Arrays.toString(params));
        
        return result;
    }
    
    /**
     * 分页查询数据
     * 
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param sql SQL语句
     * @return 查询结果
     */
    public List<Map<String, Object>> selectToPaging(int offset, int pageSize, String sql) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append(sql).append(" limit ").append(offset).append(",").append(pageSize);
        return selectToList(_sql.toString());
    }
    
    /**
     * 分页查询数据
     * 
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public List<Map<String, Object>> selectToPaging(int offset, int pageSize, String sql, Object... params) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append(sql).append(" limit ").append(offset).append(",").append(pageSize);
        return selectToList(_sql.toString(), params);
    }
    
    /**
     * 分页查询数据
     * 
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param sql SQL语句
     * @return 查询结果
     */
    public List<Map<String, String>> selectToPaging2(int offset, int pageSize, String sql) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append(sql).append(" limit ").append(offset).append(",").append(pageSize);
        return selectToList2(_sql.toString());
    }
    
    /**
     * 分页查询数据
     * 
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public List<Map<String, String>> selectToPaging2(int offset, int pageSize, String sql, Object... params) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append(sql).append(" limit ").append(offset).append(",").append(pageSize);
        return selectToList2(_sql.toString(), params);
    }
    
    /**
     * 分页查询数据
     * 
     * @param <T> 类型参数
     * @param clazz 类型参数
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param sql SQL语句
     * @return 查询结果
     */
    public <T> List<T> selectToPaging(Class<T> clazz, int offset, int pageSize, String sql) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append(sql).append(" limit ").append(offset).append(",").append(pageSize);
        return selectToList(clazz, _sql.toString());
    }
    
    /**
     * 分页查询数据
     * 
     * @param <T> 类型参数
     * @param clazz 类型参数
     * @param offset 开始记录索引
     * @param pageSize 每页记录数
     * @param sql SQL语句
     * @param params 参数集
     * @return 查询结果
     */
    public <T> List<T> selectToPaging(Class<T> clazz, int offset, int pageSize, String sql, Object... params) {
        StringBuilder _sql = new StringBuilder(500);
        _sql.append(sql).append(" limit ").append(offset).append(",").append(pageSize);
        return selectToList(clazz, _sql.toString(), params);
    }
}
