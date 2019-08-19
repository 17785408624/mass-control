package com.example.mapper;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserInfoAuditEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface UserInfoAuditMapper {
    /**
     * 查询第三方机构审核信息列表
     * 参数设置为0则视为不添加条件
     * @param user_info_audit_state //审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type  //审核类型 1初审 2变更审核
     * @return user_info_audit_id审核编号id  user_info_audit_state审核状态
     * user_info_audit_addtime 添加时间 user_info_audit_type审核类型
     * organization_name机构名 organization_code 机构社会信用代码
     * info_id 审核的资料信息id
     */
    List<Map<String,Object>> selectUserInfoAuditOrganizationByStateAType(
            @Param("user_info_audit_state") int user_info_audit_state,
            @Param("user_info_audit_type") int user_info_audit_type);

    /**
     * 查询第三方机构完整信息
     * @param organization_info_id
     * @return
     */
    OrganizationInfoEntity selectOrganizationInfoEntityFull(int organization_info_id);

    /**
     * 通过第三方机构id查询第三方机构专业人员信息
     * @param organization_info_id
     * @return
     */
    List<OrganizationInfoCareermanEntity> selectOrganizationInfoCareermanFullByOrgInfoId(
            int organization_info_id);

    /**
     * 通过文件id查询文件信息
     * @param file_info_id
     * @return
     */
    FileInfoeEntity selectFileInfoeEntityFullByFileId(int file_info_id);

    /**
     * 通过用户id查询审核信息
     * @param user_id_add
     * @return
     */
    List<UserInfoAuditEntity> selectUserInfoAuditByUid(int user_id_add);

    /**
     * 查询用户提交的第一条申请审核信息
     * @param user_id_idd
     * @return
     */
    UserInfoAuditEntity selectUserInfoAuditFirstByUid(int user_id_idd);

    /**
     * 查询专家审核信息列表
     * 参数设置为0则视为不添加条件
     * @param user_info_audit_state //审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type  //审核类型 1初审 2变更审核
     * @return user_info_audit_id审核编号id  user_info_audit_state审核状态
     *  user_info_audit_addtime添加时间  user_info_audit_type审核类型
     *  expert_info_name专家名  expert_info_idcard身份证号
     *  expert_info_companyname单位名  info_id审核的资料信息id
     */
    List<Map<String,Object>> selectUserInfoAuditExpertByStateAType(
            @Param("user_info_audit_state") int user_info_audit_state,
            @Param("user_info_audit_type") int user_info_audit_type);

}