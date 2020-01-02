package com.example.mapper;

import com.example.entity.ProjectAudit;
import com.example.entity.ProjectAuditExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectAuditMapper {
    /**
     *
     *  2019-09-15
     */
    int insert(ProjectAudit record);

    /**
     *
     *  2019-09-15
     */
    int insertSelective(ProjectAudit record);

    /**
     *
     *  2019-09-15
     */
    int updateByExampleSelective(@Param("record") ProjectAudit record, @Param("example") ProjectAuditExample example);

    /**
     *
     *  2019-09-15
     */
    int updateByExample(@Param("record") ProjectAudit record, @Param("example") ProjectAuditExample example);

    /**
     * 查询项目审核信息列表 条件为空则视为不设置查询条件
     * @param auditUserId 评审人id
     * @param auditState  评审状态(1待操作，2已评审)
     * @param isExpiration 是否包含过期信息 true包含过期信息
     * @return
     */
    List<Map> selectProjectAuditList(@Param("auditUserId") Integer auditUserId,
                                     @Param("auditState") Integer auditState,
                                     @Param("isExpiration") Boolean isExpiration
                                              );

    /**
     * 通过审核信息id修改审核信息
     * @param ProjectAuditId
     * @return
     */
    //Integer updateProjectAuditByPAuditId(Integer ProjectAuditId);

    /**
     * 通过项目审核信息id查询项目id
     * @param Paid 项目审核信息id
     * @return
     */
    Integer selectPiIdByPaId(Integer Paid);
    /**
     * 查询专家评测信息列表
     * @param projectInfoId 项目id
     * @param auditState 评测状态
     * @param auditUserRole 评审人角色
     * @return
     */
    List<Map>selectEaListByPiidlAtAur(@Param("projectInfoId") Integer projectInfoId,
                                   @Param("auditState") Integer auditState,
                                   @Param("auditUserRole") Integer auditUserRole);

    /**
     * 通过项目ip和项目评审人id查询项目评审信息
     * @param projectInfoId 项目id
     * @param auditUserIds 项目评审人id
     * @return
     */
    List<ProjectAudit> selectPaByPiidAuid(
            @Param("projectInfoId") Integer projectInfoId,
            @Param("auditUserIds") int[] auditUserIds
    );

}