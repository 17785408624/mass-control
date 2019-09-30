package com.example.service.impl;

import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ExpertAuditInfoMapper;
import com.example.service.ExpertAuditInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertAuditInfoServiceImpl implements ExpertAuditInfoService {
    @Autowired
    ExpertAuditInfoMapper expertAuditInfoMapper;
    //分页查询专家评测信息
    @Override
    public List<Map> findtEaiList_page(Integer AuditUid,
                                       Integer project_info_progress,
                                       Boolean isContainExpiration,
                                       Boolean isContainAuditnumZero,
                                       PageOderRequest pageOderRequest) {
        PageRequest pr=pageOderRequest.getPageRequest();
        Integer pageNum=pr.getPageNum();
        Integer pageSize=pr.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        List<Map> listM = expertAuditInfoMapper.selectEaiList(AuditUid,
                4,
                isContainExpiration,
                isContainAuditnumZero);
        return listM;
    }
}
