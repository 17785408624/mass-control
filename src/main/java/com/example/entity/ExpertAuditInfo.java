package com.example.entity;

public class ExpertAuditInfo {
    /**
     * 信息主键
     */
    private Integer expertAuditInfoId;

    /**
     * 关联项目id
     */
    private Integer projectInfoId;

    /**
     * 未评测专家数
     */
    private Integer notAuditNum;

    /**
     * 审核人id
     */
    private Integer auditUserId;

    /**
     * 信息主键
     * @return expert_audit_info_id 信息主键
     */
    public Integer getExpertAuditInfoId() {
        return expertAuditInfoId;
    }

    /**
     * 信息主键
     * @param expertAuditInfoId 信息主键
     */
    public void setExpertAuditInfoId(Integer expertAuditInfoId) {
        this.expertAuditInfoId = expertAuditInfoId;
    }

    /**
     * 关联项目id
     * @return project_info_id 关联项目id
     */
    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    /**
     * 关联项目id
     * @param projectInfoId 关联项目id
     */
    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    /**
     * 未评测专家数
     * @return not_audit_num 未评测专家数
     */
    public Integer getNotAuditNum() {
        return notAuditNum;
    }

    /**
     * 未评测专家数
     * @param notAuditNum 未评测专家数
     */
    public void setNotAuditNum(Integer notAuditNum) {
        this.notAuditNum = notAuditNum;
    }

    /**
     * 审核人id
     * @return audit_user_id 审核人id
     */
    public Integer getAuditUserId() {
        return auditUserId;
    }

    /**
     * 审核人id
     * @param auditUserId 审核人id
     */
    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }
}