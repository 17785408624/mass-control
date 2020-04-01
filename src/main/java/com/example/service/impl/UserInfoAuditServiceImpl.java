package com.example.service.impl;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.resultsparam.ExpertInfoResults;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.*;
import com.example.mapper.UserInfoAuditMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserInfoAuditService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional(rollbackFor=Exception.class)
@Service
public class UserInfoAuditServiceImpl implements UserInfoAuditService {
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

    /**
     * 查询第三方机构完整信息
     * @param organization_info_id
     * @return
     */
    @Override
    public OrganizationAuditResults findOrganizationInfoFull(int organization_info_id) {
        OrganizationAuditResults oar=new OrganizationAuditResults();//返回信息封装类
        //查询第三方机构信息
        OrganizationInfoEntity oie= userInfoAuditMapper.selectOrganizationInfoEntityFull(organization_info_id);
        //查询第三方机构人员信息
        List<OrganizationInfoCareermanEntity>oiceList=
                userInfoAuditMapper.selectOrganizationInfoCareermanFullByOrgInfoId(organization_info_id);
        //营业执照文件id
        String organization_license_file_info_id=oie.getOrganization_license_file_info_id().toString();

        if (organization_license_file_info_id!=null){
            //营业执照文件
            List<FileInfoeEntity> organization_license_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(organization_license_file_info_id);
            oar.setOrganization_license_file(organization_license_file.get(0));
        }
        //附件文件id
        String organization_adjunct_file_info_id=oie.getOrganization_adjunct_file_info_id();
        if(organization_adjunct_file_info_id!=null){
            //附件文件
            List<FileInfoeEntity> organization_adjunct_file=
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
        if(list==null||list.size()<1){
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
        UserInfoAuditEntity uie=userInfoAuditMapper.
                selectUserInfoAuditRecentlyByUid(user_id_add);
        return uie;
    }

    //查询专家审核信息申请列表
    @Override
    public List<Map<String, Object>> findUserInfoAuditExpert(int user_info_audit_state, int user_info_audit_type) {
        return userInfoAuditMapper.selectUserInfoAuditExpertByStateAType(user_info_audit_state,user_info_audit_type);
    }
    //分页模糊条件查询专家审核信息列表
    @Override
    public List<Map<String, Object>> findUserInfoAuditExpert(PageRequest pageRequest,int user_info_audit_state, int user_info_audit_type, String condition, OrderRequest[]orderRequests) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return userInfoAuditMapper.selectLikeUserInfoAuditExpertByStateAType(user_info_audit_state,user_info_audit_type,condition,orderRequests);
    }
   //模糊条件分页查询审核第三方机构信息列表
    @Override
    public List<Map<String, Object>> findUserInfoAuditOrganization(PageRequest pageRequest, int user_info_audit_state, int user_info_audit_type, String condition, OrderRequest[] orderRequests) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return userInfoAuditMapper.selectLikeUserInfoAuditOrganizationByStateAType(user_info_audit_state,user_info_audit_type,condition,orderRequests);
    }

    //分页查询专家审核信息列表
    @Override
    public List<Map<String, Object>> findUserInfoAuditExpert(PageRequest pageRequest, int user_info_audit_state, int user_info_audit_type) {
        List<Map<String, Object>> uiae=userInfoAuditMapper.
                selectUserInfoAuditExpertByStateAType
                        (user_info_audit_state,user_info_audit_type);
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return userInfoAuditMapper.
                selectUserInfoAuditExpertByStateAType
                        (user_info_audit_state,user_info_audit_type);
    }



    //审核用户提交的信息
    @Override
    public boolean operationUserInfoAudit(int info_id, int user_info_audit_state,boolean isCertification) {
        userInfoAuditMapper.updateUserInfoAuditStateByinfoId(
                info_id,user_info_audit_state,null);//改变审核的状态
        int user_state=0;

        if(user_info_audit_state==3){//审核通过
            if(isCertification){//是否为初次审核
                user_state=2;
            }
            //修改用户现存信息
            userMapper.updateUserInfoIdAUserStateByinfo_id(info_id, user_state);
        }
        return true;
    }
    //审核用户提交的信息
    @Override
    public boolean operationUserInfoAudit(int info_id, int user_info_audit_state,boolean isCertification,
                                          String user_info_audit_describe) {
        userInfoAuditMapper.updateUserInfoAuditStateByinfoId(
                info_id,user_info_audit_state,user_info_audit_describe);//改变审核的状态
        int user_state=0;
        if(user_info_audit_state==3){//审核通过
            if(isCertification){//是否为初次审核
                user_state=2;
            }
            //修改用户现存信息
            userMapper.updateUserInfoIdAUserStateByinfo_id(info_id, user_state);
        }
        return true;
    }

    //查询专家完整信息
    @Override
    public ExpertInfoResults findExpertInfoFull(int expert_info_id) {
        ExpertInfoResults eir=new ExpertInfoResults();
        ExpertInfoEntity eie=
                userInfoAuditMapper.selectExpertInfoFull(expert_info_id);
        eir.setExpertInfoEntity(eie);
        //照片id
        String expert_info_picture_file_info_id=eie.getExpert_info_picture_file_info_id().toString();

        if (expert_info_picture_file_info_id!=null){
            //营业执照文件
            List<FileInfoeEntity> expert_info_picture_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(expert_info_picture_file_info_id);
            eir.setExpert_info_picture_file(expert_info_picture_file.get(0));
        }
        //附件文件id
        String expert_info_jadjunct_file_info_id=eie.getExpert_info_jadjunct_file_info_id();
        if(expert_info_jadjunct_file_info_id!=null){
            //附件文件
            List<FileInfoeEntity> expert_info_jadjunct_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(expert_info_jadjunct_file_info_id);
            eir.setExpert_info_jadjunct_file(expert_info_jadjunct_file);
        }
        return eir;
    }
    //查询第三方机构现存完整信息
    @Override
    public OrganizationAuditResults findOrganizationInfoFullNowsave(int user_id) {
        UserEntity ue=userMapper.selectUserEntityByUId(user_id);//查询用户信息
        int organization_info_id=ue.getInfo_id();//用户信息id
        OrganizationAuditResults oar=new OrganizationAuditResults();//返回信息封装类
        //查询第三方机构信息
        OrganizationInfoEntity oie= userInfoAuditMapper.
                selectOrganizationInfoEntityFull(organization_info_id);
        //查询第三方机构人员信息
        List<OrganizationInfoCareermanEntity>oiceList=
                userInfoAuditMapper.
                        selectOrganizationInfoCareermanFullByOrgInfoId(organization_info_id);
        //营业执照文件id
        String organization_license_file_info_id=oie.getOrganization_license_file_info_id()==null?null:oie.getOrganization_license_file_info_id().toString();

        if (organization_license_file_info_id!=null){
            //营业执照文件
            List<FileInfoeEntity> organization_license_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(organization_license_file_info_id);
            oar.setOrganization_license_file(organization_license_file.get(0));
        }
        //附件文件id
        String organization_adjunct_file_info_ids=oie.getOrganization_adjunct_file_info_id();
        if(organization_adjunct_file_info_ids!=null){
            //附件文件
           List<FileInfoeEntity>  organization_adjunct_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(organization_adjunct_file_info_ids);
            oar.setOrganization_adjunct_file(organization_adjunct_file);
        }
        oar.setOiceList(oiceList);
        oar.setOrganizationInfoEntity(oie);
        return oar;
    }
    //查询专家现存完整信息
    @Override
    public ExpertInfoResults findExpertInfoFullNowsave(int user_id) {
        UserEntity ue=userMapper.selectUserEntityByUId(user_id);//查询用户信息
        int expert_info_id=ue.getInfo_id();//用户信息id
        ExpertInfoResults eir=new ExpertInfoResults();
        ExpertInfoEntity eie=
                userInfoAuditMapper.selectExpertInfoFull(expert_info_id);
        eir.setExpertInfoEntity(eie);
        //照片id
        String expert_info_picture_file_info_id=eie.getExpert_info_picture_file_info_id().toString();

        if (expert_info_picture_file_info_id!=null){
            //营业执照文件
            List<FileInfoeEntity> expert_info_picture_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(expert_info_picture_file_info_id);
            eir.setExpert_info_picture_file(expert_info_picture_file.get(0));
        }
        //附件文件id
        String expert_info_jadjunct_file_info_id=eie.getExpert_info_jadjunct_file_info_id();
        if(expert_info_jadjunct_file_info_id!=null){
            //附件文件
            List<FileInfoeEntity> expert_info_jadjunct_file=
                    userInfoAuditMapper.
                            selectFileInfoeEntityFullByFileId(expert_info_jadjunct_file_info_id);
            eir.setExpert_info_jadjunct_file(expert_info_jadjunct_file);
        }
        return eir;
    }
}
