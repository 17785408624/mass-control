package com.example.entity.resultsparam;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;

import java.util.List;
import com.example.entity.user.OrganizationInfoEntity;
/**
 * 第三方机构审核信息返回封装
 */
public class OrganizationAuditResults {
    private Integer user_id;
    private OrganizationInfoEntity OrganizationInfoEntity;//第三方机构信息
    private FileInfoeEntity organization_license_file;//营业执照
    private List<FileInfoeEntity> organization_adjunct_file;//附件文件
    private List<OrganizationInfoCareermanEntity> oiceList;//机构专业人员信息

    public OrganizationAuditResults() {
    }

    public com.example.entity.user.OrganizationInfoEntity getOrganizationInfoEntity() {
        return OrganizationInfoEntity;
    }

    public void setOrganizationInfoEntity(com.example.entity.user.OrganizationInfoEntity organizationInfoEntity) {
        OrganizationInfoEntity = organizationInfoEntity;
    }

    public FileInfoeEntity getOrganization_license_file() {
        return organization_license_file;
    }

    public void setOrganization_license_file(FileInfoeEntity organization_license_file) {
        this.organization_license_file = organization_license_file;
    }

    public List<FileInfoeEntity> getOrganization_adjunct_file() {
        return organization_adjunct_file;
    }

    public void setOrganization_adjunct_file(List<FileInfoeEntity> organization_adjunct_file) {
        this.organization_adjunct_file = organization_adjunct_file;
    }

    public List<OrganizationInfoCareermanEntity> getOiceList() {
        return oiceList;
    }

    public void setOiceList(List<OrganizationInfoCareermanEntity> oiceList) {
        this.oiceList = oiceList;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
