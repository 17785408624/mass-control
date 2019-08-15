package com.example.entity.user;

/**
 * 机构信息，专业人员
 */
public class OrganizationInfoCareermanEntity {
    private int organization_info_careerman_id;//数据主键
    private int organization_info_id;//所属机构信息id
    private String organization_careerman_name;//人员姓名
    private int organization_profession;//人员专业 1采矿工程2通风安全3供电4四大件5水文地质6总平面工程7造价8环保节能9土建工程
    private String organization_performance;//人员业绩

    public int getOrganization_info_careerman_id() {
        return organization_info_careerman_id;
    }

    public void setOrganization_info_careerman_id(int organization_info_careerman_id) {
        this.organization_info_careerman_id = organization_info_careerman_id;
    }

    public int getOrganization_info_id() {
        return organization_info_id;
    }

    public void setOrganization_info_id(int organization_info_id) {
        this.organization_info_id = organization_info_id;
    }


    public int getOrganization_profession() {
        return organization_profession;
    }

    public void setOrganization_profession(int organization_profession) {
        this.organization_profession = organization_profession;
    }

    public String getOrganization_performance() {
        return organization_performance;
    }

    public void setOrganization_performance(String organization_performance) {
        this.organization_performance = organization_performance;
    }

    public String getOrganization_careerman_name() {
        return organization_careerman_name;
    }

    public void setOrganization_careerman_name(String organization_careerman_name) {
        this.organization_careerman_name = organization_careerman_name;
    }
}
