package com.example.entity.ExportExcel;

import java.util.HashMap;
import java.util.Map;

public class ExcelFieldMap {
    static String[]expertExcelFiledName=new String[]{//专家数据字段名
            "user_id","user_mobile_phone","user_register_time","user_role","user_state","expert_info_name",
            "expert_info_worktelephone","expert_info_idcard","expert_info_companyname","expert_info_companysite",
            "expert_info_postcode","expert_info_companyphone","expert_info_school","expert_info_mail","expert_info_education",
            "expert_info_learnmajor","expert_info_workmajor","expert_info_declaredesign_design","expert_info_declaredesign_safety",
            "expert_info_area","user_info_audit_state","user_info_audit_addtime","user_info_audit_type","expert_info_duty","expert_info_qualification",
            "expert_info_gender"
    };

    static String[]expertExcelTitleName=new String[]{//专家数据表标题名
            "用户编号","用户手机号","用户注册时间","用户角色","用户状态","专家名","工作电话","身份证号","单位名","单位地址",
            "邮编","单位电话","毕业学校","邮箱","学历","所学专业","从事专业","申报专业-技术报告咨询审查类","申报专业-安全生产检查类",
            "地区","审核状态","申请时间","审核类型","职务","职业资格","性别"
    };
    static String[]organizationExcelFiledName=new String[]{//第三方机构数据字段名
            "user_id","user_mobile_phone","user_register_time","user_role","user_state","organization_name",
            "organization_code","organization_site","organization_postcode","organization_telephone","organization_mail",
            "organization_principal_name","organization_principal_mobilephone","user_info_audit_state",
            "user_info_audit_addtime","user_info_audit_type"
    };
    static String[]organizationExcelTitleName=new String[]{//第三方机构数据字段名
            "用户编号","用户手机号","用户注册时间","用户角色","用户状态","机构/公司名",
            "组织社会信用码","机构地址","邮编","电话号码","机构邮箱",
            "法定代表人名字","法定代表人移动电话","审核状态","申请时间","审核类型"
    };
    //static BiMap<String,String> expertFieldMap = HashBiMap.create();
    //expertFieldMap.inverse();//映射反转，即key/value互相切换的映射
    static Map<String,String> expertFieldMap=new HashMap<String,String>();//专家信息表标题与数据库字段的映射键值对
    public static String []expertExcelTitle=new String[]{//默认导出专家excel表的标题
            "用户编号", "用户手机号", "学历", "申请时间", "用户注册时间", "专家名", "身份证号", "所学专业", "从事专业", "申报专业-技术报告咨询审查类", "申报专业-安全生产检查类","审核状态","单位名","职务","职业资格","性别"};
    public static String[]expertQueryFields;//默认查询专家信息的字段组
    public static String []organizationExcelTitle=new String[]{//默认导出专家excel表的标题
            "用户编号", "用户手机号", "用户注册时间", "用户角色", "用户状态", "机构/公司名", "组织社会信用码", "机构地址", "邮编", "电话号码", "机构邮箱", "法定代表人名字", "法定代表人移动电话", "审核状态", "申请时间"
           };
    public static String[]organizationQueryFields;//默认查询专家信息的字段组
    static Map<String,String> organizationFieldMap=new HashMap<String,String>();//第三方机构信息表标题与数据库字段的映射键值对
    static{
        for(int i=0;i<expertExcelFiledName.length;i++){//将信息表标题与数据库字段相互映射存入map
            expertFieldMap.put(expertExcelFiledName[i],expertExcelTitleName[i]);
            expertFieldMap.put(expertExcelTitleName[i],expertExcelFiledName[i]);
        }
        for(int i=0;i<organizationExcelFiledName.length;i++){//将信息表标题与数据库字段相互映射存入map
            organizationFieldMap.put(organizationExcelFiledName[i],organizationExcelTitleName[i]);
            organizationFieldMap.put(organizationExcelTitleName[i],organizationExcelFiledName[i]);
        }
        //通过标题映射默认查询专家信息的字段组
        expertQueryFields=new String[expertExcelTitle.length];
        for(int i=0;i<expertExcelTitle.length;i++){
            expertQueryFields[i]=expertFieldMap.get(expertExcelTitle[i]);
        }
        //通过标题映射默认查询第三方机构信息的字段组
        organizationQueryFields=new String[organizationExcelTitle.length];
        for(int i=0;i<organizationExcelTitle.length;i++){
            organizationQueryFields[i]=organizationFieldMap.get(organizationExcelTitle[i]);
        }
    };
    //获取专家表标题与数据库字段的映射键值对
    public static Map getExpertFieldMap(){
        return expertFieldMap;
    }
    //获取第三方机构表标题与数据库字段的映射键值对
    public static Map getOrganizationFieldMap(){
        return organizationFieldMap;
    }


}
