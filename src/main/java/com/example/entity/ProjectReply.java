package com.example.entity;
//项目批复信息
public class ProjectReply {
    /**
     * 信息主键
     */
    private Integer projectReplyId;

    /**
     * 添加时间
     */
    private Long replyAddtime;

    /**
     * 上次修改时间
     */
    private Long replyEdittime;

    /**
     * 添加人id
     */
    private Integer replyAdduserId;

    /**
     * 上次修改人id
     */
    private Integer replyEdituserId;

    /**
     * 批复内容
     */
    private String replyContent;

    /**
     * 批复的项目id
     */
    private Integer projectInfoId;

    /**
     * 批复时间
     */
    private Long replyTime;

    /**
     * 信息主键
     * @return project_reply_id 信息主键
     */
    public Integer getProjectReplyId() {
        return projectReplyId;
    }

    /**
     * 信息主键
     * @param projectReplyId 信息主键
     */
    public void setProjectReplyId(Integer projectReplyId) {
        this.projectReplyId = projectReplyId;
    }

    /**
     * 添加时间
     * @return reply_addtime 添加时间
     */
    public Long getReplyAddtime() {
        return replyAddtime;
    }

    /**
     * 添加时间
     * @param replyAddtime 添加时间
     */
    public void setReplyAddtime(Long replyAddtime) {
        this.replyAddtime = replyAddtime;
    }

    /**
     * 上次修改时间
     * @return reply_edittime 上次修改时间
     */
    public Long getReplyEdittime() {
        return replyEdittime;
    }

    /**
     * 上次修改时间
     * @param replyEdittime 上次修改时间
     */
    public void setReplyEdittime(Long replyEdittime) {
        this.replyEdittime = replyEdittime;
    }

    /**
     * 添加人id
     * @return reply_adduser_id 添加人id
     */
    public Integer getReplyAdduserId() {
        return replyAdduserId;
    }

    /**
     * 添加人id
     * @param replyAdduserId 添加人id
     */
    public void setReplyAdduserId(Integer replyAdduserId) {
        this.replyAdduserId = replyAdduserId;
    }

    /**
     * 上次修改人id
     * @return reply_edituser_id 上次修改人id
     */
    public Integer getReplyEdituserId() {
        return replyEdituserId;
    }

    /**
     * 上次修改人id
     * @param replyEdituserId 上次修改人id
     */
    public void setReplyEdituserId(Integer replyEdituserId) {
        this.replyEdituserId = replyEdituserId;
    }

    /**
     * 批复内容
     * @return reply_content 批复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 批复内容
     * @param replyContent 批复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    /**
     * 批复的项目id
     * @return project_info_id 批复的项目id
     */
    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    /**
     * 批复的项目id
     * @param projectInfoId 批复的项目id
     */
    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    /**
     * 批复时间
     * @return reply_time 批复时间
     */
    public Long getReplyTime() {
        return replyTime;
    }

    /**
     * 批复时间
     * @param replyTime 批复时间
     */
    public void setReplyTime(Long replyTime) {
        this.replyTime = replyTime;
    }
}