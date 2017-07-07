package com.minyazi.dao.springjdbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.minyazi.core.util.LogUtil;

public class CommonDaoTest {
    private ApplicationContext context;
    private CommonDaoImpl commonDao;
    
    @Before
    public void init() throws Exception {
        context = new ClassPathXmlApplicationContext("minyazi-dao-springjdbc.xml");
        commonDao = context.getBean("commonDao", CommonDaoImpl.class);
    }
    
    @Test
    public void test() throws Exception {
        /*
        drop table if exists data;
        create table data (
            FD_A     char(10)          not null,
            FD_B     varchar(20)       not null,
            FD_C     decimal(16,2)     not null,
            FD_D     text              not null,
            FD_E     int               not null
        );
        */
        JdbcTemplate jdbcTemplate = commonDao.getJdbcTemplate();
        SqlRowSet srs = jdbcTemplate.queryForRowSet("select * from data limit 0");
        SqlRowSetMetaData srsmd = srs.getMetaData();
        LogUtil.info("**************************************************");
        LogUtil.info("ColumnCount       : " + srsmd.getColumnCount());
        LogUtil.info("ColumnNames       : " + Arrays.toString(srsmd.getColumnNames()));
        int columnCount = srsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            LogUtil.info("**************************************************");
            LogUtil.info("CatalogName       : " + srsmd.getCatalogName(i));
            LogUtil.info("ColumnClassName   : " + srsmd.getColumnClassName(i));
            LogUtil.info("ColumnDisplaySize : " + srsmd.getColumnDisplaySize(i));
            LogUtil.info("ColumnLabel       : " + srsmd.getColumnLabel(i));
            LogUtil.info("ColumnName        : " + srsmd.getColumnName(i));
            LogUtil.info("ColumnType        : " + srsmd.getColumnType(i));
            LogUtil.info("ColumnTypeName    : " + srsmd.getColumnTypeName(i));
            LogUtil.info("Precision         : " + srsmd.getPrecision(i));
            LogUtil.info("Scale             : " + srsmd.getScale(i));
            LogUtil.info("SchemaName        : " + srsmd.getSchemaName(i));
            LogUtil.info("TableName         : " + srsmd.getTableName(i));
            LogUtil.info("CaseSensitive     : " + srsmd.isCaseSensitive(i));
            LogUtil.info("Currency          : " + srsmd.isCurrency(i));
            LogUtil.info("Signed            : " + srsmd.isSigned(i));
        }
        LogUtil.info("**************************************************");
    }
    
    @Test
    public void testInsert() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("FD_A", "A");
        data.put("FD_B", "B");
        data.put("FD_C", "1.00");
        data.put("FD_D", "D");
        data.put("FD_E", "1");
        commonDao.insert("data", data);
    }
}
