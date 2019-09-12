package com.example.entity.requstparam;

public class ExtractionPERequest {
    Integer extractNum;//抽取人数
    Integer[]expert_info_educations;//专家学历
    Integer[]expert_info_workmajors;//专家从事行业

    public Integer getExtractNum() {
        return extractNum;
    }

    public void setExtractNum(Integer extractNum) {
        this.extractNum = extractNum;
    }

    public Integer[] getExpert_info_educations() {
        return expert_info_educations;
    }

    public void setExpert_info_educations(Integer[] expert_info_educations) {
        this.expert_info_educations = expert_info_educations;
    }

    public Integer[] getExpert_info_workmajors() {
        return expert_info_workmajors;
    }

    public void setExpert_info_workmajors(Integer[] expert_info_workmajors) {
        this.expert_info_workmajors = expert_info_workmajors;
    }
}
