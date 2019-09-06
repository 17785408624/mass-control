package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.ProjectauditOrganizationInvite;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.ProjectauditOrganizationInviteService;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ProjectauditOInvite")
public class ProjectauditOInviteController {
    @Autowired
    ProjectauditOrganizationInviteService projectauditOrganizationInviteService;

    /**
     * 添加第三方机构项目审核邀请
     * @param projectauditOrganizationInvite
     * @param httpSession
     * @return
     */
    @PostMapping("addProjectauditInvite")
    public VisitConsequenceParent addProjectauditInvite(
            @RequestBody ProjectauditOrganizationInvite projectauditOrganizationInvite,
            HttpSession httpSession) {
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        UserInfoLoginSession us;
        try {
            us=new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            e.printStackTrace();
            return  vcp;
        }
        projectauditOrganizationInviteService.addProjectauditInvite(us.getUser_id(),projectauditOrganizationInvite);
        vcp.setMessage("请求成功");
        vcp.setState(0);
        return  vcp;
    }

    /**
     * 分页查询用户项目审核邀请 （第三方机构） 包含过期邀请
     * @param pageRequest 分页信息
     * @return
     */
    @PostMapping("findUserProjectauditOIListPage")
    public VisitConsequenceParent findUserProjectauditOIListPage(HttpSession httpSession,@RequestBody PageRequest pageRequest){
        VisitConsequenceParent vc=new VisitConsequencePage();
        UserInfoLoginSession us;
        try {
            us=new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        List<ProjectauditOrganizationInvite> listP=projectauditOrganizationInviteService.findProjectauditOInviteListByUserIdPage(us.getUser_id(),true,pageRequest);
        PageInfo a=new PageInfo<ProjectauditOrganizationInvite>(listP);//分页信息
        vc=PageUtils.getVisitConsequencePage(a);//将分页信息结果封装成返回结果
        return vc;
    }

    /**
     * 分页查询用户项目审核邀请 （第三方机构）不包含过期邀请
     * @param pageRequest 分页信息
     * @return
     */
    @PostMapping("findUserProjectauditOIListPageENot")
    public VisitConsequenceParent findUserProjectauditOIListPageENot(HttpSession httpSession,@RequestBody PageRequest pageRequest){
        VisitConsequenceParent vc=new VisitConsequencePage();
        UserInfoLoginSession us;
        try {
            us=new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        List<ProjectauditOrganizationInvite> listP=projectauditOrganizationInviteService.findProjectauditOInviteListByUserIdPage(us.getUser_id(),false,pageRequest);
        PageInfo a=new PageInfo<ProjectauditOrganizationInvite>(listP);//分页信息
        vc=PageUtils.getVisitConsequencePage(a);//将分页信息结果封装成返回结果
        return vc;
    }

    /**
     * 操作项目审核邀请(第三方机构)
     * @param httpSession
     * @param pamar
     * @return
     */
    @PostMapping("operationUserProjectauditOInvite")
    public VisitConsequenceParent operationUserProjectauditOInvite(HttpSession httpSession,@RequestBody Map pamar){
        VisitConsequenceParent vc=new VisitConsequenceParentImpl();
        UserInfoLoginSession us;//用户登录信息
        Integer projectInfoId;//审核项目id
        Integer projectauditOrganizationInviteId;//邀请信息数据id
        Integer inviteEdituserId;//修改人id
        Integer inviteState;//修改状态
        Integer userRole;//修改人角色
        projectInfoId=Integer.parseInt(String.valueOf(pamar.get("projectInfoId")));
        projectauditOrganizationInviteId=Integer.parseInt(String.valueOf(pamar.get("projectauditOrganizationInviteId")));
        inviteState=Integer.parseInt(String.valueOf(pamar.get("inviteState")));
        try {
            us=new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        userRole=us.getUser_role();//用户角色
        inviteEdituserId=us.getUser_id();//用户id
        boolean results;
        try {
            results=projectauditOrganizationInviteService.operationUserProjectauditOInvite(
                    projectInfoId,projectauditOrganizationInviteId,inviteEdituserId,inviteState,userRole);
        } catch (OperationProjectauditOInviteException e) {
            e.printStackTrace();
            vc.setMessage(e.getMessage());
            vc.setState(1);
            return vc;
        }
        return vc;
    }




}
