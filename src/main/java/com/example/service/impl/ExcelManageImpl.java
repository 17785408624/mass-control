package com.example.service.impl;

import com.example.common.exceptiondefine.ExportExcelTypeCreateException;
import com.example.entity.ExportExcel.ExcelDataEntity;
import com.example.entity.ExportExcel.ExportExcelType;
import com.example.mapper.UserInfoAuditMapper;
import com.example.service.ExcelManage;
import com.example.service.vice.ExcelVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Exception.class)
@Service
public class ExcelManageImpl  implements ExcelManage {
    @Autowired
    ExcelVice excelVice;
    @Autowired
    UserInfoAuditMapper userInfoAuditMapper;
    //

    public Boolean outputExcel(OutputStream os,
                                 Integer typeCode,
                                 Integer index,
                                 Integer num) {
        ExportExcelType exportExcelType = null;//excel导出枚举类型
        try {
            exportExcelType=ExportExcelType.createToTypeCode(typeCode);
        } catch (ExportExcelTypeCreateException e) {
            e.printStackTrace();
        }
        if(typeCode==exportExcelType.getTypeCode()){//用户审核申请信息数据列表
            List listM=userInfoAuditMapper.selectUserInfoAuditOrganizationByStateATypeExportExcel(
                    null,null,null,index,num);
            ExcelDataEntity ede=new ExcelDataEntity(listM,exportExcelType);

            try {
                excelVice.exportExcel(os,ede,Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i=1;
        }
        return null;
    }

    @Override
    public Boolean exportExcel(HttpServletResponse response, Integer typeCode, Integer index, Integer num) {
        return null;
    }

    @Override
    public Boolean exportExcel(HttpServletResponse response, Integer typeCode) {
        return null;
    }
}
