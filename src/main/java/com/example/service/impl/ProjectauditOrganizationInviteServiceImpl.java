package com.example.service.impl;

import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.config.ServiceConfig;
import com.example.entity.ProjectParticipant;
import com.example.entity.ProjectauditOrganizationInvite;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectInfoEntityMapper;
import com.example.mapper.ProjectParticipantMapper;
import com.example.mapper.ProjectauditOrganizationInviteMapper;
import com.example.service.ProjectauditOrganizationInviteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Service
public class ProjectauditOrganizationInviteServiceImpl implements ProjectauditOrganizationInviteService {
    @Autowired
    ProjectauditOrganizationInviteMapper projectauditOrganizationInviteMapper;
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    @Autowired
    ProjectParticipantMapper projectParticipantMapper;
    //添加第三方机构项目审核邀请
    @Override
    public int addProjectauditInvite(Integer inviteAdduserId, ProjectauditOrganizationInvite projectauditOrganizationInvite) {
        projectauditOrganizationInvite.setInviteState(1);//设置状态 待操作
        projectauditOrganizationInvite.setInviteAddtime(new Date().getTime());//设置添加时间
        projectauditOrganizationInvite.setInviteAdduserId(inviteAdduserId);//添加人用户id
        Long inviteExpiration= ServiceConfig.getAUDIT_ORGANIZATION_INVITE_EXPIRATION()+new Date().getTime();//邀请过期时间
        projectauditOrganizationInvite.setInviteExpiration(inviteExpiration);
        projectauditOrganizationInviteMapper.insertPoi(projectauditOrganizationInvite);
        return projectauditOrganizationInvite.getProjectauditOrganizationInviteId();
    }
    //分页查询用户项目审核邀请信息列表
    @Override
    public List<ProjectauditOrganizationInvite> findProjectauditOInviteListByUserIdPage(Integer inviteUserId,boolean inviteExpiration, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        if(inviteExpiration){
            return  projectauditOrganizationInviteMapper.selectProjectauditOInviteListByinviteUserId(inviteUserId);
        }else{
            return projectauditOrganizationInviteMapper.selectProjectauditOInviteListByIUserIdExpirationNot(inviteUserId,null);
        }


    }
    //用户操作项目审核邀请 (第三方机构)
    @Override
    public boolean operationUserProjectauditOInvite(Integer projectInfoId,Integer projectauditOrganizationInviteId,Integer inviteEdituserId, Integer inviteState,Integer userRole) throws OperationProjectauditOInviteException {
        ProjectauditOrganizationInvite poi=new ProjectauditOrganizationInvite();
        poi.setInviteEdittime(new Date().getTime());//修改时间
        poi.setInviteEdituserId(inviteEdituserId);//修改人
        poi.setInviteState(inviteState);//修改状态
        poi.setProjectauditOrganizationInviteId(
                projectauditOrganizationInviteId);//审核信息id
        switch (inviteState){
            case 2://接受
                ProjectParticipant pP=new ProjectParticipant();//项目参与者信息
                pP.setProjectInfoId(projectInfoId);//参与项目
                pP.setUserId(inviteEdituserId);//参与者userid
                pP.setUserRole(userRole);//参与者角色
                projectParticipantMapper.insertSelective(pP);//添加项目参与者信息
                projectInfoEntityMapper.updateProjectInfoProgressByPIid(
                        projectInfoId,2);//改变邀请审核的项目进程为选择组长
                projectauditOrganizationInviteMapper.updatePOIStateByPOIId(poi);//修改审核邀请信息
                break;
            case 3://拒绝
                projectauditOrganizationInviteMapper.updatePOIStateByPOIId(poi);
                break;
            case 4://取消邀请
                break;
            default://异常
                throw new OperationProjectauditOInviteException("操作错误，无效操作");
        }
        return true;
    }
    //判断用户是否能进行审核邀请操作
    @Override
    public boolean isLegalOperationProjectauditOrganization(Integer inviteProjectId,Integer projectauditOrganizationInviteId,Integer inviteEdituserId, Integer inviteState,Integer userRole) {
        return false;
    }


}
