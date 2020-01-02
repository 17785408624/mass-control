package com.example.mapper;

import com.example.entity.ProjectauditOrganizationInvite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectauditOrganizationInviteMapper {
    /**
     *
     *  2019-08-28
     */
    int insert(ProjectauditOrganizationInvite record);

    /**
     *
     *  2019-08-28
     */
    int insertSelective(ProjectauditOrganizationInvite record);

    /**
     * 添加项目审核第三方机构邀请信息
     * @param record
     * @return
     */
    int insertPoi(ProjectauditOrganizationInvite record);

    /**
     * 通过项目审核邀请对象的用户id查询记录
     * @param inviteUserId 邀请对象id
     * @return
     */
    List<Map> selectProjectauditOInviteListByinviteUserId(
            @Param("inviteUserId") Integer inviteUserId);

    /**
     * 通过项目审核邀请对象的用户id和邀请状态查询未过期的记录
     * @param inviteUserId 邀请对象id
     * @param inviteState 状态条件 (1等待操作 2接受 3拒绝 4取消邀请 ) 为null不设置
     * @return
     */
    List<Map> selectProjectauditOInviteListByIUserIdExpirationNot(
            @Param("inviteUserId") Integer inviteUserId,@Param("inviteState") Integer inviteState);

    /**
     * 更新第三方机构项目审核邀请信息
     * @param projectauditOrganizationInvite 审核信息类
     */
    void updatePOIStateByPOIId(ProjectauditOrganizationInvite projectauditOrganizationInvite);

    /**
     * 通过第三方项目审核邀请id 查询项目审核邀请信息
     * @param projectauditOrganizationInviteId 信息id
     * @return
     */
    ProjectauditOrganizationInvite selectPoiByPoiId(Integer projectauditOrganizationInviteId);

    /**
     * 查询第三方机构项目审核邀请信息
     * @param inviteUserId 邀请对象id
     * @param inviteState 邀请状态状态 状态 (1等待操作 2接受 3拒绝 4取消邀请 5过期)
     * @return
     */
    ProjectauditOrganizationInvite selectPoiByIuidIs(@Param("inviteUserId") Integer inviteUserId,@Param("inviteState") Integer inviteState);

    /**
     * 通过邀请审核的项目id和邀请状态
     * @param projectInfoId
     * @param inviteState
     * @return
     */
    Integer countPoiNumByPiIdIs(@Param("projectInfoId") Integer projectInfoId,@Param("inviteState") Integer inviteState);

}