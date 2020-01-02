package com.example.entity;

/**
 * 项目邀请审核（第三方机构）
 */
public class ProjectauditOrganizationInvite {
    /**
     * 信息主键
     */
    private Integer projectauditOrganizationInviteId;

    /**
     * 状态 (1等待操作 2接受 3拒绝 4取消邀请 )
     */
    private Integer inviteState;

    /**
     * 邀请对象 用户id
     */
    private Integer inviteUserId;

    /**
     * 邀请机构名
     */
    private String inviteOrganizationName;

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
     * 
     * @return projectaudit_organization_invite_id 
     */
    public Integer getProjectauditOrganizationInviteId() {
        return projectauditOrganizationInviteId;
    }

    /**
     * 
     * @param projectauditOrganizationInviteId 
     */
    public void setProjectauditOrganizationInviteId(Integer projectauditOrganizationInviteId) {
        this.projectauditOrganizationInviteId = projectauditOrganizationInviteId;
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
     * 邀请机构名
     * @return invite_organization_name 邀请机构名
     */
    public String getInviteOrganizationName() {
        return inviteOrganizationName;
    }

    /**
     * 邀请机构名
     * @param inviteOrganizationName 邀请机构名
     */
    public void setInviteOrganizationName(String inviteOrganizationName) {
        this.inviteOrganizationName = inviteOrganizationName == null ? null : inviteOrganizationName.trim();
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


    public Integer getInviteEdituserId() {
        return inviteEdituserId;
    }

    public void setInviteEdituserId(Integer inviteEdituserId) {
        this.inviteEdituserId = inviteEdituserId;
    }

    public Long getInviteExpiration() {
        return inviteExpiration;
    }

    public void setInviteExpiration(Long inviteExpiration) {
        this.inviteExpiration = inviteExpiration;
    }


    public String getProjectInfoName() {
        return projectInfoName;
    }

    public void setProjectInfoName(String projectInfoName) {
        this.projectInfoName = projectInfoName;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}