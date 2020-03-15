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

    /**
     * 导出专家信息数据为excel文件
     * @param os 输出流
     * @param user_info_audit_state 审核状态 1未审核 2拒绝 3通过 为0视为不设置此条件
     * @param user_info_audit_type 审核类型 1初审 2变更信息审核 为0视为不设置此条件
     * @return
     */
    Boolean exportExcelExpertAudit(OutputStream os, Integer user_info_audit_state,Integer user_info_audit_type);

    /**
     * 导出第三方机构信息数据为excel文件
     * @param os 输出流
     * @param user_info_audit_state 审核状态 1未审核 2拒绝 3通过 为0视为不设置此条件
     * @param user_info_audit_type 审核类型 1初审 2变更信息审核 为0视为不设置此条件
     * @return
     */
    Boolean exportExcelOrganizationAudit(OutputStream os, Integer user_info_audit_state,Integer user_info_audit_type);

}
