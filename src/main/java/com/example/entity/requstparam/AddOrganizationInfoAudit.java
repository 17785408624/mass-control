package com.example.entity.requstparam;

import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;

import java.util.List;

public class AddOrganizationInfoAudit {
    List<OrganizationInfoCareermanEntity> oceList;
    OrganizationInfoEntity organizationInfo;

    public List<OrganizationInfoCareermanEntity> getOceList() {
        return oceList;
    }

    public void setOceList(List<OrganizationInfoCareermanEntity> oceList) {
        this.oceList = oceList;
    }

    public OrganizationInfoEntity getOrganizationInfo() {
        return organizationInfo;
    }

    public void setOrganizationInfo(OrganizationInfoEntity organizationInfo) {
        this.organizationInfo = organizationInfo;
    }
}
