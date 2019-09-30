package com.example.service;

import com.example.entity.requstparam.PageOderRequest;

import java.util.List;
import java.util.Map;

/**
 * 专家评测信息业务类
 */
public interface ExpertAuditInfoService {
    /**
     *  分页查询专家评测信息 如果条件为空则视为不设置查询条件
     * @param AuditUid 审核人id
     * @param project_info_progress 项目进程
     * @param isContainExpiration 是否包含工作时间已过期的数据 true为包含
     * @param isContainAuditnumZero 是否包含未审核人数为0的数据
     * @param pageOderRequest 分页排序信息
     * @return
     */
    List<Map> findtEaiList_page(Integer AuditUid,
                                Integer project_info_progress,
                                Boolean isContainExpiration,
                                Boolean isContainAuditnumZero,
                                PageOderRequest pageOderRequest);
}
