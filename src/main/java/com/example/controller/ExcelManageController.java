package com.example.controller;

import com.example.service.ExcelManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
@Controller
@RequestMapping("ExcelManage")
public class ExcelManageController {
    @Autowired
    private ExcelManageService excelManage;


    /**
     * 导出excel表
     * @param response
     * @param param
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestBody Map param) {
        Integer typeCode=Integer.valueOf(param.get("typeCode").toString());
        excelManage.exportExcel(response,typeCode);
    }
    @RequestMapping("/exportExcelEx")
    public void exportExcel1(HttpServletResponse response, HttpServletRequest reqeust) {
        String param=reqeust.getParameter("typeCode");
        Integer typeCode=Integer.valueOf(param);
        excelManage.exportExcel(response,typeCode);
    }

    /**
     * 导出专家审核信息为excel文件
     * @param response
     * @param reqeust
     */
    @RequestMapping("/exportExcelExpertAudit")
    public void exportExcelExpertAudit(HttpServletResponse response, HttpServletRequest reqeust) {
        String user_info_audit_state=reqeust.getParameter("user_info_audit_state");//审核状态 1未审核 2拒绝 3通过 为0视为不设置此条件
        String user_info_audit_type=reqeust.getParameter("user_info_audit_type");//审核类型 1初审 2变更信息审核 为0视为不设置此条件
        OutputStream os = null;
        try {
            os =response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 下载文件的默认名称
        try {
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(exportExcelType.getTypeName() + ".xls", "utf-8"));
            response.setHeader("Content-Disposition","attachment;filename="+ java.net.URLEncoder.encode("专家审核信息"+".xls", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        excelManage.exportExcelExpertAudit(os,Integer.valueOf(user_info_audit_state),Integer.valueOf(user_info_audit_type));
    }

    /**
     * 导出第三方机构审核信息为excel文件
     * @param response
     * @param reqeust
     */
    @RequestMapping("/exportExcelOrganizationAudit")
    public void exportExcelOrganizationAudit(HttpServletResponse response, HttpServletRequest reqeust) {
        String user_info_audit_state=reqeust.getParameter("user_info_audit_state");//审核状态 1未审核 2拒绝 3通过 为0视为不设置此条件
        String user_info_audit_type=reqeust.getParameter("user_info_audit_type");//审核类型 1初审 2变更信息审核 为0视为不设置此条件
        OutputStream os = null;
        try {
            os =response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 下载文件的默认名称
        try {
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(exportExcelType.getTypeName() + ".xls", "utf-8"));
            response.setHeader("Content-Disposition","attachment;filename="+ java.net.URLEncoder.encode("第三方机构审核信息"+".xls", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        excelManage.exportExcelOrganizationAudit(os,Integer.valueOf(user_info_audit_state),Integer.valueOf(user_info_audit_type));
    }

}
