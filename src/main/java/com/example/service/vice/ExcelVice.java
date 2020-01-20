package com.example.service.vice;

import com.example.entity.ExportExcel.ExcelDataEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class ExcelVice {
    /**
     * 使用浏览器选择路径下载
     * @param response
     * @param fileName
     * @param data
     * @throws Exception
     */
    public  void exportExcel(HttpServletResponse response, String fileName, ExcelDataEntity data,Class rowDataClass) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "utf-8"));
        OutputStream os= response.getOutputStream();
        exportExcel(data, os,rowDataClass);
    }
    /**
     * 输出excel文件
     * @param os 输出流
     * @param data 输出数据
     */
    public  void exportExcel(OutputStream os, ExcelDataEntity data,Class rowDataClass) throws IOException {
        exportExcel(data, os,rowDataClass);

    }

    public  int generateExcel(ExcelDataEntity excelData, String path,Class rowDataClass) throws Exception {
        File f = new File(path);
        FileOutputStream out = new FileOutputStream(f);
        return exportExcel(excelData, out,rowDataClass);
    }

    private  int exportExcel(ExcelDataEntity data, OutputStream out,Class rowDataClass) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        int rowIndex = 0;
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            rowIndex = writeExcel(wb, sheet, data,rowDataClass);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //此处需要关闭 wb 变量
            out.close();
        }
        return rowIndex;
    }

    /**
     * 表不显示字段
     * @param wb
     * @param sheet
     * @param data
     * @return
     */
//    private static int writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
//        int rowIndex = 0;
//        writeTitlesToExcel(wb, sheet, data.getTitles());
//        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
//        autoSizeColumns(sheet, data.getTitles().size() + 1);
//        return rowIndex;
//    }

    /**
     * 表显示字段
     * @param wb
     * @param sheet
     * @param data
     * @return
     */
    private  int writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelDataEntity data,Class rowDataClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex, rowDataClass);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
        return rowIndex;
    }
    /**
     * 设置表头
     *
     * @param wb
     * @param sheet
     * @param titles
     * @return
     */
    private  int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;
        Font titleFont = wb.createFont();
        //设置字体
        titleFont.setFontName("simsun");
        //设置粗体
        titleFont.setBoldweight(Short.MAX_VALUE);
        //设置字号
        titleFont.setFontHeightInPoints((short) 14);
        //设置颜色
        titleFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle titleStyle = wb.createCellStyle();
        //水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置图案颜色
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        //设置图案样式
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        Row titleRow = sheet.createRow(rowIndex);
        titleRow.setHeightInPoints(25);
        colIndex = 0;
        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }

    /**
     * 设置内容
     *
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
    private  int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List rows, int rowIndex,Class rowDataClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(95, 95, 95, 117)));
//        for(List rowData : rows){
//
//        }
        return rowIndex;
    }
//    private  int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
//        int colIndex;
//        Font dataFont = wb.createFont();
//        dataFont.setFontName("simsun");
//        dataFont.setFontHeightInPoints((short) 14);
//        dataFont.setColor(IndexedColors.BLACK.index);
//
//        XSSFCellStyle dataStyle = wb.createCellStyle();
//        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//        dataStyle.setFont(dataFont);
//        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
//        for (List rowData : rows) {
//            Row dataRow = sheet.createRow(rowIndex);
//            dataRow.setHeightInPoints(25);
//            colIndex = 0;
//            for (Object cellData : rowData) {
//                Cell cell = dataRow.createCell(colIndex);
//                if (cellData != null) {
//                    cell.setCellValue(cellData.toString());
//                } else {
//                    cell.setCellValue("");
//                }
//                cell.setCellStyle(dataStyle);
//                colIndex++;
//            }
//            rowIndex++;
//        }
//        return rowIndex;
//    }

    /**
     * 自动调整列宽
     *
     * @param sheet
     * @param columnNumber
     */
    private  void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置边框
     *
     * @param style
     * @param border
     * @param color
     */
    private  void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, color);
    }

}
