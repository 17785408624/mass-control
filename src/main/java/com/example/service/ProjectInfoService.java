package com.example.service;

import com.example.common.exceptiondefine.OperationServiceException;
import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.requstparam.PageOderRequest;

import java.util.List;
import java.util.Map;

public interface ProjectInfoService {
    /**
     *添加项目信息 项目入库
     * @param projectInfoEntityWithBLOBs 项目信息
     * @param  user_role 1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     * @return
     */
    int addProjectInfo(int user_id,int user_role,
            ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs);

    /**
     * 查询 进程为选择机构和选择专家组 的项目信息列表
     * @return
     */
    List<ProjectInfoEntityWithBLOBs>findProjectInfoChooseByProgressOE();
    /**
     * 查询 进程为选择机构和选择专家组 的项目列表
     * @param pageOderRequest 排序分页信息
     * @return
     */
    List<ProjectInfoEntityWithBLOBs>findProjectInfoChooseByProgressOE(PageOderRequest pageOderRequest);

    /**
     * 查询 进程为等待批复的项目信息
     * @param pageOderRequest
     * @return
     */
    List<ProjectInfoEntityWithBLOBs>findProjectInfoToProgress5(PageOderRequest pageOderRequest);
    /**
     * 通过项目id查询项目全部信息
     * @return
     */
    ProjectInfoEntityWithBLOBs findProjectInfoFullByPid(
            Integer project_info_id);

    /**
     * 抽取审核项目的第三方机构
     * @param excludeUids 回避的单位用户id
     * @return
     */
    Map extractionProjectOrganization(Integer[]excludeUids);

    /**
     * 分页查询 进程为选择专家组组长的项目信息列表
     * @param pageOderRequest
     * @return
     */
    List<ProjectInfoEntityWithBLOBs> findProjectInfoProgressLeader(PageOderRequest pageOderRequest,Integer Uid);

    /**
     * 抽取审核项目的专家组
     * @param extractNum 抽取人数
     * @param expert_info_educations 专家学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
     * @param expert_info_workmajors 专家从事专业  1采矿工程2通风安全3供电4四大件5水文地质6总平面工程7造价8环保节能9土建工程
     * @return
     */
    List<Map> extractionProjectExpert(Integer extractNum,Integer[]expert_info_educations,
                                Integer[]expert_info_workmajors) throws OperationServiceException;

}
