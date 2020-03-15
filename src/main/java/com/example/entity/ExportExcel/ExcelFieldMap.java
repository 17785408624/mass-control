package com.example.entity.ExportExcel;

import java.util.HashMap;
import java.util.Map;

public class ExcelFieldMap {
    static String[]expertExcelFiledName=new String[]{//专家数据字段名
            "user_id","user_mobile_phone","user_register_time","user_role","user_state","expert_info_name",
            "expert_info_worktelephone","expert_info_idcard","expert_info_companyname","expert_info_companysite",
            "expert_info_postcode","expert_info_companyphone","expert_info_school","expert_info_mail","expert_info_education",
            "expert_info_learnmajor","expert_info_workmajor","expert_info_declaredesign_design","expert_info_declaredesign_safety",
            "expert_info_area","user_info_audit_state","user_info_audit_addtime","user_info_audit_type"
    };

    static String[]expertExcelTitleName=new String[]{//专家数据表标题名
            "用户编号","用户手机号","用户注册时间","用户角色","用户状态","专家名","工作电话","身份证号","单位名","单位地址",
            "邮编","单位电话","毕业学校","邮箱","学历","所学专业","从事专业","申报专业-技术报告咨询审查类","申报专业-安全生产检查类",
            "地区","审核状态","申请时间","审核类型"
    };
    //static BiMap<String,String> expertFieldMap = HashBiMap.create();
    //expertFieldMap.inverse();//映射反转，即key/value互相切换的映射
    static Map<String,String> expertFieldMap=new HashMap<String,String>();//专家表标题与数据库字段的映射键值对
    static{
        for(int i=0;i<expertExcelFiledName.length;i++){
            expertFieldMap.put(expertExcelFiledName[i],expertExcelTitleName[i]);
            expertFieldMap.put(expertExcelTitleName[i],expertExcelFiledName[i]);
        }
    };
    //获取专家表标题与数据库字段的映射键值对
    public static Map getExpertFieldMap(){
        return expertFieldMap;
    }

}
