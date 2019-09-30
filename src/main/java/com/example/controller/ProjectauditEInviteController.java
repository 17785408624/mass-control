package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationProjectauditOInviteException;
import com.example.entity.ProjectauditExpertInvite;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.InsertPEinviteBatch;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.ProjectauditExpertInviteService;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 项目审核邀请 (专家)
 */
@RestController
@RequestMapping("ProjectauditEInvite")
public class ProjectauditEInviteController {
    @Autowired
    private ProjectauditExpertInviteService projectauditExpertInviteService;

    /**
     * 添加项目审核邀请 (专家组组长)
     * @param projectauditExpertInvite
     * @param httpSession
     * @return
     */
    @PostMapping("addPEInviteLeader")
    public VisitConsequenceParent addPEInviteLeader(
            @RequestBody ProjectauditExpertInvite projectauditExpertInvite,
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
        projectauditExpertInvite.setInviteType(1);//邀请类型(1组长 2组员)
        projectauditExpertInviteService.addPEInvite(projectauditExpertInvite,us.getUser_id());
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(1);
        return  vcp;
    }
    /**
     * 分页查询用户项目审核邀请 （专家）不包含过期邀请
     * @param pageOderRequest 分页信息
     * @return
     */
    @PostMapping("findPEInviteListPageENot")
    public VisitConsequenceParent findPEInviteListPageENot(HttpSession httpSession,@RequestBody PageOderRequest pageOderRequest){
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
        //查询项目审核邀请信息(专家)
        List<ProjectauditExpertInvite> listP=projectauditExpertInviteService.findPEInviteListByUserIdPage(us.getUser_id(),false,pageOderRequest.getPageRequest());
        PageInfo a=new PageInfo<ProjectauditExpertInvite>(listP);//分页信息
        vc= PageUtils.getVisitConsequencePage(a);//将分页信息结果封装成返回结果
        return vc;
    }
    /**
     * 分页查询用户项目审核邀请 （专家） 包含过期邀请
     * @param pageOderRequest 分页信息
     * @return
     */
    @PostMapping("findPEInviteListPage")
    public VisitConsequenceParent findPEInviteListPage(HttpSession httpSession,@RequestBody PageOderRequest pageOderRequest){
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
        List<ProjectauditExpertInvite> listP=projectauditExpertInviteService.findPEInviteListByUserIdPage(us.getUser_id(),true,pageOderRequest.getPageRequest());
        PageInfo a=new PageInfo<ProjectauditExpertInvite>(listP);//分页信息
        vc= PageUtils.getVisitConsequencePage(a);//将分页信息结果封装成返回结果
        return vc;
    }
    /**
     * 操作项目审核邀请(专家)
     * @param httpSession
     * @param pamar
     * @return
     */
    @PostMapping("operationUserPEInvite")
    public VisitConsequenceParent operationUserPEInvite(HttpSession httpSession,@RequestBody Map pamar){
        VisitConsequenceParent vc=new VisitConsequenceParentImpl();
        UserInfoLoginSession us;//用户登录信息
        Integer projectauditExpertInviteId;//邀请信息数据id
        Integer inviteType;//邀请类型(1组长 2组员)
        Integer inviteState;//修改状态 (1等待操作 2接受 3拒绝 4取消邀请)

        Integer inviteEdituserId;//修改人id
        Integer userRole;//修改人角色
        projectauditExpertInviteId=Integer.parseInt(String.valueOf(pamar.get("projectauditExpertInviteId")));
        inviteState=Integer.parseInt(String.valueOf(pamar.get("inviteState")));
        inviteType=Integer.parseInt(String.valueOf(pamar.get("inviteType")));
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
            results=projectauditExpertInviteService.operationUserPEInvite(projectauditExpertInviteId,inviteEdituserId,inviteState,userRole,inviteType);
        } catch (OperationProjectauditOInviteException e) {
            e.printStackTrace();
            vc.setMessage(e.getMessage());
            vc.setState(1);
            return vc;
        }
        return vc;
    }

    /**
     *  添加项目审核邀请(专家组组员)
     * @param httpSession
     * @param insertPEinviteBatch
     * @return
     */
    @PostMapping("addPEInvite")
    public VisitConsequenceParent addPEInvite(HttpSession httpSession,
                                              @RequestBody InsertPEinviteBatch insertPEinviteBatch){
        VisitConsequenceParent vc=new VisitConsequenceParentImpl();
        UserInfoLoginSession us;
        try {
            us=new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vc.setMessage(e.getMessage());
            vc.setState(1);
            e.printStackTrace();
            return  vc;
        }
        Integer i=projectauditExpertInviteService.addPEInvite(insertPEinviteBatch,us.getUser_id());
        vc.setObject(i);
        return vc;
    }

}
