package com.example.service;

import com.example.common.exceptiondefine.AuditOperationServiceException;
import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.requstparam.InsertPEinviteBatch;
import com.example.entity.requstparam.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectauditExpertInviteService {
    /**
     * 添加项目审核邀请 专家组长
     * @param pei 邀请信息
     * @param inviteAdduserId 添加人id
     * @return
     */
    int addPEInvite(
            ProjectauditExpertInvite pei,Integer inviteAdduserId) throws AuditOperationServiceException;

    /**
     *分页查询用户项目审核邀请信息列表(专家)
     * @param inviteUserId 邀请对象的用户id
     * @param inviteStates 邀请状态(1等待操作 2接受 3拒绝 4取消邀请 5邀请过期) 为空则不加此条件
     * @param inviteExpiration 是否包含过期记录 true包含 false不包含
     * @param pageRequest 分页信息
     * @return
     */
    List findPEInviteListByUserIdPage(Integer inviteUserId,Object[] inviteStates,
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

    /**
     * 查询项目的专家组成员审核邀请信息 不包含过期以及取消的邀请
     * @param projectInfoId 项目信息id
     * @return
     */
    List<Map>findPeiByPid(Integer projectInfoId);

    /**
     * 抽取专家
     *  @domainType 专业条件类型 declaredesign申报专业
     * major 所学专业或从事专业
     *
     * 申报专业不能和所学专业与从事专业进行同时分组。
     * @param expert_info_educations 学历
     * @param domainCondition 抽取条件
     * @param domain  分组字段-- declaredesign申报专业技术报告咨询审查类和申报专业安全生产检查类
     *      declaredesign_design 申报专业技术报告咨询审查类 declaredesign_safety  申报专业安全生产检查类
     *      major 所学专业和从事专业
     *      learnmajor所学专业 workmajor从事专业
     *      申报专业不能和所学专业与从事专业进行同时分组。
     * @return
     */
   List extractionExpert(String domainType,String domain, Object[] expert_info_educations,Map domainCondition);
}
