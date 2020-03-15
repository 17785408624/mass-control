package com.example.service.impl;


import com.example.common.exceptiondefine.ExcelTypeCreateException;
import com.example.entity.ExportExcel.ExcelDataEntity;
import com.example.entity.ExportExcel.ExcelType;
import com.example.mapper.UserInfoAuditMapper;
import com.example.service.ExcelManageService;
import com.example.service.vice.ExcelVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class ExcelManageImpl implements ExcelManageService {
    @Autowired
    ExcelVice excelVice;
    @Autowired
    UserInfoAuditMapper userInfoAuditMapper;
    //

    public Boolean outputExcel(OutputStream os,
                               Integer typeCode,
                               Integer index,
                               Integer num) {
        ExcelType exportExcelType = null;//excel导出枚举类型
        try {
            exportExcelType = ExcelType.createToTypeCode(typeCode);
        } catch (ExcelTypeCreateException e) {
            e.printStackTrace();
        }
        if (typeCode == ExcelType.UserInfoAudit_NotRefer_Expert.getTypeCode()) {//用户审核申请信息数据列表
            List listM = userInfoAuditMapper.selectUserInfoAuditOrganizationByStateATypeExportExcel(
                    null, null, null, index, num);
            ExcelDataEntity ede = new ExcelDataEntity(listM, exportExcelType);

            try {
                excelVice.exportExcel(os, ede, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i = 1;
        }
        return null;
    }

    @Override
    public Boolean exportExcel(HttpServletResponse response, Integer typeCode, Integer index, Integer num) {

        return null;
    }

    @Override
    public Boolean exportExcel(HttpServletResponse response, Integer typeCode) {
        ExcelType exportExcelType = null;//excel信息枚举类型
        List listM = null;
        try {
            exportExcelType = ExcelType.createToTypeCode(typeCode);
        } catch (ExcelTypeCreateException e) {
            e.printStackTrace();
        }
        if (typeCode == ExcelType.UserInfoAudit_Expert_all.getTypeCode()) {//专家用户全部审核信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 0, 0);//专家用户全部审核信息
        }else if(typeCode == ExcelType.UserInfoAudit_Expert.getTypeCode()){//初审审核-专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 0, 1);//初审审核-专家用户信息
        }else if (typeCode == ExcelType.UserInfoAudit_NotRefer_Expert.getTypeCode()) {//变更审核-专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 1, 2);//变更审核-专家用户信息
        }else if (typeCode == ExcelType.UserInfoAudit_NotRefer_Expert.getTypeCode()) {//初审未审核的专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 1, 1);//初审未审核的专家用户信息
        } else if (typeCode == ExcelType.UserInfoAudit_Pass_Expert.getTypeCode()) {//初审已通过审核的专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 3, 1);//初审已通过审核的专家用户信息
        } else if (typeCode == ExcelType.UserInfoAudit_Refuse_Expert.getTypeCode()) {//初审已拒绝的用户审核信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 2, 1);//初审已拒绝的用户审核信息
        }else if (typeCode == ExcelType.UserInfoAuditChange_NotRefer_Expert.getTypeCode()) {//变更审核-未审核的专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 1, 2);//变更审核-未审核的专家用户信息
        }else if (typeCode == ExcelType.UserInfoAuditChange_Pass_Expert.getTypeCode()) {//变更审核-已通过的专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 3, 2);//变更审核-已通过的专家用户信息
        }else if (typeCode == ExcelType.UserInfoAuditChange_Refuse_Expert.getTypeCode()) {//变更审核-已拒绝的专家用户信息
            listM = userInfoAuditMapper.
                    selectExperInfoByQueryFields(exportExcelType.getQueryFields(), 2, 2);//变更审核-已拒绝的专家用户信息
        };

        ExcelDataEntity ede = new ExcelDataEntity(listM, exportExcelType);
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName=null;
        try {
            fileName= new String(exportExcelType.getTypeName().getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 下载文件的默认名称
        try {
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(exportExcelType.getTypeName() + ".xls", "utf-8"));
            response.setHeader("Content-Disposition","attachment;filename="+ java.net.URLEncoder.encode(fileName+".xls", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            excelVice.exportExcel(response.getOutputStream(), ede);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}
