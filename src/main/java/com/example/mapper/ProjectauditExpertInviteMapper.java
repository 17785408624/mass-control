package com.example.mapper;

import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.ProjectauditOrganizationInvite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository

public interface ProjectauditExpertInviteMapper {
    /**
     *
     *  2019-09-06
     */
    int insert(ProjectauditExpertInvite record);

    /**
     *
     *  2019-09-06
     */
    int insertSelective(ProjectauditExpertInvite record);

    /**
     * 通过用户id查询用户审核邀请记录
     * @param inviteUserId 邀请对象的用户id
     * @param inviteStates 邀请状态 (1等待操作 2接受 3拒绝 4取消邀请 5邀请过期) 为空则不加此条件
     * @return
     */
    List selectPEInviteListByinviteUserId(
            @Param("inviteUserId") Integer inviteUserId,@Param("inviteStates")Object[] inviteStates);

    /**
     * 通过项目审核邀请对象的用户id和邀请状态查询未过期的记录
     * @param inviteUserId 邀请对象id
     * @param inviteState 状态条件 (1等待操作 2接受 3拒绝 4取消邀请 ) 为null不设置
     * @return
     */
    List<ProjectauditExpertInvite> selectPEInviteListByinviteUserIdExpirationNot(
            @Param("inviteUserId") Integer inviteUserId,@Param("inviteState") Integer inviteState);

    /**
     *通过数据主键修改邀请状态
     *@param  projectauditExpertInvite 审核邀请信息
     * @return
     */
    Integer updatePEInviteStateById(ProjectauditExpertInvite projectauditExpertInvite);

    /**
     * 批量添加项目审核邀请(专家)
     * @param invitesInfo 邀请信息
     * @param experInfos 专家信息
     * @return
     */
    Integer insertPEinviteBatch(ProjectauditExpertInvite invitesInfo,
                                Map[]experInfos);

    /**
     * 通过 信息id 查询审核邀请信息
     * @param projectauditExpertInviteId 信息id
     * @return
     */
    ProjectauditExpertInvite selectPEinviteById(Integer projectauditExpertInviteId);

    /**
     * 查询审核邀请信息
     * @param inviteUserId 邀请对象
     * @param inviteState 邀请状态 状态 (1等待操作 2接受 3拒绝 4取消邀请 5过期)
     * @param inviteType 邀请对象类型 1组长，2组员
     * @return
     */
    ProjectauditExpertInvite selectPEinviteByiUidIsIt(@Param("inviteUserId") Integer inviteUserId,
                                                      @Param("inviteState")Integer inviteState,
                                                      @Param("inviteType")Integer inviteType);

    /**
     * 通过审核邀请的信息 查询符合条件的记录数量
     * @param projectInfoId 项目id
     * @param inviteState 审核邀请信息状态
     * @param inviteType 邀请人类型
     * @return
     */
    Integer countPeiNumByPiidIsIt(@Param("projectInfoId") Integer projectInfoId,
                  @Param("inviteState")Integer inviteState,
                  @Param("inviteType")Integer inviteType);

    /**
     * 通过项目id查询邀请审核的专家组成员信息
     * @param projectInfoId 项目id
     * @param inviteState 邀请状态 (1等待操作 2接受 3拒绝 4取消邀请 5邀请过期)
     * @return
     */
    List<Map> selectPeiByPid(@Param("projectInfoId") Integer projectInfoId,@Param("inviteState") Integer[]inviteState);

    /**
     * 根据项目id查询项目审核 组长 邀请信息最新的一条数据
     * @param projectInfoId
     * @return
     */
    ProjectauditExpertInvite selectPeiNewestLeaderByPid(@Param("projectInfoId") Integer projectInfoId);
    /**
     * 根据项目id查询项目审核 组员 邀请信息最新的一条数据
     * @param projectInfoId
     * @return
     */
    List<ProjectauditExpertInvite> selectPeiNewestByPid(@Param("projectInfoId") Integer projectInfoId);

}