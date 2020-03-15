package com.example.entity.ExportExcel;

import com.example.common.exceptiondefine.ExcelTypeCreateException;

import java.util.Map;

/**
 * excel信息枚举类型
 */
public enum ExcelType {

    UserInfoAudit_Expert_all(
            1, new String[]{"用户编号","申请时间","用户手机号", "用户注册时间", "用户角色", "用户状态", "专家名", "工作电话","身份证号", "单位名", "单位地址","邮编", "单位电话", "毕业学校", "邮箱", "学历", "所学专业", "从事专业", "申报专业-技术报告咨询审查类", "申报专业-安全生产检查类", "地区", "审核状态",  "审核类型"},
            "专家用户审核信息",
            "expertDate"),//用户审核申请信息数据列表 导出excel类型
    UserInfoAudit_Expert(
            2,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类","审核状态"},
            "初审审核-专家用户信息",
            "expertDate"),
    UserInfoAuditChange_Expert(
            3,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类","审核状态"},
            "变更审核-专家用户信息",
            "expertDate"),
    UserInfoAudit_NotRefer_Expert(
            4,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类"},
            "初审审核-未审核的专家用户信息",
            "expertDate"),
    UserInfoAudit_Pass_Expert(
            5,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类"},
            "初审审核-已通过审核的专家用户信息", "expertDate"),
    UserInfoAudit_Refuse_Expert(
            6,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类"},
            "初审审核-已拒绝的用户审核信息",
            "expertDate"),
    UserInfoAuditChange_NotRefer_Expert(
            7,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类"},
            "变更审核-未审核的专家用户信息",
            "expertDate"),
    UserInfoAuditChange_Pass_Expert(
            8,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类"},
            "变更审核-已通过的专家用户信息",
            "expertDate"),
    UserInfoAuditChange_Refuse_Expert(
            9,
            new String[]{"用户编号","用户手机号","学历","申请时间","用户注册时间","专家名","身份证号","所学专业", "从事专业", "申报专业-技术报告咨询审查类"},
            "变更审核-已拒绝的专家用户信息",
            "expertDate"),
    ;
    private Integer typeCode;//类型code
    private String[] typeTitle;//类型表头
    private String typeName;//类型名字
    private String dataType;//数据类型
    private String[] queryFields;//excel表需要查询的字段名

    /**
     * 获取需要查询的字段
     *
     * @return
     */
    public String[] getQueryFields() {
        Map<String, String> fieldMap = null;//excel标题头与数据库字段名的映射集合
        switch (this.getDataType()) {
            case "expertDate":
                fieldMap = ExcelFieldMap.getExpertFieldMap();
                break;
        }
        ;
        String[] fields = new String[this.getTypeTitle().length];//excel表需要的字段名
        for (int i = 0; i < this.getTypeTitle().length; i++) {//循环excel表字段标题名得到映射的数据库字段名
            String key = this.getTypeTitle()[i];
            String fi = fieldMap.get(key);
            fields[i] = fieldMap.get(key);//得到map中映射的字段名
        }
        return fields;
    }

    //构造方法
    ExcelType(Integer typeCode, String[] typeTitle, String typeName, String dataType) {
        this.typeCode = typeCode;
        this.typeTitle = typeTitle;
        this.typeName = typeName;
        this.dataType = dataType;
        queryFields = getQueryFields();

    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String[] getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String[] typeTitle) {
        this.typeTitle = typeTitle;
    }

    /**
     * 通过类型code创建excel导出枚举类型
     *
     * @param typeCode 类型code
     * @return
     * @throws ExcelTypeCreateException excel导出类型实体类创建异常
     */
    public static ExcelType createToTypeCode(Integer typeCode) throws ExcelTypeCreateException {
        for (ExcelType eet : ExcelType.values()
        ) {
            if (eet.getTypeCode().equals(typeCode)) {
                return eet;

            }
        }
        throw new ExcelTypeCreateException("通过code找不到创建的类型");
    }

    ;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }}
