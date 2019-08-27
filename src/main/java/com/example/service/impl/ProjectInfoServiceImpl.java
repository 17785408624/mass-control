package com.example.service.impl;

import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectInfoEntityMapper;
import com.example.service.ProjectInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    //添加项目信息
    @Override
    public int addProjectInfo(int user_id,ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs) {
        projectInfoEntityWithBLOBs.setProjectInfoAddtime(new Date().getTime());
        projectInfoEntityWithBLOBs.setProjectInfoAdduserid(user_id);
        return projectInfoEntityMapper.inserBatchAC(projectInfoEntityWithBLOBs);
    }

    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoChooseByProgressOE() {
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{1,2},null);
    }

    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoChooseByProgressOE(PageOderRequest pageOderRequest) {
        PageRequest pageRequest=pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{1,2},pageOderRequest.getOrderRequests());

    }
}
