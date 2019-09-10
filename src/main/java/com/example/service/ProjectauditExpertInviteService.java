package com.example.service;

import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.requstparam.PageRequest;

import java.util.List;

public interface ProjectauditExpertInviteService {
    /**
     * 添加项目审核邀请 专家
     * @param pei 邀请信息
     * @param inviteAdduserId 添加人id
     * @return
     */
    int addPEInvite(
            ProjectauditExpertInvite pei,Integer inviteAdduserId);

    /**
     *分页查询用户项目审核邀请信息列表(专家)
     * @param inviteUserId 邀请对象的用户id
     * @param inviteExpiration 是否包含过期记录 true包含 false不包含
     * @param pageRequest 分页信息
     * @return
     */
    List<ProjectauditExpertInvite> findPEInviteListByUserIdPage(Integer inviteUserId,
                                                                boolean inviteExpiration, PageRequest pageRequest);

    /**
     * 用户操作项目审核邀请 (专家)
     * @param projectInfoId 邀请审核的审核的项目id
     * @param projectauditOrganizationInviteId 项目审核邀请信息数据id
     * @param inviteEdituserId 修改人id
     * @param inviteState 修改状态
     * @param userRole 修改人角色
     * @param inviteType 邀请类型(1组长 2组员)
     * @return
     * @throws OperationProjectauditOInviteException
     */
    Boolean operationUserPEInvite(Integer projectInfoId,
                              Integer projectauditOrganizationInviteId,
                              Integer inviteEdituserId,
                              Integer inviteState,
                              Integer userRole,
                              Integer inviteType) throws OperationProjectauditOInviteException;
}
