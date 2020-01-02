package com.example.entity;

/**
 * 项目审核邀请(专家)
 */
public class ProjectauditExpertInvite {
    /**
     * 信息id
     */
    private Integer projectauditExpertInviteId;

    /**
     * 状态 (1等待操作 2接受 3拒绝 4取消邀请)
     */
    private Integer inviteState;

    /**
     * 邀请对象 用户id
     */
    private Integer inviteUserId;

    /**
     * 邀请专家名
     */
    private String inviteExpertName;

    /**
     * 添加时间
     */
    private Long inviteAddtime;

    /**
     * 修改时间
     */
    private Long inviteEdittime;

    /**
     * 添加人用户id
     */
    private Integer inviteAdduserId;

    /**
     * 修改人用户id
     */
    private Integer inviteEdituserId;

    /**
     * 邀请过期时间
     */
    private Long inviteExpiration;

    /**
     * 邀请审核的项目名字
     */
    private String projectInfoName;

    /**
     * 邀请审核的项目id
     */
    private Integer projectInfoId;

    /**
     * 邀请类型(1组长 2组员)
     */
    private Integer inviteType;

    /**
     * 
     * @return projectaudit_expert_invite_id 
     */
    public Integer getProjectauditExpertInviteId() {
        return projectauditExpertInviteId;
    }

    /**
     * 
     * @param projectauditExpertInviteId 
     */
    public void setProjectauditExpertInviteId(Integer projectauditExpertInviteId) {
        this.projectauditExpertInviteId = projectauditExpertInviteId;
    }

    /**
     * 状态 (1等待操作 2接受 3拒绝 4取消邀请)
     * @return invite_state 状态 (1等待操作 2接受 3拒绝 4取消邀请)
     */
    public Integer getInviteState() {
        return inviteState;
    }

    /**
     * 状态 (1等待操作 2接受 3拒绝 4取消邀请)
     * @param inviteState 状态 (1等待操作 2接受 3拒绝 4取消邀请)
     */
    public void setInviteState(Integer inviteState) {
        this.inviteState = inviteState;
    }

    /**
     * 邀请对象 用户id
     * @return invite_user_id 邀请对象 用户id
     */
    public Integer getInviteUserId() {
        return inviteUserId;
    }

    /**
     * 邀请对象 用户id
     * @param inviteUserId 邀请对象 用户id
     */
    public void setInviteUserId(Integer inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    /**
     * 邀请专家名
     * @return invite_expert_name 邀请专家名
     */
    public String getInviteExpertName() {
        return inviteExpertName;
    }

    /**
     * 邀请专家名
     * @param inviteExpertName 邀请专家名
     */
    public void setInviteExpertName(String inviteExpertName) {
        this.inviteExpertName = inviteExpertName == null ? null : inviteExpertName.trim();
    }

    /**
     * 添加时间
     * @return invite_addtime 添加时间
     */
    public Long getInviteAddtime() {
        return inviteAddtime;
    }

    /**
     * 添加时间
     * @param inviteAddtime 添加时间
     */
    public void setInviteAddtime(Long inviteAddtime) {
        this.inviteAddtime = inviteAddtime;
    }

    /**
     * 修改时间
     * @return invite_edittime 修改时间
     */
    public Long getInviteEdittime() {
        return inviteEdittime;
    }

    /**
     * 修改时间
     * @param inviteEdittime 修改时间
     */
    public void setInviteEdittime(Long inviteEdittime) {
        this.inviteEdittime = inviteEdittime;
    }

    /**
     * 添加人用户id
     * @return invite_adduser_id 添加人用户id
     */
    public Integer getInviteAdduserId() {
        return inviteAdduserId;
    }

    /**
     * 添加人用户id
     * @param inviteAdduserId 添加人用户id
     */
    public void setInviteAdduserId(Integer inviteAdduserId) {
        this.inviteAdduserId = inviteAdduserId;
    }

    /**
     * 修改人用户id
     * @return invite_edituser_id 修改人用户id
     */
    public Integer getInviteEdituserId() {
        return inviteEdituserId;
    }

    /**
     * 修改人用户id
     * @param inviteEdituserId 修改人用户id
     */
    public void setInviteEdituserId(Integer inviteEdituserId) {
        this.inviteEdituserId = inviteEdituserId;
    }

    /**
     * 邀请过期时间
     * @return invite_expiration 邀请过期时间
     */
    public Long getInviteExpiration() {
        return inviteExpiration;
    }

    /**
     * 邀请过期时间
     * @param inviteExpiration 邀请过期时间
     */
    public void setInviteExpiration(Long inviteExpiration) {
        this.inviteExpiration = inviteExpiration;
    }

    /**
     * 邀请审核的项目名字
     * @return project_info_name 邀请审核的项目名字
     */
    public String getProjectInfoName() {
        return projectInfoName;
    }

    /**
     * 邀请审核的项目名字
     * @param projectInfoName 邀请审核的项目名字
     */
    public void setProjectInfoName(String projectInfoName) {
        this.projectInfoName = projectInfoName == null ? null : projectInfoName.trim();
    }

    /**
     * 邀请审核的项目id
     * @return project_info_id 邀请审核的项目id
     */
    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    /**
     * 邀请审核的项目id
     * @param projectInfoId 邀请审核的项目id
     */
    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }


    public Integer getInviteType() {
        return inviteType;
    }

    public void setInviteType(Integer inviteType) {
        this.inviteType = inviteType;
    }
}