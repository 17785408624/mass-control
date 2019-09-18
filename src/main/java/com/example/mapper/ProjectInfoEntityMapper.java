package com.example.mapper;

import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.requstparam.OrderRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
     *
     * @param PUid 项目参与者id
     * @param array
     * @param orderRequests
     * @return
     */
    List<ProjectInfoEntityWithBLOBs> selectListByPiProgressPUid(
            @Param("PUid") int PUid,
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

    /**
     * 通过项目信息id改变项目审核开始时间和过期时间
     * @param projectInfoId 项目开始时间
     * @param projectInfoAuditstartdate 项目审核开始时间
     * @param projectInfoExpiration 项目审核过期时间
     * @return
     */
    Integer updateProjectInfoDateByPIid(@Param("projectInfoId") Integer projectInfoId,
                                          @Param("projectInfoAuditstartdate") Long projectInfoAuditstartdate,
                                          @Param("projectInfoExpiration") Long projectInfoExpiration);

    /**
     * 通过项目id改变项目状态
     * @param projectAuditId 审核信息id
     * @param projectInfoState 改变的项目状态
     * @return
     */
    Integer updateProjectInfoState(@Param("projectAuditId") Integer projectAuditId,
                                   @Param("projectInfoState") Integer projectInfoState);

    /**
     *
     * @return
     */
    /**
     *  查询未过期的项目审核信息列表
     * @param projectInfoProgress
     * @param participantUid
     * @param participantUrole
     * @return
     */
    Map selectPIExpirationNot(Integer projectInfoProgress,
                                                        Integer participantUid,
                                                        Integer participantUrole);
}