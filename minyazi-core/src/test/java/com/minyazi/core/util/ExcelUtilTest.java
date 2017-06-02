package com.minyazi.core.util;

import org.junit.Test;

import com.minyazi.core.util.ExcelUtil;
import com.minyazi.core.util.StringUtil;

public class ExcelUtilTest {
    @Test
    public void testCreateExcel() {
        ExcelUtil excel = new ExcelUtil();
        String sheetName = "小额来账信息查询";
        String tableHead = "小额客户发起普通贷记业务来账信息（2014-09-30～2014-09-30）";
        String[] columnHead = new String[8];
        columnHead[0] = "交易流水号";
        columnHead[1] = "所在包报文标识号";
        columnHead[2] = "报文类型";
        columnHead[3] = "付款行行号";
        columnHead[4] = "收款行行号";
        columnHead[5] = "金额";
        columnHead[6] = "业务状态";
        columnHead[7] = "账务状态";
        String[][] columnValue = new String[2][8];
        columnValue[0][0] = "2014093000004419";
        columnValue[0][1] = "2014093000004430";
        columnValue[0][2] = "客户发起普通贷记业务报文";
        columnValue[0][3] = "313513008886";
        columnValue[0][4] = "402821000015";
        columnValue[0][5] = "1,234.00";
        columnValue[0][6] = "已轧差";
        columnValue[0][7] = "成功入账";
        columnValue[1][0] = "2014093000004419";
        columnValue[1][1] = "2014093000004430";
        columnValue[1][2] = "客户发起普通贷记业务报文";
        columnValue[1][3] = "313513008886";
        columnValue[1][4] = "402821000015";
        columnValue[1][5] = "111,234.00";
        columnValue[1][6] = "已轧差";
        columnValue[1][7] = "成功入账";
        int[] columnWidth = new int[8];
        columnWidth[0] = 5000;
        columnWidth[1] = 5000;
        columnWidth[2] = 8000;
        columnWidth[3] = 4000;
        columnWidth[4] = 4000;
        columnWidth[5] = 5000;
        columnWidth[6] = 4000;
        columnWidth[7] = 4000;
        excel.createExcel(sheetName, tableHead, columnHead, columnValue, columnWidth);
        excel.saveExcel("D:/SFTP", StringUtil.getUpperUUID() + ".xls");
    }
}
