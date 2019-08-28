package com.example.service.impl;

import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectInfoEntityMapper;
import com.example.mapper.UserMapper;
import com.example.service.ProjectInfoService;
import com.github.pagehelper.PageHelper;
import com.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(rollbackFor=Exception.class)
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    @Autowired
    UserMapper userMapper;
    //添加项目信息
    @Override
    public int addProjectInfo(int user_id,ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs) {
        projectInfoEntityWithBLOBs.setProjectInfoAddtime(new Date().getTime());
        projectInfoEntityWithBLOBs.setProjectInfoAdduserid(user_id);
        return projectInfoEntityMapper.inserBatchAC(projectInfoEntityWithBLOBs);
    }
    //查询项目信息列表
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoChooseByProgressOE() {
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{1,2},null);
    }
    //分页查询项目信息列表
    @Override
    public List<ProjectInfoEntityWithBLOBs> findProjectInfoChooseByProgressOE(PageOderRequest pageOderRequest) {
        PageRequest pageRequest=pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return projectInfoEntityMapper.selectListByPiProgress(new int[]{1,2}, EncryptUtil.decodeOrderField(pageOderRequest.getOrderRequests()));

    }

    //通过项目id查询项目全部信息
    @Override
    public ProjectInfoEntityWithBLOBs findProjectInfoFullByPid(Integer project_info_id) {
        ProjectInfoEntityWithBLOBs pe=projectInfoEntityMapper.
                selectProjectInfoAll(project_info_id);
        return pe;
    }
    //抽取审核项目的第三方机构
    @Override
    public Map extractionProjectOrganization(Integer[] excludeUids) {
        Integer excludeNum=0;//回避单位的数量
        if(excludeUids!=null){
            excludeNum=excludeUids.length;
        }
        int userNum=userMapper.
                selectUserNum(2,2);//已审核的第三方机构数量
        userNum=userNum-excludeNum;
        /**
         * 获取机构总数，在随机获取一个一到机构总数的数字
         * 随机获取0-机构总数 不包含0 这里结果加1 random
         */
        int random= (int) ((Math.random()*userNum))+1;//随机选择机构
        Map map=userMapper.selectOrganizationInfoLimit(2,random-1,1,excludeUids).get(0);
        return map;
    }


}
