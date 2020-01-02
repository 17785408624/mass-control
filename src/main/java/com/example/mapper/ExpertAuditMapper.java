package com.example.mapper;

import com.example.entity.ExpertAudit;
import com.example.entity.ExpertAuditExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertAuditMapper {
    /**
     * 2019-09-18
     */
    int deleteByExample(ExpertAuditExample example);

    /**
     * 2019-09-18
     */
    int insert(ExpertAudit record);

    /**
     * 2019-09-18
     */
    int insertSelective(ExpertAudit record);

    /**
     * 2019-09-18
     */
    List<ExpertAudit> selectByExample(ExpertAuditExample example);

    /**
     * 2019-09-18
     */
    int updateByExampleSelective(@Param("record") ExpertAudit record, @Param("example") ExpertAuditExample example);

    /**
     * 2019-09-18
     */
    int updateByExample(@Param("record") ExpertAudit record, @Param("example") ExpertAuditExample example);

    /**
     * 查询专家评审信息列表
     *
     * @param expertAuditInfoId 专家评测信息主键
     * @param auditState    评审状态(1待操作，2已评审)
     * @return
     */
    List<Map> selectExpertAuditListByPid(@Param("expertAuditInfoId") Integer expertAuditInfoId,
                                         @Param("auditState") Integer auditState);

    /**
     * 批量修改数据
     * @param expertAudits 修改的数据集合
     * @param commonUpdate 公共修改部分
     * @return
     */
    Integer updateEaByEiDBatch(@Param("expertAudits") ExpertAudit[] expertAudits,@Param("commonUpdate") ExpertAudit commonUpdate);



}