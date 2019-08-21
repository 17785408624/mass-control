package com.example.service.impl;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.resultsparam.ExpertInfoResults;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.ExpertInfoEntity;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserInfoAuditEntity;
import com.example.mapper.UserInfoAuditMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional(rollbackFor=Exception.class)
@Service
public class UserInfoAuditServiceImpl implements UserInfoService {
    @Autowired
    UserInfoAuditMapper userInfoAuditMapper;
    @Autowired
    UserMapper userMapper;
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
    //查询用户提交申请的审核信息
    @Override
    public List<UserInfoAuditEntity> selectUserInfoAuditByUid(int user_id_add) {
        return userInfoAuditMapper.selectUserInfoAuditByUid(user_id_add);
    }
    //查询用户是否已提交过审核信息
    @Override
    public boolean findUserInfoAuditSubmitState(int user_id_add) {
        List<UserInfoAuditEntity> list=userInfoAuditMapper.
                selectUserInfoAuditByUid(user_id_add);//查询用户提交的审核信息
        if(list==null&&list.size()<1){
            return false;
        }else{
            return true;
        }

    }
    //查询用户提交的第一条审核信息
    @Override
    public UserInfoAuditEntity findUserInfoAuditSubmitFirst(int user_id_add) {
        return userInfoAuditMapper.selectUserInfoAuditFirstByUid(user_id_add);
    }
    //查询用户最近提交的一条审核信息
    @Override
    public UserInfoAuditEntity findUserInfoAuditRecentlyByUid(int user_id_add) {
        return userInfoAuditMapper.
                selectUserInfoAuditRecentlyByUid(user_id_add);
    }

    //查询审核专家信息申请列表
    @Override
    public List<Map<String, Object>> findUserInfoAuditExpert(int user_info_audit_state, int user_info_audit_type) {
        return userInfoAuditMapper.selectUserInfoAuditExpertByStateAType(user_info_audit_state,user_info_audit_type);
    }

    //审核用户初次提交的信息
    @Override
    public boolean operationUserInfoAuditFirst(int info_id, int user_info_audit_state) {
        userInfoAuditMapper.updateUserInfoAuditStateByinfoId(
                info_id,user_info_audit_state);//改变审核的状态
        if(user_info_audit_state==3){//审核通过
            userMapper.updateUserInfoIdAUserStateByinfo_id(info_id, 2);//修改用户的状态和现存信息
        }
        return true;
    }
    //审核用户提交的信息
    @Override
    public boolean operationUserInfoAudit(int info_id, int user_info_audit_state) {
        userInfoAuditMapper.updateUserInfoAuditStateByinfoId(
                info_id,user_info_audit_state);//改变审核的状态
        if(user_info_audit_state==3){//审核通过
            //修改用户现存信息
            userMapper.updateUserInfoIdAUserStateByinfo_id(info_id, 0);
        }
        return true;
    }
    //查询专家完整信息
    @Override
    public ExpertInfoResults findExpertInfoFull(int expert_info_id) {
        ExpertInfoResults eir=new ExpertInfoResults();
        ExpertInfoEntity eie=
                userInfoAuditMapper.selectExpertInfoFull(expert_info_id);
        //照片id
        Integer expert_info_picture_file_info_id=eie.getExpert_info_picture_file_info_id();

        if (expert_info_picture_file_info_id!=null){
            //营业执照文件
            FileInfoeEntity expert_info_picture_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(expert_info_picture_file_info_id);
            eir.setExpert_info_picture_file(expert_info_picture_file);
        }
        //附件文件id
        Integer expert_info_jadjunct_file_info_id=eie.getExpert_info_jadjunct_file_info_id();
        if(expert_info_jadjunct_file_info_id!=null){
            //附件文件
            FileInfoeEntity expert_info_jadjunct_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(expert_info_jadjunct_file_info_id);
            eir.setExpert_info_jadjunct_file(expert_info_jadjunct_file);
        }
        return eir;
    }
}
