package com.example.service;

import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.requstparam.InsertPEinviteBatch;
import com.example.entity.requstparam.PageRequest;

import java.util.List;

public interface ProjectauditExpertInviteService {
    /**
     * 添加项目审核邀请 专家组长
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
     * 一 修改状态为通过
     * 1 当角色为组长时 改变邀请审核的项目进程为选择专家组组员
     * 2添加项目审核信息 每个参与的用户都有一条审核记录
     * 3修改审核状态
     * 4添加项目参与者信息
     * 5添加专家评测审核信息
     * 6改变未审核人数
     *二 修改状态为拒绝
     * 1修改审核状态
     * @param projectauditExpertInviteId 项目审核邀请信息数据id
     * @param inviteEdituserId 修改人id
     * @param inviteState 修改状态
     * @param userRole 修改人角色
     * @param inviteType 邀请类型(1组长 2组员)
     * @return
     * @throws OperationProjectauditOInviteException
     */
    Boolean operationUserPEInvite(Integer projectauditExpertInviteId,
                              Integer inviteEdituserId,
                              Integer inviteState,
                              Integer userRole,
                              Integer inviteType) throws OperationProjectauditOInviteException;

    /**
     * 添加项目审核邀请(专家组组员)
     * @param insertPEinviteBatch
     * @param inviteAdduserId
     * @return
     */
    Integer addPEInvite(InsertPEinviteBatch insertPEinviteBatch,Integer inviteAdduserId);
}
