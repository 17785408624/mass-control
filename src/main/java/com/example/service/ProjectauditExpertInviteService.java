package com.example.service;

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
    List<ProjectauditExpertInvite> findPEInviteListByUserIdPage(Integer inviteUserId, boolean inviteExpiration, PageRequest pageRequest);
}
