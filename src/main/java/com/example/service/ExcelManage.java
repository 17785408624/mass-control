package com.example.service;

import com.example.entity.ExportExcel.ExcelDataEntity;

import java.io.OutputStream;

public interface ExcelManage {
    /**
     * 输出excel文件
     * @param os 输出流对象
     * @param typeCode 导出excel的类型
     * @param index 导出数据开始索引
     * @param num 导出条数
     * @return
     */
    Boolean outputExcel(OutputStream os,Integer typeCode,Integer index,Integer num);
}
