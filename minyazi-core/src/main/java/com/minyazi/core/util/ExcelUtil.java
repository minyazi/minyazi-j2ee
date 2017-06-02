package com.minyazi.core.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Excel工具类
 * 
 * @author minyazi
 */
public class ExcelUtil {
    /**
     * 工作薄
     */
    private HSSFWorkbook workbook = null;
    /**
     * 工作表
     */
    private HSSFSheet sheet = null;
    
    public ExcelUtil() {}
    
    public HSSFWorkbook getWorkbook() {
        return workbook;
    }
    
    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }
    
    public HSSFSheet getSheet() {
        return sheet;
    }
    
    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }
    
    /**
     * 创建Excel
     * 
     * @param sheetName 表名
     * @param tableHead 表头
     * @param columnHead 列头
     * @param columnValue 列值
     * @param columnWidth 列宽
     */
    public void createExcel(String sheetName, String tableHead, String[] columnHead, String[][] columnValue, int[] columnWidth) {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
        for (int i = 0; i < columnHead.length; i++) {
            sheet.setColumnWidth(i, columnWidth[i]);
        }
        
        // 表头
        HSSFFont tableHeadFont = workbook.createFont();
        tableHeadFont.setFontName("宋体"); // 字体
        tableHeadFont.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字形
        tableHeadFont.setFontHeightInPoints((short) 11); // 字号
        HSSFCellStyle tableHeadCellStyle = workbook.createCellStyle();
        tableHeadCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中对齐
        tableHeadCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中对齐
        tableHeadCellStyle.setWrapText(true); // 自动换行
        tableHeadCellStyle.setBorderTop(CellStyle.BORDER_THIN); // 上边框
        tableHeadCellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
        tableHeadCellStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左边框
        tableHeadCellStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边框
        tableHeadCellStyle.setFont(tableHeadFont);
        HSSFRow tableHeadRow = sheet.createRow(0);
        tableHeadRow.setHeightInPoints((short) 21);
        HSSFCell tableHeadCell = tableHeadRow.createCell(0, Cell.CELL_TYPE_STRING);
        tableHeadCell.setCellValue(tableHead);
        tableHeadCell.setCellStyle(tableHeadCellStyle);
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, columnHead.length - 1);
        setMergedCellsStyle(sheet, address, tableHeadCellStyle);
        sheet.addMergedRegion(address); // 合并单元格
        
        // 列头
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体"); // 字体
        columnHeadFont.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字形
        columnHeadFont.setFontHeightInPoints((short) 10); // 字号
        HSSFCellStyle columnHeadCellStyle = workbook.createCellStyle();
        columnHeadCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中对齐
        columnHeadCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中对齐
        columnHeadCellStyle.setWrapText(true); // 自动换行
        columnHeadCellStyle.setBorderTop(CellStyle.BORDER_THIN); // 上边框
        columnHeadCellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
        columnHeadCellStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左边框
        columnHeadCellStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边框
        columnHeadCellStyle.setFont(columnHeadFont);
        HSSFRow columnHeadRow = sheet.createRow(1);
        columnHeadRow.setHeightInPoints((short) 20);
        HSSFCell columnHeadCell = null;
        for (int i = 0; i < columnHead.length; i++) {
            columnHeadCell = columnHeadRow.createCell(i, Cell.CELL_TYPE_STRING);
            columnHeadCell.setCellValue(columnHead[i]);
            columnHeadCell.setCellStyle(columnHeadCellStyle);
        }
        
        // 列
        HSSFFont columnFont = workbook.createFont();
        columnFont.setFontName("宋体"); // 字体
        columnFont.setBoldweight(Font.BOLDWEIGHT_NORMAL); // 字形
        columnFont.setFontHeightInPoints((short) 10); // 字号
        HSSFCellStyle columnCellStyle = workbook.createCellStyle();
        columnCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中对齐
        columnCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中对齐
        columnCellStyle.setWrapText(true); // 自动换行
        columnCellStyle.setBorderTop(CellStyle.BORDER_THIN); // 上边框
        columnCellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
        columnCellStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左边框
        columnCellStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边框
        columnCellStyle.setFont(columnFont);
        HSSFRow columnRow = null;
        HSSFCell columnCell = null;
        for (int i = 0; i < columnValue.length; i++) {
            columnRow = sheet.createRow(i + 2);
            columnRow.setHeightInPoints((short) 20);
            for (int j = 0; j < columnHead.length; j++) {
                columnCell = columnRow.createCell(j, Cell.CELL_TYPE_STRING);
                columnCell.setCellValue(columnValue[i][j]);
                columnCell.setCellStyle(columnCellStyle);
            }
        }
    }
    
    /**
     * 设置合并单元格样式
     * 
     * @param sheet 工作表
     * @param address 合并单元格
     * @param cellStyle 样式
     */
    public void setMergedCellsStyle(HSSFSheet sheet, CellRangeAddress address, HSSFCellStyle cellStyle) {
        for (int i = address.getFirstRow(); i <= address.getLastRow(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = address.getFirstColumn(); j <= address.getLastColumn(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, j);
                cell.setCellStyle(cellStyle);
            }
        }
    }
    
    /**
     * 保存Excel
     * 
     * @param filePath 保存路径
     * @param fileName 保存文件名
     */
    public void saveExcel(String filePath, String fileName) {
        try {
            FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
            workbook.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
