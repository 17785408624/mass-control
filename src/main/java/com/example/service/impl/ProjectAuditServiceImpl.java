package com.example.service.impl;

import com.example.entity.ProjectAudit;
import com.example.entity.ProjectAuditExample;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectAuditMapper;
import com.example.mapper.ProjectInfoEntityMapper;
import com.example.mapper.ProjectParticipantMapper;
import com.example.service.ProjectAuditService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectAuditServiceImpl implements ProjectAuditService {
    @Autowired
    ProjectAuditMapper projectAuditMapper;
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    @Autowired
    ProjectParticipantMapper projectParticipantMapper;
    //用户查询项目审核信息列表
    @Override
    public List<Map> findProjectAuditListByUser(Integer auditUserId, Integer auditState, Boolean isExpiration, PageOderRequest pageOderRequest) {
        PageRequest pageRequest=pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectAuditMapper.selectProjectAuditList(auditUserId, auditState, isExpiration);
    }
    //项目评审
    @Override
    public Boolean auditProject(Object projectAuditId, Integer auditUserId, Integer auditUserRole, String auditContent) {
        ProjectAudit projectAudit=new ProjectAudit();
        Integer projectInfoId=
                projectAuditMapper.selectPiIdByPaId(
                        Integer.parseInt(String.valueOf(projectAuditId)));//项目id
        Long nowDate=new Date().getTime();//当前时间
        projectAudit.setAuditUserId(auditUserId);//审核人id
        projectAudit.setAuditUserRole(auditUserRole);//审核人角色
        projectAudit.setAuditContent(auditContent);//审核内容
        projectAudit.setAuditDatetime(nowDate);//评审时间
        projectAudit.setAuditState(2);//将审核状态改为已审核
        ProjectAuditExample pae=new ProjectAuditExample();
        ProjectAuditExample.Criteria c=pae.createCriteria();
        c.andProjectAuditIdEqualTo(projectAuditId);
        pae.or(c);
        projectAuditMapper.updateByExampleSelective(projectAudit,pae);//更改项目审核信息
        projectParticipantMapper.updateUaByUidPid(
                projectInfoId,auditUserId,2);//修改项目参与者表 用户是否对项目进行过审核
        return true;
    }
   //项目审核，包含更改项目状态
    @Override
    public Boolean auditProject(Integer projectAuditId, Integer auditUserId, Integer auditUserRole, String auditContent, Integer projectInfoState) {
        Integer PiId=projectAuditMapper.selectPiIdByPaId(projectAuditId);//查询项目id
        ProjectAudit projectAudit=new ProjectAudit();
        Long nowDate=new Date().getTime();//当前时间
        projectAudit.setAuditUserId(auditUserId);//审核人id
        projectAudit.setAuditUserRole(auditUserRole);//审核人角色
        projectAudit.setAuditContent(auditContent);//审核内容
        projectAudit.setAuditDatetime(nowDate);//评审时间
        projectAudit.setAuditState(2);//将审核状态改为已审核
        ProjectAuditExample pae=new ProjectAuditExample();
        ProjectAuditExample.Criteria c=pae.createCriteria();
        c.andProjectAuditIdEqualTo(projectAuditId);
        //pae.
        projectAuditMapper.updateByExampleSelective(projectAudit,pae);//修改项目审核信息表
        projectInfoEntityMapper.
                updateProjectInfoStateByPIid(PiId,projectInfoState);//更改项目状态
        projectInfoEntityMapper.updatePiProgressByPIid(PiId,5,null);//改变项目进程
        projectParticipantMapper.updateUaByUidPid(PiId,auditUserId,
                2);//修改项目参与者表 用户是否对项目进行过审核
        return true;
    }
    //项目审核，包含更改项目状态
    @Override
    public Boolean auditProject(Integer projectAuditId, Integer auditUserId, Integer auditUserRole, String auditContent, Integer projectInfoState, Long auditTime) {
        Integer PiId=projectAuditMapper.selectPiIdByPaId(projectAuditId);//查询项目id
        ProjectAudit projectAudit=new ProjectAudit();
        if(auditTime==null||auditTime==0){
            auditTime =new Date().getTime();//当前时间
        }
        projectAudit.setAuditUserId(auditUserId);//审核人id
        projectAudit.setAuditUserRole(auditUserRole);//审核人角色
        projectAudit.setAuditContent(auditContent);//审核内容
        projectAudit.setAuditDatetime(auditTime);//评审时间
        projectAudit.setAuditState(2);//将审核状态改为已审核
        ProjectAuditExample pae=new ProjectAuditExample();
        ProjectAuditExample.Criteria c=pae.createCriteria();
        c.andProjectAuditIdEqualTo(projectAuditId);
        //pae.
        projectAuditMapper.updateByExampleSelective(projectAudit,pae);
        projectInfoEntityMapper.
                updateProjectInfoStateByPIid(PiId,projectInfoState);//更改项目状态
        projectInfoEntityMapper.updatePiProgressByPIid(PiId,5,null);//改变项目进程
        projectParticipantMapper.updateUaByUidPid(PiId,auditUserId,
                2);//修改项目参与者表 用户是否对项目进行过审核
        return true;
    }


    //能源局或者煤监局审核项目
    @Override
    public Boolean auditProjectBureau(Integer auditUserId,
                                      Integer auditUserRole,
                                      String auditContent,
                                      Integer projectInfoState,
                                      Long auditTime,
                                      Integer projectInfoId) {
        ProjectAudit projectAudit=new ProjectAudit();
        if(auditTime==null||auditTime==0){
            auditTime =new Date().getTime();//当前时间
        }
        projectAudit.setAuditUserId(auditUserId);//审核人id
        projectAudit.setAuditUserRole(auditUserRole);//审核人角色
        projectAudit.setAuditContent(auditContent);//审核内容
        projectAudit.setAuditDatetime(auditTime);//评审时间
        projectAudit.setAddDatetime(auditTime);
        projectAudit.setAuditState(2);//将审核状态改为已审核
        projectAudit.setProjectInfoId(projectInfoId);//审核的项目id
        projectAuditMapper.insertSelective(projectAudit);//添加审核项目信息
        projectInfoEntityMapper.
                updateProjectInfoStateByPIid(projectInfoId,projectInfoState);//更改项目状态
        projectInfoEntityMapper.updatePiProgressByPIid(projectInfoId,5,null);//改变项目进程
        projectParticipantMapper.updateUaByUidPid(projectInfoId,auditUserId,
                2);//修改项目参与者表 用户是否对项目进行过审核
        return true;

    }
    //查询专家评测信息列表
    @Override
    public List<Map> findEaListByPiidlAtAur(Integer projectInfoId, Integer auditState, Integer auditUserRole) {

        return  projectAuditMapper.selectEaListByPiidlAtAur(projectInfoId,auditState,auditUserRole);
    }

}
