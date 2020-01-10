package com.example.mapper;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.ExpertInfoEntity;
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
     * @return user_info_audit_id审核编号id  user_info_audit_state审核状态 1未审核 2拒绝 3 通过
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

    /**
     *
     * @param user_info_audit_state
     * @param user_info_audit_type
     * @param condition 模糊查询条件
     * @return
     */
    List<Map<String,Object>> selectLikeUserInfoAuditExpertByStateAType(
            @Param("user_info_audit_state") int user_info_audit_state,
            @Param("user_info_audit_type") int user_info_audit_type,
            @Param("condition") String condition);

    /**
     * 根据审核id改变审核状态
     * @param user_info_audit_state 审核状态 1未审核 2拒绝 3 通过
     * @param info_id 审核的资料信息id
     * @param user_info_audit_describe 审核描述
     * @return
     */
    int updateUserInfoAuditStateByinfoId(
            @Param("user_info_audit_state") int user_info_audit_state,
            @Param("info_id")int info_id,
            @Param("user_info_audit_describe")String user_info_audit_describe);

    /**
     * 查询专家信息
     * @param expert_info_id
     * @return
     */
    ExpertInfoEntity selectExpertInfoFull(int expert_info_id);

    /**
     * 查询用户提交时间最近的一条信息审核信息
     * @param user_id_idd
     * @return
     */
    UserInfoAuditEntity selectUserInfoAuditRecentlyByUid(int user_id_idd);

    /**
     * 查询用户未审核的信息
     * @param Uid 用户id
     * @param user_info_audit_content 审核类容 1第三方机构信息 2专家信息
     * @return
     */
    Integer selectUiaByUid(@Param("Uid") Integer Uid,
                                       @Param("user_info_audit_content") Integer user_info_audit_content);


}
