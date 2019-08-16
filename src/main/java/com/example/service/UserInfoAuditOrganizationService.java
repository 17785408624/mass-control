package com.example.service;

import com.example.entity.resultsparam.OrganizationAuditResults;

import java.util.List;
import java.util.Map;

public interface UserInfoAuditOrganizationService {
    /**
     * 查询第三方机构信息审核申请
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
}
