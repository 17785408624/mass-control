package com.example.service.impl;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.mapper.UserInfoAuditMapper;
import com.example.service.UserInfoAuditOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional(rollbackFor=Exception.class)
@Service
public class UserInfoAuditOrganizationServiceImpl implements UserInfoAuditOrganizationService {
    @Autowired
    UserInfoAuditMapper userInfoAuditMapper;
    //查询第三方机构信息审核申请
    @Override
    public List<Map<String, Object>> findUserInfoAuditOrganization(int user_info_audit_state,
                                                                   int user_info_audit_type) {
        return userInfoAuditMapper.selectUserInfoAuditOrganizationByStateAType(user_info_audit_state,user_info_audit_type);
    }

    @Override
    public OrganizationAuditResults findOrganizationInfoFull(int organization_info_id) {
        OrganizationAuditResults oar=new OrganizationAuditResults();//返回信息封装类
        //查询第三方机构信息
        OrganizationInfoEntity oie= userInfoAuditMapper.selectOrganizationInfoEntityFull(organization_info_id);
        //查询第三方机构人员信息
        List<OrganizationInfoCareermanEntity>oiceList=
                userInfoAuditMapper.selectOrganizationInfoCareermanFullByOrgInfoId(organization_info_id);
        //营业执照文件id
        Integer organization_license_file_info_id=oie.getOrganization_license_file_info_id();

        if (organization_license_file_info_id!=null){
            //营业执照文件
            FileInfoeEntity organization_license_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(organization_license_file_info_id);
            oar.setOrganization_license_file(organization_license_file);
        }
        //附件文件id
        Integer organization_adjunct_file_info_id=oie.getOrganization_adjunct_file_info_id();
        if(organization_adjunct_file_info_id!=null){
            //附件文件
            FileInfoeEntity organization_adjunct_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(organization_adjunct_file_info_id);
            oar.setOrganization_adjunct_file(organization_adjunct_file);
        }
        oar.setOiceList(oiceList);
        oar.setOrganizationInfoEntity(oie);
        return oar;
    }
}
