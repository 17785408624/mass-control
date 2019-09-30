package com.example.entity.requstparam;

import com.example.entity.ExpertAudit;

public class ExpertEvaluationMap {
    private ExpertAudit[] expertAudits;
    private Integer projectInfoId;

    public ExpertAudit[] getExpertAudits() {
        return expertAudits;
    }

    public void setExpertAudits(ExpertAudit[] expertAudits) {
        this.expertAudits = expertAudits;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}
