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
     * @param projectInfoProgress 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     * @param orderRequests 排序规则
     * @param projectInfoName 项目名字
     * @param
     * @return
     */
    List<ProjectInfoEntityWithBLOBs> selectListByPiProgress(
            @Param("projectInfoProgress") int []projectInfoProgress,
            @Param("orderRequests") OrderRequest[] orderRequests,
            @Param("projectInfoName") String projectInfoName,
            @Param("projectInfoProgressoperations")Integer[] projectInfoProgressoperations
            );


    /**
     *
     * @param PUid 项目参与者id
     * @param array 项目进程数组
     * @param orderRequests 排序字段
     * @param searchCondition 搜索条件
     * @return
     */
    List<ProjectInfoEntityWithBLOBs> selectListByPiProgressPUid(
            @Param("PUid") int PUid,
            @Param("array") int []array,
            @Param("orderRequests") OrderRequest[] orderRequests,
            @Param("searchCondition")String searchCondition
            );

    /**
     * 查询项目全部信息
     * @param project_info_id 项目id
     * @return
     */
    ProjectInfoEntityWithBLOBs selectProjectInfoAll(int project_info_id);
    /**
     *修改项目进程和进程状态为空则不修改
     * @param projectInfoId 项目id
     * @param projectInfoProgress 项目进程(1等待选择机构 2等待选择组长 3等待选择专家组 4等待项目评审 5等待批复 6完成)
     * @param projectInfoProgressoperation 进程状态 1未邀请 2等待响应 3过期
     * @return
     */
    int updatePiProgressByPIid(@Param("projectInfoId") Integer projectInfoId,
                                        @Param("projectInfoProgress") Integer projectInfoProgress,
                                        @Param("projectInfoProgressoperation") Integer projectInfoProgressoperation);

    /**
     * 通过项目信息id改变项目审核开始时间和过期时间
     * @param projectInfoId 项目id
     * @param projectInfoAuditstartdate 项目审核开始时间
     * @param projectInfoExpiration 项目审核过期时间
     * @return
     */
    Integer updateProjectInfoDateByPIid(@Param("projectInfoId") Integer projectInfoId,
                                          @Param("projectInfoAuditstartdate") Long projectInfoAuditstartdate,
                                          @Param("projectInfoExpiration") Long projectInfoExpiration);

    /**
     * 通过项目审核信息id改变项目状态
     * @param projectAuditId 审核信息id
     * @param projectInfoState 改变的项目状态
     * @return
     */
    Integer updateProjectInfoStateByPAid(@Param("projectAuditId") Integer projectAuditId,
                                   @Param("projectInfoState") Integer projectInfoState);

    /**
     * 通过项目id修改项目状态
     * @param projectInfoId
     * @param projectInfoState
     * @return
     */
    Integer updateProjectInfoStateByPIid(@Param("projectInfoId") Integer projectInfoId,
                                         @Param("projectInfoState") Integer projectInfoState);

    /**
     * 通过项目id批量修改项目进程
     * @param projectInfoIds
     * @param projectInfoProgress
     * @return
     */
    Integer updateProjectInfoStateByPIidBatch(@Param("projectInfoIds") Integer[] projectInfoIds,
                                              @Param("projectInfoProgress") Integer projectInfoProgress);
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

    /**
     * 查询待评审且已超过工作时间的项目id
     * @return
     */
    Integer[] selectPidToReviewExpiration();
    /**
     * 查询所有项目信息列表
     * @param orderRequests 排序字段
     * @return
     */
    List<Map> selectPiAll(@Param("orderRequests") OrderRequest[] orderRequests,@Param("conditionSearch")String conditionSearch);


}