package com.example.mapper;

import com.example.entity.ExpertAuditInfo;
import com.example.entity.ExpertAuditInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertAuditInfoMapper {
    /**
     *
     *  2019-09-18
     */
    int deleteByExample(ExpertAuditInfoExample example);

    /**
     *
     *  2019-09-18
     */
    int insert(ExpertAuditInfo record);

    /**
     *
     *  2019-09-18
     */
    int insertSelective(ExpertAuditInfo record);

    /**
     *
     *  2019-09-18
     */
    List<ExpertAuditInfo> selectByExample(ExpertAuditInfoExample example);

    /**
     *
     *  2019-09-18
     */
    int updateByExampleSelective(@Param("record") ExpertAuditInfo record, @Param("example") ExpertAuditInfoExample example);

    /**
     *
     *  2019-09-18
     */
    int updateByExample(@Param("record") ExpertAuditInfo record, @Param("example") ExpertAuditInfoExample example);

    /**
     * 通过专家评测信息关联的项目id改变未审核的人数
     * @param projectInfoId 专家评测信息管理的项目信息id
     * @param isadd 是否为添加 true为添加 false为减少
     * @param updateNum 改变的数量
     * @return
     */
    Integer updateNotAuditNumByPid(Integer projectInfoId,Boolean isadd ,Integer updateNum);

    /**
     * 查询专家评测信息 如果条件为空则视为不设置查询条件
     * @param auditUserId 审核人id
     * @param project_info_progress 项目进程
     * @param isContainExpiration 是否包含工作时间已过期的数据 true为包含
     * @param isContainAuditnumZero 是否包含未审核人数为0的数据
     * @return
     */
    List<Map>selectEaiList(@Param("auditUserId") Integer auditUserId,
                           @Param("project_info_progress")Integer project_info_progress,
                           @Param("isContainExpiration") Boolean isContainExpiration,
                           @Param("isContainAuditnumZero") Boolean isContainAuditnumZero
                          );

}