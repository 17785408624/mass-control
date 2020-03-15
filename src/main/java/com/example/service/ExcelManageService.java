package com.example.service;

import com.example.entity.ExportExcel.ExcelDataEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public interface ExcelManageService {
    /**
     * 输出excel文件
     * @param os 输出流对象
     * @param typeCode 导出excel的类型
     * @param index 导出数据开始索引
     * @param num 导出条数
     * @return
     */
    /**
     * 使用浏览器导出excel数据文件
     * @param response
     * @param typeCode
     * @param index
     * @param num
     * @return
     */
    Boolean exportExcel(HttpServletResponse response, Integer typeCode, Integer index, Integer num);
    Boolean exportExcel(HttpServletResponse response, Integer typeCode);
}
