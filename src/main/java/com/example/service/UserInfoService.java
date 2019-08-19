package com.example.service;

import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.UserInfoAuditEntity;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    /**
     * 查询第三方机构信息审核申请列表
     * @param user_info_audit_state 审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type 审核类型 1初审 2变更审核
     * @return
     */
    List<Map<String,Object>> findUserInfoAuditOrganization(int user_info_audit_state,
                                                           int user_info_audit_type);

    /**
     * 查询第三方机构信息 完整信息
     * @param organization_info_id
     * @return
     */
    OrganizationAuditResults findOrganizationInfoFull(int organization_info_id);

    /**
     *
     * @param user_id_add 查询用户提交申请的审核信息
     * @return
     */
    List<UserInfoAuditEntity> selectUserInfoAuditByUid(int user_id_add);
    /**
     * 查询用户是否已提交过审核信息
     * @param
     * @return 已提交返回true 未提交返回false
     */
    boolean findUserInfoAuditSubmitState(int user_id_add);

    /**
     *  查询用户提交的第一条审核信息
     * @param user_id_add
     * @return
     */
    UserInfoAuditEntity findUserInfoAuditSubmitFirst(int user_id_add);

    /**
     * 查询审核专家信息申请列表
     * @param user_info_audit_state //审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type //审核类型 1初审 2变更审核
     * @return
     */
    List<Map<String,Object>> findUserInfoAuditExpert(int user_info_audit_state,
                                                           int user_info_audit_type);


}