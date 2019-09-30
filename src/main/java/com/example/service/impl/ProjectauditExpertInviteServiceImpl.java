package com.example.service.impl;

import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.config.ServiceConfig;
import com.example.entity.ExpertAudit;
import com.example.entity.ProjectAudit;
import com.example.entity.ProjectParticipant;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.requstparam.InsertPEinviteBatch;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.*;
import com.example.service.ProjectauditExpertInviteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Service
public class ProjectauditExpertInviteServiceImpl implements ProjectauditExpertInviteService {
    @Autowired
    ProjectauditExpertInviteMapper projectauditExpertInviteMapper;
    @Autowired
    ProjectParticipantMapper projectParticipantMapper;
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    @Autowired
    ProjectAuditMapper projectAuditMapper;
    @Autowired
    ExpertAuditMapper expertAuditMapper;
    @Autowired
    ExpertAuditInfoMapper expertAuditInfoMapper;
    //添加项目审核邀请
    @Override
    public int addPEInvite(ProjectauditExpertInvite pei, Integer inviteAdduserId) {
        Long nowDate=new Date().getTime();
        pei.setInviteAddtime(nowDate);//添加时间
        pei.setInviteExpiration(
                ServiceConfig.getAUDIT_INVITE_EXPIRATION(nowDate));//过期时间
        pei.setInviteAdduserId(inviteAdduserId);//添加人id
        projectauditExpertInviteMapper.insertSelective(pei);//添加项目审核邀请信息
        return pei.getProjectauditExpertInviteId();
    }
    //分页查询用户项目审核邀请信息列表(专家)
    @Override
    public List<ProjectauditExpertInvite> findPEInviteListByUserIdPage(Integer inviteUserId, boolean inviteExpiration, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        if(inviteExpiration){
            return  projectauditExpertInviteMapper.selectPEInviteListByinviteUserId(inviteUserId);
        }else{
            return projectauditExpertInviteMapper.selectPEInviteListByinviteUserIdExpirationNot(inviteUserId,null);
        }
    }
    // 用户操作项目审核邀请 (专家)
    @Override
    public Boolean operationUserPEInvite(Integer projectauditExpertInviteId,
                                     Integer inviteEdituserId,
                                     Integer inviteState,
                                     Integer userRole,
                                     Integer inviteType) throws OperationProjectauditOInviteException {
        ProjectauditExpertInvite projectauditExpertInvite=
                projectauditExpertInviteMapper.
                        selectPEinviteById(projectauditExpertInviteId);//查询审核邀请信息
        Integer projectInfoId=projectauditExpertInvite.getProjectInfoId();
        Long nowDate =new Date().getTime();
        ProjectauditExpertInvite pei =new ProjectauditExpertInvite();//审核邀请信息（专家）
        pei.setInviteEdittime(nowDate);//修改时间
        pei.setInviteEdituserId(inviteEdituserId);//修改人
        pei.setInviteState(inviteState);//修改状态
        pei.setProjectauditExpertInviteId(
                projectauditExpertInviteId);//审核信息id
        switch (inviteState){
            case 2://接受
                Integer projectinfoProgress = null;//邀请审核的项目进程
                switch (inviteType){//邀请类型
                    case 1://组长
                        projectinfoProgress=3;//项目进程为选择专家组组员
                        projectInfoEntityMapper.updateProjectInfoProgressByPIid(
                                projectInfoId,projectinfoProgress);//改变邀请审核的项目进程为选择专家组组员
                        userRole=6;//改变用户角色
                        break;
                    case 2://组员
                        projectinfoProgress=null;//项目进程为项目评审
                        break;
                }
                ProjectAudit projectAudit=new ProjectAudit();//添加项目审核信息
                projectAudit.setAddDatetime(nowDate);
                projectAudit.setAuditUserId(inviteEdituserId);
                projectAudit.setProjectInfoId(projectInfoId);
                projectAudit.setAuditUserRole(userRole);
                projectAuditMapper.insertSelective(projectAudit);//添加项目审核信息
                ProjectParticipant pP=new ProjectParticipant();//项目参与者信息
                pP.setProjectInfoId(projectInfoId);//参与项目
                pP.setUserId(inviteEdituserId);//参与者userid
                pP.setUserRole(userRole);//参与者角色
                projectParticipantMapper.insertSelective(pP);//添加项目参与者信息
                projectauditExpertInviteMapper.updatePEInviteStateById(pei);//修改审核邀请信息
                ExpertAudit expertAudit=new ExpertAudit();//专家评测数据信息
                expertAudit.setAddDatetime(nowDate);//数据添加时间
                expertAudit.setByauditUserId(inviteEdituserId);//被评审人id
                expertAudit.setAddUserId(inviteEdituserId);//添加人id
                expertAudit.setProjectInfoId(projectInfoId);//关联项目id
                expertAuditMapper.insertSelective(expertAudit);//添加专家评测数据
                expertAuditInfoMapper.updateNotAuditNumByPid(projectInfoId,true,1);//改变未审核人数
                break;
            case 3://拒绝
                projectauditExpertInviteMapper.updatePEInviteStateById(pei);//修改审核状态
                break;
            case 4://取消邀请
                break;
            default:
                break;
        }

        return true;
    }
    //添加项目审核邀请(专家组组员)
    @Override
    public Integer addPEInvite(InsertPEinviteBatch insertPEinviteBatch, Integer inviteAdduserId) {
        Long nowDate=new Date().getTime();
        insertPEinviteBatch.setInviteAddtime(nowDate);//添加时间
        insertPEinviteBatch.setInviteAdduserId(inviteAdduserId);//添加人id
        insertPEinviteBatch.setInviteState(1);//状态 (1等待操作 2接受 3拒绝 4取消邀请)
        insertPEinviteBatch.setInviteType(2);// 邀请类型(1组长 2组员)
        insertPEinviteBatch.setInviteExpiration(
                ServiceConfig.getAUDIT_INVITE_EXPIRATION(nowDate));//邀请过期时间
        projectInfoEntityMapper.updateProjectInfoProgressByPIid(
                insertPEinviteBatch.getProjectInfoId(),
                4);//将项目进程改为项目评审
        projectInfoEntityMapper.updateProjectInfoDateByPIid(
                insertPEinviteBatch.getProjectInfoId(),
                nowDate,ServiceConfig.getPROJECT_AUDIT_EXPIRATION(nowDate));//修改项目审核开始时间
        return projectauditExpertInviteMapper.insertPEinviteBatch(insertPEinviteBatch,insertPEinviteBatch.getExperInfos());//添加项目审核邀请(专家组组员)

    }
}
