package com.example.service.impl;

import com.example.common.exceptiondefine.OperationServiceException;
import com.example.config.ServiceConfig;
import com.example.entity.*;
import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.*;
import com.example.service.ProjectInfoService;
import com.github.pagehelper.PageHelper;
import com.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    @Autowired
    ProjectReplyMapper projectReplyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProjectParticipantMapper projectParticipantMapper;
    @Autowired
    ProjectAuditMapper projectAuditMapper;
    @Autowired
    ProjectauditOrganizationInviteMapper projectauditOrganizationInviteMapper;
    @Autowired
    ProjectauditExpertInviteMapper projectauditExpertInviteMapper;

    //添加项目信息
    @Override
    public int addProjectInfo(int user_id, int user_role, ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs) {
        projectInfoEntityWithBLOBs.setProjectInfoAddtime(new Date().getTime());
        projectInfoEntityWithBLOBs.setProjectInfoAdduserid(user_id);
        Integer projectInfoId = null;//项目id
        ProjectParticipant p = new ProjectParticipant();//项目参与者信息
        p.setUserRole(user_role);//用户角色
        for (int i = 1; i <= 2; i++) {
            projectInfoEntityWithBLOBs.setProjectInfoType(i);//设置项目类型
            projectInfoEntityWithBLOBs.setProjectInfoId(null);
            projectInfoEntityMapper.insertSelective(projectInfoEntityWithBLOBs);//添加项目信息
            projectInfoId = projectInfoEntityWithBLOBs.getProjectInfoId();//项目id
            p.setProjectInfoId(projectInfoId);//参与者信息项目id
            projectParticipantMapper.insertSelective(p);//添加项目参与者信息
        }
        return projectInfoId;
    }

    //查询项目信息列表
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoChooseByProgressOE() {
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{1, 3}, null, null, null);
    }

    //查询 进程为选择机构和选择专家组 的项目列表
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoChooseByProgressOE(PageOderRequestMap pageOderRequest) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        Map param = pageOderRequest.getParam();
        Integer[] projectInfoProgressoperations = null;//项目进程状态
        String projectInfoName = null;

        if (!PublicUtil.mapKeyIsNull_keyString(param, "projectInfoProgressoperations")) {//判断是否传入条件查询参数
            List list = new ArrayList();
            list = (List) param.get("projectInfoProgressoperations");
            Object[] o = list.toArray();
            projectInfoProgressoperations = PublicUtil.convertArray(Integer.class, o);//object数组转化Integer数组
            if (projectInfoProgressoperations.length < 1) {
                projectInfoProgressoperations = null;
            }
        }
        if (!PublicUtil.mapKeyIsNull_keyString(param, "projectInfoName")) {//判断是否传入项目搜索名
            projectInfoName = String.valueOf(param.get("projectInfoName"));
        }
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{1, 3}, pageOderRequest.getOrderRequests(), projectInfoName, projectInfoProgressoperations);

    }

    //分页查询 进程为待批复的项目信息列表
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoToProgress5(PageOderRequest pageOderRequest) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{5}, pageOderRequest.getOrderRequests(), null, null);
    }

    /**
     * 查询 进程为等待批复的项目信息
     *
     * @param pageOderRequest
     * @param projectInfoName
     * @return
     */
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoToProgress5(PageOderRequest pageOderRequest, String projectInfoName) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{5}, pageOderRequest.getOrderRequests(), projectInfoName, null);

    }

    //通过项目id查询项目全部信息
    @Override
    public ProjectInfoEntityWithBLOBs findProjectInfoFullByPid(Integer project_info_id) {
        ProjectInfoEntityWithBLOBs pe = projectInfoEntityMapper.
                selectProjectInfoAll(project_info_id);
        return pe;
    }

    //抽取审核项目的第三方机构
    @Override
    public Map extractionProjectOrganization(Integer[] excludeUids) {
        Integer excludeNum = 0;//回避单位的数量
        if (excludeUids != null) {
            excludeNum = excludeUids.length;
        }
        int userNum = userMapper.
                selectUserNum(2, 2);//已审核的第三方机构数量
        userNum = userNum - excludeNum;
        /**
         * 获取机构总数，在随机获取一个一到机构总数的数字
         * 随机获取0-机构总数 不包含0 这里结果加1 random
         */
        int random = (int) ((Math.random() * userNum)) + 1;//随机选择机构
        Map map = userMapper.selectOrganizationInfoLimit(2, random - 1, 1, excludeUids).get(0);
        return map;
    }

    //分页查询 进程为选择专家组组长的项目信息列表
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoProgressLeader(PageOderRequest pageOderRequest, Integer Uid, String searchCondition) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectInfoEntityMapper.selectListByPiProgressPUid(Uid, new int[]{2}, pageOderRequest.getOrderRequests(),
                searchCondition);
    }

    //抽取审核项目的专家组
    @Override
    public List<Map> extractionProjectExpert(Integer extractNum, Integer[] expert_info_educations, Integer[] expert_info_workmajors) throws OperationServiceException {
        Integer[] userIds = userMapper.selectUserIdArrayByUrUs(
                1,
                2,
                expert_info_educations,
                expert_info_workmajors);//查询符合条件的用户id
        List<Map> resultList = new ArrayList<>();//抽取后的用户信息
        if (userIds == null || userIds.length < 1) {
            throw new OperationServiceException("没有符合条件的专家");
        } else if (userIds.length < extractNum) {
            throw new OperationServiceException("符合条件的专家没有达到指定人数");
        }
        List<Map> listMap = userMapper.selectExpertInfoListById(userIds);//符合条件的用户信息
        if (extractNum >= listMap.size()) {
            return listMap;
        } else {
            return randomExtractionExpert(
                    listMap, extractNum);//返回随机抽取的专家信息

        }
    }

    /**
     * 随机返回抽取人数的数量id
     *
     * @param listMap    源数据集合
     * @param extractNum 返回的数据集合
     * @return
     */
    private List<Map> randomExtractionExpert(List<Map> listMap, int extractNum) {
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < extractNum; i++) {
            int random = (int) (Math.random() * (listMap.size()));//返回一个0—list长度的随机数
            resultList.add(listMap.get(random));
            listMap.remove(random);
        }
        return resultList;
    }

    /**
     * 查询 进程为评审项目的项目信息
     *
     * @param pageOderRequest
     * @return
     */
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoToProgress4(PageOderRequestMap pageOderRequest) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        Map param = pageOderRequest.getParam();
        String condition = null;
        if (param != null && param.containsKey("condition") && param.get("condition") != null && !param.get("condition").equals(null)) {
            condition = String.valueOf(param.get("condition"));//查询条件 项目名
        }
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{4}, pageOderRequest.getOrderRequests(), condition, null);
    }

    //查询全部项目信息
    @Override
    public List<Map> findPiAll(PageOderRequest pageOderRequest, String conditionSearch) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());//调用分页
        OrderRequest[] or = pageOderRequest.getOrderRequests();
        if (or == null || or.length < 1) {//未传入排序字段默认按照添加时间进行排序
            OrderRequest o = new OrderRequest();
            o.setOrderName("project_info_addtime");
            o.setOrderRule("desc");
            or = new OrderRequest[]{o};

        }
        return projectInfoEntityMapper.selectPiAll(or, conditionSearch);//查询全部项目信息

    }

    //查询项目历程
    @Override
    public Map findProjectCourse(Integer projectInfoId) {
        ProjectInfoEntityWithBLOBs pi = projectInfoEntityMapper.selectProjectInfoAll(projectInfoId);
        Integer projectInfoProgress = pi.getProjectInfoProgress();//项目进程
        Map projectCourse = new HashMap();
        switch (projectInfoProgress) {
            case 6://6完成
                ProjectReply projectReply = projectReplyMapper.selectPrFullByPiid(projectInfoId);//查询批复信息
                Long replyTime = projectReply.getReplyTime();//批复时间
                projectCourse.put("replyTime", replyTime);
            case 5://5等待批复
                List<ProjectParticipant> listp = projectParticipantMapper.
                        selectPpByPiid(projectInfoId, ServiceConfig.PROJECT_PARTICIPANT_DECISION,
                                2);//查询已评审并且能对项目状态进行操作的项目参与者信息
                int[] auditUserIds = new int[listp.size()];//项目参与者的用户id
                for (int i = 0; i < listp.size(); i++) {
                    auditUserIds[i] = listp.get(i).getUserId();
                }
                //通过项目ip和项目评审人id查询项目评审信息
                List<ProjectAudit> listPa = projectAuditMapper.selectPaByPiidAuid(projectInfoId, auditUserIds);
                Long[] auditDatetimes = new Long[listPa.size()];//评审时间
                for (int i = 0; i < listPa.size(); i++) {
                    auditDatetimes[i] = listPa.get(i).getAuditDatetime();
                }
                if (auditDatetimes.length > 0) {
                    Arrays.sort(auditDatetimes);
                    projectCourse.put("projectAuditTime", auditDatetimes[listPa.size()]);//项目评审情况
                }

            case 4://4等待项目评审
                Long ProjectInfoAuditstartdate = pi.getProjectInfoAuditstartdate();//项目审核开始时间
                projectCourse.put("ProjectInfoAuditstartdate", ProjectInfoAuditstartdate);
            case 3://3等待选择专家组
            case 2://2等待选择组长
            case 1://1等待选择机构
                Long projectInfoAddtime = pi.getProjectInfoAddtime();//项目添加时间
                projectCourse.put("projectInfoAddtime", projectInfoAddtime);
                projectCourse.put("projectInfoProgress", pi.getProjectInfoProgress());//当前项目进程
                break;
        }
        return projectCourse;
    }

    //查询项目审核邀请信息
    @Override
    public Map findProjectAuditInfo(Integer projectInfoId, Integer project_info_progress) {
        Map projectAuditInfo=new HashMap();//审核邀请信息
        switch (project_info_progress) {
            case 3://3选择专家组
                List<ProjectauditExpertInvite> listPei = projectauditExpertInviteMapper.selectPeiNewestByPid(projectInfoId);
                projectAuditInfo.put("listPei",listPei);//专家组审核邀请信息
            case 2://2选择组长
                ProjectauditExpertInvite pei = projectauditExpertInviteMapper.selectPeiNewestLeaderByPid(projectInfoId);
                projectAuditInfo.put("pei",pei);//专家组长审核邀请信息
            case 1://1选择机构
                ProjectauditOrganizationInvite poi = projectauditOrganizationInviteMapper.selectPoiNewestByPid(projectInfoId);
                projectAuditInfo.put("poi",poi);//机构审核邀请信息
                break;
//            case 4://4项目评审
//                break;
//            case 5://5批复
//                break;
//            case 6://6完成
//                break;
        }
        return projectAuditInfo;
    }

}
