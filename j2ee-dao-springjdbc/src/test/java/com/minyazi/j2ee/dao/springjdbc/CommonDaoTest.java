package com.minyazi.j2ee.dao.springjdbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.dao.DaoBeansConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoBeansConfig.class)
public class CommonDaoTest {
    @Autowired
    private CommonDaoImpl commonDao;
    
    @Before
    public void beforeTest() {}
    
    @After
    public void afterTest() {}
    
    @Test
    public void test() {
        JdbcTemplate jdbcTemplate = commonDao.getJdbcTemplate();
        SqlRowSet srs = jdbcTemplate.queryForRowSet("select * from test limit 0");
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
    public void testInsert() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("FD_A", "A");
        data.put("FD_B", "B");
        data.put("FD_C", "1.00");
        data.put("FD_D", "D");
        data.put("FD_E", "1");
        data.put("FD_F", "");
        commonDao.insert("test", data);
    }
}
