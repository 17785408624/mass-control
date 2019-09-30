package com.example.service;

public interface ProjectReplyService {
    /**
     * 项目批复操作
     * 1添加项目批复信息
     * 2修改项目进程为 6完成
     * @param projectInfoId 项目id
     * @param replyContent 批复内容
     * @param userId 添加人id
     * @param replyTime 批复时间
     * @return
     */
    Boolean ReplyProject(Integer projectInfoId,String replyContent,Integer userId,Long replyTime);
    Boolean ReplyProject(Integer projectInfoId,String replyContent,Integer userId);

}
