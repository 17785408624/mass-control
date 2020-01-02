package com.example.entity;

/**
 * 项目审核信息表
 */
public class ProjectAudit {
    /**
     * 数据主键 项目评审信息id
     */
    private Integer projectAuditId;

    /**
     * 项目id
     */
    private Integer projectInfoId;

    /**
     * 评审人id
     */
    private Integer auditUserId;

    /**
     * 添加人id
     */
    private Integer addUserId;

    /**
     * 添加时间
     */
    private Long addDatetime;

    /**
     * 评审人角色1专家 2第三方机构 3煤监局 4能源局
     */
    private Integer auditUserRole;

    /**
     * 评审内容
     */
    private String auditContent;

    /**
     * 评审时间
     */
    private Long auditDatetime;

    /**
     * 评审修改时间
     */
    private Long auditRevamp;

    /**
     * 评审状态(1待操作，2已评审)
     */
    private Integer auditState;










    /**
     * 
     * @return project_audit_id 
     */
    public Integer getProjectAuditId() {
        return projectAuditId;
    }

    /**
     * 
     * @param projectAuditId 
     */
    public void setProjectAuditId(Integer projectAuditId) {
        this.projectAuditId = projectAuditId;
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
     * 评审人id
     * @return audit_user_id 评审人id
     */
    public Integer getAuditUserId() {
        return auditUserId;
    }

    /**
     * 评审人id
     * @param auditUserId 评审人id
     */
    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    /**
     * 添加人id
     * @return add_user_id 添加人id
     */
    public Integer getAddUserId() {
        return addUserId;
    }

    /**
     * 添加人id
     * @param addUserId 添加人id
     */
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    /**
     * 添加时间
     * @return add_datetime 添加时间
     */
    public Long getAddDatetime() {
        return addDatetime;
    }

    /**
     * 添加时间
     * @param addDatetime 添加时间
     */
    public void setAddDatetime(Long addDatetime) {
        this.addDatetime = addDatetime;
    }

    /**
     * 评审人角色1专家 2第三方机构 3煤监局 4能源局
     * @return audit_user_role 评审人角色1专家 2第三方机构 3煤监局 4能源局
     */
    public Integer getAuditUserRole() {
        return auditUserRole;
    }

    /**
     * 评审人角色1专家 2第三方机构 3煤监局 4能源局
     * @param auditUserRole 评审人角色1专家 2第三方机构 3煤监局 4能源局
     */
    public void setAuditUserRole(Integer auditUserRole) {
        this.auditUserRole = auditUserRole;
    }

    /**
     * 评审内容
     * @return audit_content 评审内容
     */
    public String getAuditContent() {
        return auditContent;
    }

    /**
     * 评审内容
     * @param auditContent 评审内容
     */
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent == null ? null : auditContent.trim();
    }

    /**
     * 评审时间
     * @return audit_datetime 评审时间
     */
    public Long getAuditDatetime() {
        return auditDatetime;
    }

    /**
     * 评审时间
     * @param auditDatetime 评审时间
     */
    public void setAuditDatetime(Long auditDatetime) {
        this.auditDatetime = auditDatetime;
    }

    /**
     * 评审修改时间
     * @return audit_revamp 评审修改时间
     */
    public Long getAuditRevamp() {
        return auditRevamp;
    }

    /**
     * 评审修改时间
     * @param auditRevamp 评审修改时间
     */
    public void setAuditRevamp(Long auditRevamp) {
        this.auditRevamp = auditRevamp;
    }

    /**
     * 评审状态(1待操作，2已评审)
     * @return audit_state 评审状态(1待操作，2已评审)
     */
    public Integer getAuditState() {
        return auditState;
    }

    /**
     * 评审状态(1待操作，2已评审)
     * @param auditState 评审状态(1待操作，2已评审)
     */
    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }
}