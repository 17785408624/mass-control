package com.example.entity.requstparam;

public class FindtEaiListUser_pageR {
    private Integer project_info_progress;//项目进程
    private Boolean containExpiration;//是否包含工作时间已过期的数据
    private Boolean containAuditnumZero;//是否包含未审核人数为0的数据
    private PageOderRequest pageOderRequest;//分页信息


    public Integer getProject_info_progress() {
        return project_info_progress;
    }

    public void setProject_info_progress(Integer project_info_progress) {
        this.project_info_progress = project_info_progress;
    }

    public Boolean getContainExpiration() {
        return containExpiration;
    }

    public void setContainExpiration(Boolean containExpiration) {
        this.containExpiration = containExpiration;
    }

    public Boolean getContainAuditnumZero() {
        return containAuditnumZero;
    }

    public void setContainAuditnumZero(Boolean containAuditnumZero) {
        this.containAuditnumZero = containAuditnumZero;
    }

    public PageOderRequest getPageOderRequest() {
        return pageOderRequest;
    }

    public void setPageOderRequest(PageOderRequest pageOderRequest) {
        this.pageOderRequest = pageOderRequest;
    }
}
