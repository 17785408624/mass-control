package com.example.service.impl;

import com.example.entity.ProjectAudit;
import com.example.entity.ProjectAuditExample;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectAuditMapper;
import com.example.mapper.ProjectInfoEntityMapper;
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
        return true;
    }
   //项目审核，包含更改项目状态
    @Override
    public Boolean auditProject(Integer projectAuditId, Integer auditUserId, Integer auditUserRole, String auditContent, Integer projectInfoState) {
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
        pae.or(c);
        projectAuditMapper.updateByExampleSelective(projectAudit,pae);
        projectInfoEntityMapper.
                updateProjectInfoState(projectAuditId,projectInfoState);//更改项目状态
        return true;
    }
}
