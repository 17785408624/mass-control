package com.example.service;

import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.requstparam.PageOderRequest;

import java.util.List;

public interface ProjectInfoService {
    /**
     *添加项目信息 项目入库
     * @param projectInfoEntityWithBLOBs
     * @return
     */
    int addProjectInfo(int user_id,
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
}
