package com.example.service;

import com.example.entity.ExpertAudit;
import com.example.entity.requstparam.PageOderRequest;

import java.util.List;
import java.util.Map;

public interface ExpertAuditService {
    /**
     * 用户查询专家评测列表
     * @param expertAuditInfoId 专家评测信息id
     * @param auditState 评测状态 1待操作 2已评审
     * @return
     */
    List<Map> findExpertAuditList(Integer expertAuditInfoId, Integer auditState);
    List<Map> findExpertAuditList(Integer expertAuditInfoId, Integer auditState, PageOderRequest por);
    /**
     * 评测专家信息
     * @param expertAudits 批量修改的数据
     * @param projectInfoId 关联的项目id
     * @param auditUserId 审核人id
     * @param auditUserRole 审核人角色
     * @return
     */
    Integer expertEvaluation(ExpertAudit[] expertAudits,Integer projectInfoId,Integer auditUserId,Integer auditUserRole);
}
