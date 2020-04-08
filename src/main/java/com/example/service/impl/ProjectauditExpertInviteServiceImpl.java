package com.example.service.impl;

import com.example.common.exceptiondefine.AuditOperationServiceException;
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
import com.example.service.vice.ExpertInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
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
    @Autowired
    UserMapper userMapper;
    @Autowired
    ExpertInfo expertInfo;

    //添加项目审核邀请(专家组长)
    @Override
    public int addPEInvite(ProjectauditExpertInvite pei, Integer inviteAdduserId) throws AuditOperationServiceException {
        if (projectauditExpertInviteMapper.countPeiNumByPiidIsIt(
                pei.getProjectInfoId(), pei.getInviteType(), 1) > 0) {
            throw new AuditOperationServiceException("已发出组长审核邀请，请勿重复操作");

        }
        ;
        Long nowDate = new Date().getTime();
        pei.setInviteAddtime(nowDate);//添加时间
        pei.setInviteExpiration(
                ServiceConfig.getAUDIT_INVITE_EXPIRATION(nowDate));//过期时间
        pei.setInviteAdduserId(inviteAdduserId);//添加人id

        projectauditExpertInviteMapper.insertSelective(pei);//添加项目审核邀请信息
        projectInfoEntityMapper.updatePiProgressByPIid(pei.getProjectInfoId(), null, 2);
        return pei.getProjectauditExpertInviteId();
    }

    //分页查询用户项目审核邀请信息列表(专家)
    @Override
    public List findPEInviteListByUserIdPage(Integer inviteUserId, Object[] inviteStates, boolean inviteExpiration, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        if (inviteExpiration) {
            return projectauditExpertInviteMapper.selectPEInviteListByinviteUserId(inviteUserId, inviteStates);
        } else {
            return projectauditExpertInviteMapper.selectPEInviteListByinviteUserIdExpirationNot(inviteUserId, null);
        }
    }

    // 用户操作项目审核邀请 (专家)
    @Override
    public Boolean operationUserPEInvite(Integer projectauditExpertInviteId,
                                         Integer inviteEdituserId,
                                         Integer inviteState,
                                         Integer userRole,
                                         Integer inviteType) throws OperationProjectauditOInviteException {
        ProjectauditExpertInvite projectauditExpertInvite =
                projectauditExpertInviteMapper.
                        selectPEinviteById(projectauditExpertInviteId);//查询审核邀请信息
        Integer projectInfoId = projectauditExpertInvite.getProjectInfoId();
        Long nowDate = new Date().getTime();
        ProjectauditExpertInvite pei = new ProjectauditExpertInvite();//审核邀请信息（专家）
        pei.setInviteEdittime(nowDate);//修改时间
        pei.setInviteEdituserId(inviteEdituserId);//修改人
        pei.setInviteState(inviteState);//修改状态
        pei.setProjectauditExpertInviteId(
                projectauditExpertInviteId);//审核信息id
        switch (inviteState) {
            case 2://接受
                Integer projectinfoProgress = null;//邀请审核的项目进程
                switch (inviteType) {//邀请类型
                    case 1://组长
                        projectinfoProgress = 3;//项目进程为选择专家组组员
                        projectInfoEntityMapper.updatePiProgressByPIid(
                                projectInfoId, projectinfoProgress, 1);//改变邀请审核的项目进程为选择专家组组员
                        userRole = 6;//改变用户角色
                        break;
                    case 2://组员
                        projectinfoProgress = null;//项目进程为项目评审
                        break;
                }
                ProjectAudit projectAudit = new ProjectAudit();//添加项目审核信息
                projectAudit.setAddDatetime(nowDate);
                projectAudit.setAuditUserId(inviteEdituserId);
                projectAudit.setProjectInfoId(projectInfoId);
                projectAudit.setAuditUserRole(userRole);
                projectAuditMapper.insertSelective(projectAudit);//添加项目审核信息
                ProjectParticipant pP = new ProjectParticipant();//项目参与者信息
                pP.setProjectInfoId(projectInfoId);//参与项目
                pP.setUserId(inviteEdituserId);//参与者userid
                pP.setUserRole(userRole);//参与者角色
                projectParticipantMapper.insertSelective(pP);//添加项目参与者信息
                projectauditExpertInviteMapper.updatePEInviteStateById(pei);//修改审核邀请信息
                ExpertAudit expertAudit = new ExpertAudit();//专家评测数据信息
                expertAudit.setAddDatetime(nowDate);//数据添加时间
                expertAudit.setByauditUserId(inviteEdituserId);//被评审人id
                expertAudit.setAddUserId(inviteEdituserId);//添加人id
                expertAudit.setProjectInfoId(projectInfoId);//关联项目id
                expertAuditMapper.insertSelective(expertAudit);//添加专家评测数据
                expertAuditInfoMapper.updateNotAuditNumByPid(projectInfoId, true, 1);//改变未审核人数

                projectInfoEntityMapper.updatePiProgressByPIid(
                        projectInfoId, null, 1);//初始项目进程状态
                break;
            case 3://拒绝
                projectauditExpertInviteMapper.updatePEInviteStateById(pei);//修改审核状态
                projectInfoEntityMapper.updatePiProgressByPIid(
                        projectInfoId, null, 4);//改变项目进程状态为已拒绝
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
        Long nowDate = new Date().getTime();
        insertPEinviteBatch.setInviteAddtime(nowDate);//添加时间
        insertPEinviteBatch.setInviteAdduserId(inviteAdduserId);//添加人id
        insertPEinviteBatch.setInviteState(1);//状态 (1等待操作 2接受 3拒绝 4取消邀请)
        insertPEinviteBatch.setInviteType(2);// 邀请类型(1组长 2组员)
        insertPEinviteBatch.setInviteExpiration(
                ServiceConfig.getAUDIT_INVITE_EXPIRATION(nowDate));//邀请过期时间
        auditstart(insertPEinviteBatch.getProjectInfoId());
        projectauditExpertInviteMapper.insertPEinviteBatch(insertPEinviteBatch, insertPEinviteBatch.getExperInfos());//添加项目审核邀请(专家组组员);
        projectInfoEntityMapper.updatePiProgressByPIid(
                insertPEinviteBatch.getProjectInfoId(),
                null, 2);//将项目进程状态改为等待邀请对象相应
        return 1;

    }

    //查询项目的专家组成员审核邀请信息 不包含过期以及取消的邀请
    @Override
    public List<Map> findPeiByPid(Integer projectInfoId) {

        return projectauditExpertInviteMapper.selectPeiByPid(projectInfoId, new Integer[]{1, 2, 3});
    }

    //抽取专家
    @Override
    public List extractionExpert(String domainType, Object[] expert_info_educations, Object[] excludeCompanyNames,
            List majorRequire,Integer priorityNum,boolean repeatedlyExtraction) {
        String domainTypeDefult = "declaredesign";//申报专业默认分组字段
        domainType = domainType == null || domainType.equals("") || domainType.equals(" ") ? "default" : domainTypeDefult;
        List userGroupDomain = userMapper.selectExpertGroupDomain(domainType, expert_info_educations, excludeCompanyNames);//获取专业分组数据
        String[]aaa=expertInfo.drawRandomExpert(userGroupDomain,majorRequire);
        return userGroupDomain;
    }

    /**
     * 开始审核项目，将项目进程改为等待项目评审
     *
     * @param projectInfoId 项目id
     * @return
     */
    private void auditstart(Integer projectInfoId) {
        Long nowDate = new Date().getTime();
        projectInfoEntityMapper.updatePiProgressByPIid(
                projectInfoId,
                4, 1);//将项目进程改为项目评审
        projectInfoEntityMapper.updateProjectInfoDateByPIid(
                projectInfoId,
                nowDate, ServiceConfig.getPROJECT_AUDIT_EXPIRATION(nowDate));//修改项目审核开始时间和审核过期时间


    }
}
