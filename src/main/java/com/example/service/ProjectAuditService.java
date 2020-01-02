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
     * 项目审核
     * 1修改项目审核信息表
     * 2修改项目参与者表 用户是否对项目进行过审核
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
     * 1修改项目审核信息表/添加项目审核信息
     * 2修改项目状态
     * 3改变项目进程
     * 4修改项目参与者表 用户是否对项目进行过审核
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

    /**
     * 项目审核，包含更改项目状态
     * 1修改项目审核信息表/添加项目审核信息
     * 2修改项目状态
     * 3改变项目进程
     * 4修改项目参与者表 用户是否对项目进行过审核
     * @param projectAuditId
     * @param auditUserId
     * @param auditUserRole
     * @param auditContent
     * @param projectInfoState
     * @param auditTime 评审时间
     * @return
     */
    public Boolean auditProject(Integer projectAuditId,
                                Integer auditUserId,
                                Integer auditUserRole,
                                String auditContent,
                                Integer projectInfoState,
                                Long auditTime);

    /**
     * 能源局或者煤监局审核项目
     * 1添加审核项目信息数据
     * 2更改项目状态
     * 3改变项目进程
     *  4修改项目参与者表 用户是否对项目进行过审核
     * @param auditUserId 评审人id
     * @param auditUserRole 评审人角色
     * @param auditContent 评审内容
     * @param projectInfoState 评审状态 2通过 3不通过
     * @param auditTime 评审时间
     * @param projectInfoId 评审的项目id
     * @return
     */
    public Boolean auditProjectBureau(Integer auditUserId,
                                      Integer auditUserRole,
                                      String auditContent,
                                      Integer projectInfoState,
                                      Long auditTime,
                                      Integer projectInfoId);

    /**
     * 查询专家评测信息列表
     * @param projectInfoId 项目id
     * @param auditState 评测状态
     * @param auditUserRole 评审人角色
     * @return
     */
    public List<Map>findEaListByPiidlAtAur(Integer projectInfoId,Integer auditState,Integer auditUserRole);



}
