package com.example.entity.user;

import java.util.List;

/**
 * 第三方机构信息
 */
public class OrganizationInfoEntity {
    private int organization_info_id;//数据主键
    private String organization_name;//单位名称
    private String organization_code;//统一社会信信用码
    private String organization_area;//地区
    private String organization_site;//地址
    private String organization_postcode;//邮编
    private String organization_telephone;//单位电话
    private String organization_mail;//单位邮箱
    private String organization_principal_name;//法定代表人名字
    private String organization_principal_mobilephone;//法定代表人移动电话
    private String organization_performance_content;//组织业绩文本内容
    private Integer organization_license_file_info_id;//营业执照文件id
    private String organization_adjunct_file_info_id;//附件文件id

    public int getOrganization_info_id() {
        return organization_info_id;
    }

    public void setOrganization_info_id(int organization_info_id) {
        this.organization_info_id = organization_info_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getOrganization_code() {
        return organization_code;
    }

    public void setOrganization_code(String organization_code) {
        this.organization_code = organization_code;
    }

    public String getOrganization_area() {
        return organization_area;
    }

    public void setOrganization_area(String organization_area) {
        this.organization_area = organization_area;
    }

    public String getOrganization_site() {
        return organization_site;
    }

    public void setOrganization_site(String organization_site) {
        this.organization_site = organization_site;
    }

    public String getOrganization_postcode() {
        return organization_postcode;
    }

    public void setOrganization_postcode(String organization_postcode) {
        this.organization_postcode = organization_postcode;
    }

    public String getOrganization_telephone() {
        return organization_telephone;
    }

    public void setOrganization_telephone(String organization_telephone) {
        this.organization_telephone = organization_telephone;
    }

    public String getOrganization_mail() {
        return organization_mail;
    }

    public void setOrganization_mail(String organization_mail) {
        this.organization_mail = organization_mail;
    }

    public String getOrganization_principal_name() {
        return organization_principal_name;
    }

    public void setOrganization_principal_name(String organization_principal_name) {
        this.organization_principal_name = organization_principal_name;
    }

    public String getOrganization_principal_mobilephone() {
        return organization_principal_mobilephone;
    }

    public void setOrganization_principal_mobilephone(String organization_principal_mobilephone) {
        this.organization_principal_mobilephone = organization_principal_mobilephone;
    }

    public String getOrganization_performance_content() {
        return organization_performance_content;
    }

    public void setOrganization_performance_content(String organization_performance_content) {
        this.organization_performance_content = organization_performance_content;
    }


    public Integer getOrganization_license_file_info_id() {
        return organization_license_file_info_id;
    }

    public void setOrganization_license_file_info_id(Integer organization_license_file_info_id) {
        this.organization_license_file_info_id = organization_license_file_info_id;
    }

    public String getOrganization_adjunct_file_info_id() {
        return organization_adjunct_file_info_id;
    }

    public void setOrganization_adjunct_file_info_id(String organization_adjunct_file_info_id) {
        this.organization_adjunct_file_info_id = organization_adjunct_file_info_id;
    }
}
