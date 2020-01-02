package com.example.entity;

public class ExpertAudit {
    /**
     * 专家评测数据id
     */
    private Integer expertAuditId;

    /**
     * 专业水平(1非常差，2差，3一般，4高，5非常高)
     */
    private Integer expertise;

    /**
     * 职业道德(1非常差，2差，3一般，4高，5非常高)
     */
    private Integer professional;

    /**
     * 服务态度(1非常差，2差，3一般，4高，5非常高)
     */
    private Integer attitude;

    /**
     * 审核类容
     */
    private String auditContent;

    /**
     * 关联的项目id
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
     * 被评审人id
     */
    private Integer byauditUserId;

    /**
     * 专家评测数据id
     * @return expert_audit_id 专家评测数据id
     */
    public Integer getExpertAuditId() {
        return expertAuditId;
    }

    /**
     * 专家评测数据id
     * @param expertAuditId 专家评测数据id
     */
    public void setExpertAuditId(Integer expertAuditId) {
        this.expertAuditId = expertAuditId;
    }

    /**
     * 专业水平(1非常差，2差，3一般，4高，5非常高)
     * @return expertise 专业水平(1非常差，2差，3一般，4高，5非常高)
     */
    public Integer getExpertise() {
        return expertise;
    }

    /**
     * 专业水平(1非常差，2差，3一般，4高，5非常高)
     * @param expertise 专业水平(1非常差，2差，3一般，4高，5非常高)
     */
    public void setExpertise(Integer expertise) {
        this.expertise = expertise;
    }

    /**
     * 职业道德(1非常差，2差，3一般，4高，5非常高)
     * @return professional 职业道德(1非常差，2差，3一般，4高，5非常高)
     */
    public Integer getProfessional() {
        return professional;
    }

    /**
     * 职业道德(1非常差，2差，3一般，4高，5非常高)
     * @param professional 职业道德(1非常差，2差，3一般，4高，5非常高)
     */
    public void setProfessional(Integer professional) {
        this.professional = professional;
    }

    /**
     * 服务态度
     * @return attitude 服务态度
     */
    public Integer getAttitude() {
        return attitude;
    }

    /**
     * 服务态度
     * @param attitude 服务态度
     */
    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    /**
     * 审核类容
     * @return audit_content 审核类容
     */
    public String getAuditContent() {
        return auditContent;
    }

    /**
     * 审核类容
     * @param auditContent 审核类容
     */
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent == null ? null : auditContent.trim();
    }

    /**
     * 关联的项目id
     * @return project_info_id 关联的项目id
     */
    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    /**
     * 关联的项目id
     * @param projectInfoId 关联的项目id
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

    /**
     * 被评审人id
     * @return byaudit_user_id 被评审人id
     */
    public Integer getByauditUserId() {
        return byauditUserId;
    }

    /**
     * 被评审人id
     * @param byauditUserId 被评审人id
     */
    public void setByauditUserId(Integer byauditUserId) {
        this.byauditUserId = byauditUserId;
    }
}