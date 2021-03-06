package com.example.service;

import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.resultsparam.ExpertInfoResults;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.UserInfoAuditEntity;

import java.util.List;
import java.util.Map;

public interface UserInfoAuditService {
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
     *  //查询用户最近提交的一条审核信息，如果是未审核用户当状态变为审核通过将用户信息重新登录
     * @param user_id_add 用户id
     * @return
     */
    UserInfoAuditEntity findUserInfoAuditRecentlyByUid(int user_id_add);

    /**
     * 查询审核专家信息列表
     * @param user_info_audit_state //审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type //审核类型 1初审 2变更审核
     * @return
     */
    List<Map<String,Object>> findUserInfoAuditExpert(int user_info_audit_state,
                                                           int user_info_audit_type);

    /**
     * 模糊条件查询审核专家信息列表
     * @param pageRequest //分页参数
     * @param user_info_audit_state //审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type //审核类型 1初审 2变更审核
     * @param condition 查询条件
     * @param orderRequests 排序字段
     * @return
     */
    List<Map<String,Object>> findUserInfoAuditExpert(PageRequest pageRequest,
                                                     int user_info_audit_state,
                                                     int user_info_audit_type,
                                                     String condition,
                                                     OrderRequest[]orderRequests);

    /**
     * 模糊条件分页查询审核第三方机构信息列表
     * @param pageRequest 分页参数
     * @param user_info_audit_state //审核状态 1未审核 2拒绝 3 通过
     * @param user_info_audit_type //审核类型 1初审 2变更审核
     * @param condition 查询条件
     * @param orderRequests 排序字段
     * @return
     */
    List<Map<String,Object>> findUserInfoAuditOrganization(PageRequest pageRequest,
                                                     int user_info_audit_state,
                                                     int user_info_audit_type,
                                                     String condition,
                                                     OrderRequest[]orderRequests);


    /**
     * 分页查询审核专家信息列表
     * @param pageRequest 分页信息
     * @param user_info_audit_state
     * @param user_info_audit_type
     * @return
     */
    List<Map<String,Object>> findUserInfoAuditExpert(PageRequest pageRequest, int user_info_audit_state,
                                                     int user_info_audit_type);




    /**
     * 审核用户信息
     * 审核用户信息
     * 如果为初次审核(1审核状态改变,2用户的信息改变并且用户改为已审核)
     * 反之（1审核状态改变,2用户的信息改变）
     * @param info_id 审核的信息id
     * @param user_info_audit_state 审核状态 2通过 3拒绝
     * @param isCertification 是否为初次认证审核 true为初次审核
     * @return
     */
    boolean operationUserInfoAudit(
            int info_id,
            int user_info_audit_state,
            boolean isCertification
    );

    /**
     *
     * @param info_id 信息id
     * @param user_info_audit_state 审核状态  2通过 3拒绝
     * @param user_info_audit_describe 审核描述
     * @param isCertification 是否为初次审核
     * @return
     */
    boolean operationUserInfoAudit(int info_id, int user_info_audit_state,boolean isCertification,String user_info_audit_describe);

    /**
     * 查询专家完整信息
     * @param expert_info_id
     * @return
     */
    ExpertInfoResults findExpertInfoFull(int expert_info_id);

    /**
     * 查询第三方机构现存完整信息
     * @return
     */
    OrganizationAuditResults findOrganizationInfoFullNowsave(int user_id);

    /**
     * 查询专家现存完整信息
     * @param
     * @return
     */
    ExpertInfoResults findExpertInfoFullNowsave(int user_id);





}
