package com.example.service;

import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectauditOrganizationInvite;
import com.example.entity.requstparam.PageRequest;

import java.util.List;

public interface ProjectauditOrganizationInviteService {
   /**
    * 添加第三方机构项目审核邀请
    * @param projectauditOrganizationInvite
    * @param inviteAdduserId 添加人用户id
    * @return
    */
   int  addProjectauditInvite(Integer inviteAdduserId,
           ProjectauditOrganizationInvite projectauditOrganizationInvite);

   /**
    *
    * @param inviteUserId
    * @return
    */
    /**
     * 分页查询用户项目审核邀请信息列表(第三方机构)
     * @param inviteUserId 邀请对象的用户id
     * @param inviteExpiration 是否包含过期记录 true包含 false不包含
     * @param pageRequest 分页信息
     * @return
     */
  List<ProjectauditOrganizationInvite> findProjectauditOInviteListByUserIdPage(Integer inviteUserId, boolean inviteExpiration,PageRequest pageRequest);

    /**
     * 用户操作项目审核邀请 (第三方机构)
     * 一、接受
     * 1添加项目参与者信息
     * 2改变邀请审核的项目进程为选择组长
     * 3修改审核邀请信息
     * 4添加项目审核信息
     * 5添加专家评测信息数据
     * 二、拒绝
     * 修改审核邀请信息
     * @param projectauditOrganizationInviteId 项目审核邀请信息数据id
     * @param inviteEdituserId 修改人id
     * @param inviteState 修改状态
     * @param userRole 修改人角色
     * @return
     */
  boolean operationUserProjectauditOInvite(
          Integer projectauditOrganizationInviteId,
          Integer inviteEdituserId,
          Integer inviteState,
          Integer userRole) throws OperationProjectauditOInviteException;

    /**
     *判断用户是否能进行审核邀请操作
     * @return true为能进行操作
     */
  boolean isLegalOperationProjectauditOrganization(Integer inviteProjectId,Integer projectauditOrganizationInviteId,Integer operationUid,Integer inviteState,Integer userRole);
}
