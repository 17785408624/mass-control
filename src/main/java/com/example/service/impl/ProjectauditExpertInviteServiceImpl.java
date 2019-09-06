package com.example.service.impl;

import com.example.config.ServiceConfig;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.requstparam.PageRequest;
import com.example.mapper.ProjectauditExpertInviteMapper;
import com.example.service.ProjectauditExpertInviteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Service
public class ProjectauditExpertInviteServiceImpl implements ProjectauditExpertInviteService {
    @Autowired
    ProjectauditExpertInviteMapper projectauditExpertInviteMapper;
    //添加项目审核邀请
    @Override
    public int addPEInvite(ProjectauditExpertInvite pei, Integer inviteAdduserId) {
        Long nowDate=new Date().getTime();
        pei.setInviteAddtime(nowDate);//添加时间
        pei.setInviteExpiration(
                ServiceConfig.getAUDIT_ORGANIZATION_INVITE_EXPIRATION()+nowDate);//过期时间
        pei.setInviteAdduserId(inviteAdduserId);//添加人id
        projectauditExpertInviteMapper.insertSelective(pei);//添加项目审核邀请信息
        return pei.getProjectauditExpertInviteId();
    }
    //分页查询用户项目审核邀请信息列表(专家)
    @Override
    public List<ProjectauditExpertInvite> findPEInviteListByUserIdPage(Integer inviteUserId, boolean inviteExpiration, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);//调用分页
        if(inviteExpiration){
            return  projectauditExpertInviteMapper.selectPEInviteListByinviteUserId(inviteUserId);
        }else{
            return projectauditExpertInviteMapper.selectPEInviteListByinviteUserIdExpirationNot(inviteUserId,null);
        }
    }
}
