package com.example.service.impl;

import com.example.entity.ExpertAudit;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ExpertAuditInfoMapper;
import com.example.mapper.ExpertAuditMapper;
import com.example.service.ExpertAuditService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertAuditServiceImpl implements ExpertAuditService {
    @Autowired
    ExpertAuditMapper expertAuditMapperr;
    @Autowired
    ExpertAuditInfoMapper expertAuditInfoMapper;

    //查询被评测的专家信息、项目关联的专家评测数据列表
    @Override
    public List<Map> findExpertAuditList(Integer expertAuditInfoId, Integer auditState) {

        return expertAuditMapperr.selectExpertAuditListByPid(expertAuditInfoId,auditState);
    }
    //分页查询被评测的专家信息、项目关联的专家评测数据列表
    @Override
    public List<Map> findExpertAuditList(Integer expertAuditInfoId, Integer auditState,  PageOderRequest por) {
        PageRequest pr=por.getPageRequest();
        PageHelper.startPage(pr.getPageNum(), pr.getPageSize());//调用分页
        List<Map>listM=expertAuditMapperr.selectExpertAuditListByPid(expertAuditInfoId,auditState);
        return listM;
    }
    //评审专家
    @Override
    public Integer expertEvaluation(ExpertAudit[] expertAudits,Integer projectInfoId,Integer auditUserId,Integer auditUserRole) {
        ExpertAudit commonUpdate =new ExpertAudit();//批量修改数据的公共参数
        Long nowDate=new Date().getTime();
        commonUpdate.setAuditDatetime(nowDate);//审核时间
        commonUpdate.setAuditState(2);//状态 已审核
        commonUpdate.setAuditUserId(auditUserId);//审核人id
        commonUpdate.setAuditUserRole(auditUserRole);//审核人角色
        Integer updateNum=expertAuditMapperr.updateEaByEiDBatch(expertAudits,commonUpdate);//批量修改数据
        expertAuditInfoMapper.updateNotAuditNumByPid(projectInfoId,false,expertAudits.length);
        return updateNum;
    }
}
