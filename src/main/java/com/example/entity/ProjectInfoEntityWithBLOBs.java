package com.example.entity;

public class ProjectInfoEntityWithBLOBs extends ProjectInfoEntity {
    /**
     * 项目简介
     */
    private String projectInfoSynopsis;

    /**
     * 项目内容
     */
    private String projectInfoContent;

    /**
     * 项目简介
     * @return project_info_synopsis 项目简介
     */
    public String getProjectInfoSynopsis() {
        return projectInfoSynopsis;
    }

    /**
     * 项目简介
     * @param projectInfoSynopsis 项目简介
     */
    public void setProjectInfoSynopsis(String projectInfoSynopsis) {
        this.projectInfoSynopsis = projectInfoSynopsis == null ? null : projectInfoSynopsis.trim();
    }

    /**
     * 项目内容
     * @return project_info_content 项目内容
     */
    public String getProjectInfoContent() {
        return projectInfoContent;
    }

    /**
     * 项目内容
     * @param projectInfoContent 项目内容
     */
    public void setProjectInfoContent(String projectInfoContent) {
        this.projectInfoContent = projectInfoContent == null ? null : projectInfoContent.trim();
    }
}