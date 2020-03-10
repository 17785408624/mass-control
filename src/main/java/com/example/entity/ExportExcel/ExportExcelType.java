package com.example.entity.ExportExcel;

import com.example.common.exceptiondefine.ExportExcelTypeCreateException;

/**
 * excel导出枚举类型
 */
public enum  ExportExcelType {
    UserInfoAudit(1,new String[]{"数据编号","审核状态","审核添加时间","组织名","统一社会用码"},
            "用户审核申请信息")//用户审核申请信息数据列表 导出excel类型
    ;
    private Integer typeCode;//类型code
    private String[]typeTitle;//类型表头
    private String  typeName ;//类型名字

    ExportExcelType(Integer typeCode, String[] typeTitle,String typeName) {
        this.typeCode = typeCode;
        this.typeTitle = typeTitle;
        this.typeName=typeName;
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
     * @param typeCode 类型code
     * @return
     * @throws ExportExcelTypeCreateException excel导出类型实体类创建异常
     */
    public static ExportExcelType createToTypeCode(Integer typeCode) throws ExportExcelTypeCreateException{
        for (ExportExcelType eet:ExportExcelType.values()
             ) {
            if(eet.getTypeCode().equals(typeCode)){
                return eet;
            }
        }
        throw new ExportExcelTypeCreateException("通过code找不到创建的类型");
    };

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
