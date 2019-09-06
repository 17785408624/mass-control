package com.example.mapper;

import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.requstparam.OrderRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectInfoEntityMapper {
    /**
     *
     *  2019-08-28
     */
    int insert(ProjectInfoEntityWithBLOBs record);

    /**
     *
     *  2019-08-28
     */
    int insertSelective(ProjectInfoEntityWithBLOBs record);

    /**
     * 添加项目信息 包含初设和安设两条信息
     * @param projectInfoEntityWithBLOBs
     * @return
     */
    int inserBatchAC(ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs);

    /**
     * 通过项目进程状态查询项目信息列表
     * @param array 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     * @param orderRequests 排序规则
     * @return
     */
    List<ProjectInfoEntityWithBLOBs> selectListByPiProgress(
            @Param("array") int []array,
            @Param("orderRequests") OrderRequest[] orderRequests);

    /**
     * 查询项目全部信息
     * @param project_info_id 项目id
     * @return
     */
    ProjectInfoEntityWithBLOBs selectProjectInfoAll(int project_info_id);

    /**
     * 通过项目id改变项目进程信息
     * @param projectInfoId
     * @param projectInfoProgress 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     * @return
     */
    int updateProjectInfoProgressByPIid(@Param("projectInfoId") Integer projectInfoId,
                                        @Param("projectInfoProgress") Integer projectInfoProgress);
}