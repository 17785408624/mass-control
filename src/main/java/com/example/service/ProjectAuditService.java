package com.example.service;

import com.example.entity.requstparam.PageOderRequest;

import java.util.List;
import java.util.Map;

/**
 * 项目评审业务
 */
public interface ProjectAuditService {
    /**
     *
     * @return
     */
    /**
     * 用户查询项目审核信息列表
     * @param auditUserId 评审人id
     * @param auditState 评审状态(1待操作，2已评审)
     * @param isExpiration 是否包含过期信息 true包含过期信息
     * @param pageOderRequest 分页及排序信息
     * @return
     */
    public List<Map> findProjectAuditListByUser(Integer auditUserId, Integer auditState, Boolean isExpiration, PageOderRequest pageOderRequest);


    /**
     *
     * @param projectAuditId 评审信息id
     * @param auditUserId 评审人id
     * @param auditUserRole 评审人角色
     * @param auditContent 评审内容
     * @return
     */
    public Boolean auditProject(Object projectAuditId,
                                Integer auditUserId,
                                Integer auditUserRole,
                                String auditContent);

    /**
     *项目审核，包含更改项目状态
     * @param projectAuditId
     * @param auditUserId
     * @param auditUserRole
     * @param auditContent
    * @param projectInfoState 项目状态(1待操作 2通过 3不通过)
     * @return
     */
    public Boolean auditProject(Integer projectAuditId,
                                Integer auditUserId,
                                Integer auditUserRole,
                                String auditContent,
                                Integer projectInfoState);


}
