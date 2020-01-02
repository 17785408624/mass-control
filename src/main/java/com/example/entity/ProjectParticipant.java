package com.example.entity;

/**
 * 项目参与者记录
 */
public class ProjectParticipant {
    /**
     * 信息主键
     */
    private Integer projectParticipantId;

    /**
     * 项目id
     */
    private Integer projectInfoId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员 6组长
     */
    private Integer userRole;
    /**
     * 用户是否对项目进行过审核(1未进行过审核，2进行过审核)
     */
    private Integer userAudit;

    /**
     * 信息主键
     * @return project_participant_id 信息主键
     */
    public Integer getProjectParticipantId() {
        return projectParticipantId;
    }

    /**
     * 信息主键
     * @param projectParticipantId 信息主键
     */
    public void setProjectParticipantId(Integer projectParticipantId) {
        this.projectParticipantId = projectParticipantId;
    }

    /**
     * 项目id
     * @return project_info_id 项目id
     */
    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    /**
     * 项目id
     * @param projectInfoId 项目id
     */
    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    /**
     * 
     * @return user_id 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     * @return user_role 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     * @param userRole 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getUserAudit() {
        return userAudit;
    }

    public void setUserAudit(Integer userAudit) {
        this.userAudit = userAudit;
    }
}