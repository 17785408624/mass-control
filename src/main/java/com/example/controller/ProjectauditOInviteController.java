package com.example.controller;

import com.example.common.exceptiondefine.AuditOperationServiceException;
import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectauditOrganizationInvite;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.user.UserInfoLoginEntity;
import com.example.service.ProjectauditOrganizationInviteService;
import com.example.service.vice.LoginVice;
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
    @Autowired
    private LoginVice loginVice;
    /**
     * 添加第三方机构项目审核邀请
     * @param
     * @param httpSession
     * @return
     */
    @PostMapping("addProjectauditInvite")
    public VisitConsequenceParent addProjectauditInvite(
            @RequestBody ProjectauditOrganizationInvite projectauditOrganizationInvite,
            HttpSession httpSession) {
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        UserInfoLoginEntity uie;
        try {
             uie=loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            e.printStackTrace();
            return  vcp;
        }
        try {
            projectauditOrganizationInviteService.addProjectauditInvite(Integer.valueOf(uie.getUser_id()),projectauditOrganizationInvite);
        } catch (AuditOperationServiceException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
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
        UserInfoLoginEntity uie;
        try {
            uie=loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        List<Map> listP=projectauditOrganizationInviteService.findProjectauditOInviteListByUserIdPage(
                Integer.valueOf(uie.getUser_id()),true,pageRequest);
        PageInfo a=new PageInfo<Map>(listP);//分页信息
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
        UserInfoLoginEntity uie;
        try {
            uie=loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        List<Map> listP=projectauditOrganizationInviteService.findProjectauditOInviteListByUserIdPage(
                Integer.valueOf(uie.getUser_id()),false,pageRequest);
        PageInfo a=new PageInfo<Map>(listP);//分页信息
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
        UserInfoLoginEntity uie;//用户登录信息
        Integer projectInfoId;//审核项目id
        Integer projectauditOrganizationInviteId;//邀请信息数据id
        Integer inviteEdituserId;//修改人id
        Integer inviteState;//修改状态
        Integer userRole;//修改人角色
        projectauditOrganizationInviteId=Integer.parseInt(String.valueOf(pamar.get("projectauditOrganizationInviteId")));
        inviteState=Integer.parseInt(String.valueOf(pamar.get("inviteState")));
        try {
            uie=loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        userRole= Integer.valueOf(uie.getUser_role());//用户角色
        inviteEdituserId= Integer.valueOf(uie.getUser_id());//用户id
        boolean results;
        try {
            results=projectauditOrganizationInviteService.operationUserProjectauditOInvite(
                    projectauditOrganizationInviteId,inviteEdituserId,inviteState,userRole);
        } catch (OperationProjectauditOInviteException e) {
            e.printStackTrace();
            vc.setMessage(e.getMessage());
            vc.setState(1);
            return vc;
        }
        return vc;
    }




}
