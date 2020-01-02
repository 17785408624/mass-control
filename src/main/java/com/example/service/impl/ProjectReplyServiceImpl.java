package com.example.service.impl;

import com.example.entity.ProjectReply;
import com.example.mapper.ProjectInfoEntityMapper;
import com.example.mapper.ProjectReplyMapper;
import com.example.service.ProjectReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectReplyServiceImpl implements ProjectReplyService {
    @Autowired
    ProjectReplyMapper projectReplyMapper;
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    //项目批复操作
    @Override
    public Boolean ReplyProject(Integer projectInfoId, String replyContent, Integer userId, Long replyTime) {
        Long newDate=new Date().getTime();//当前时间
        ProjectReply projectReply=new ProjectReply();//批复信息类
        projectReply.setProjectInfoId(projectInfoId);//项目id
        projectReply.setReplyAddtime(newDate);//添加时间
        projectReply.setReplyAdduserId(userId);//添加人id
        projectReply.setReplyContent(replyContent);//项目批复内容
        projectReply.setReplyTime(replyTime);//审批时间
        projectReplyMapper.insertSelective(projectReply);//添加项目批复信息
        projectInfoEntityMapper.updatePiProgressByPIid(projectInfoId,6,1);//改变项目进程
        return true;
    }
    //项目批复操作
    @Override
    public Boolean ReplyProject(Integer projectInfoId, String replyContent,Integer userId) {
        Long newDate=new Date().getTime();//当前时间
        ProjectReply projectReply=new ProjectReply();//批复信息类
        projectReply.setProjectInfoId(projectInfoId);//项目id
        projectReply.setReplyAddtime(newDate);//添加时间
        projectReply.setReplyAdduserId(userId);//添加人id
        projectReply.setReplyContent(replyContent);//项目批复内容
        projectReply.setReplyTime(newDate);//审批时间
        projectReplyMapper.insertSelective(projectReply);//添加项目批复信息
        projectInfoEntityMapper.updatePiProgressByPIid(projectInfoId,6,null);//改变项目进程
        return true;
    }
}
