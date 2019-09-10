package com.example.service.impl;

import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.config.ServiceConfig;
import com.example.entity.ProjectParticipant;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectInfoEntityMapper;
import com.example.mapper.ProjectParticipantMapper;
import com.example.mapper.ProjectauditExpertInviteMapper;
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
    //添加项目审核邀请
    @Override
    public int addPEInvite(ProjectauditExpertInvite pei, Integer inviteAdduserId) {
        Long nowDate=new Date().getTime();
        pei.setInviteAddtime(nowDate);//添加时间
        pei.setInviteExpiration(
                ServiceConfig.getAUDIT_ORGANIZATION_INVITE_EXPIRATION()+nowDate);//过期时间
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
    public Boolean operationUserPEInvite(Integer projectInfoId,
                                     Integer projectauditOrganizationInviteId,
                                     Integer inviteEdituserId,
                                     Integer inviteState,
                                     Integer userRole,
                                     Integer inviteType) throws OperationProjectauditOInviteException {
        ProjectauditExpertInvite pei =new ProjectauditExpertInvite();
        pei.setInviteEdittime(new Date().getTime());//修改时间
        pei.setInviteEdituserId(inviteEdituserId);//修改人
        pei.setInviteState(inviteState);//修改状态
        pei.setProjectauditExpertInviteId(
                projectauditOrganizationInviteId);//审核信息id
        switch (inviteState){
            case 2://接受
                Integer projectinfoProgress = null;//邀请审核的项目进程
                switch (inviteType){//邀请类型
                    case 1://组长
                        projectinfoProgress=3;//项目进程为选择专家组组员
                        break;
                    case 2://组员
                        projectinfoProgress=4;//项目进程为项目评审
                        break;
                }
                ProjectParticipant pP=new ProjectParticipant();//项目参与者信息
                pP.setProjectInfoId(projectInfoId);//参与项目
                pP.setUserId(inviteEdituserId);//参与者userid
                pP.setUserRole(userRole);//参与者角色
                projectParticipantMapper.insertSelective(pP);//添加项目参与者信息
                projectInfoEntityMapper.updateProjectInfoProgressByPIid(
                        projectInfoId,projectinfoProgress);//改变邀请审核的项目进程为下一步
                projectauditExpertInviteMapper.updatePEInviteStateById(pei);//修改审核邀请信息
                break;
            case 3://拒绝
                projectauditExpertInviteMapper.updatePEInviteStateById(pei);
                break;
            case 4://取消邀请
                break;
            default:
                break;
        }

        return true;
    }
}
