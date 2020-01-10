package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.user.UserInfoLoginEntity;
import com.example.service.ProjectAuditService;
import com.example.service.vice.LoginVice;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ProjectAuditController")
public class ProjectAuditController {
    @Autowired
    ProjectAuditService projectAuditService;
    @Autowired
    private LoginVice loginVice;
    /**
     * 用户查询所属用户项目审核信息列表
     *
     * @param httpSession
     * @param
     * @return
     */
    @PostMapping("findProjectAuditListByUser_page")
    public VisitConsequenceParent findProjectAuditListByUser_page(HttpSession httpSession,
                                                                  @RequestBody PageOderRequestMap params) {
        VisitConsequenceParent vcp = new VisitConsequencePage();
        UserInfoLoginEntity uie = null;
        Integer auditState = null;//审核信息的状态 1待操作，2已评审
        Map param = params.getParam();//请求条件参数
        PageOderRequest pageOderRequest = params;//请求分页参数
        if (param.containsKey("auditState") && param.get("auditState") != null && !param.get("auditState").equals("null")) {
            auditState = (Integer) param.get("auditState");
        }
        Boolean isExpiration = true;//是否包含过期数据，默认包含
        if (param.containsKey("isExpiration") && param.get("isExpiration") != null && !param.get("isExpiration").equals("null")) {
            String s = String.valueOf(param.get("isExpiration"));
            isExpiration = Boolean.valueOf(s);
        }
        try {
            uie = loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            e.printStackTrace();
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        List<Map> listM = projectAuditService.findProjectAuditListByUser(Integer.valueOf(uie.getUser_id()), auditState, isExpiration, pageOderRequest);
        PageInfo a = new PageInfo<Map>(listM);//分页信息
        //将分页信息封装到统一的对象
        vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    /**
     * 审核项目
     * @param session
     * @param param
     * @return
     */
    @RequestMapping("auditProject")
    public VisitConsequenceParent auditProject(HttpSession session, @RequestBody Map param) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        Integer projectAuditId = null;//审核信息id
        String auditContent = "";//审核内容
        UserInfoLoginEntity uie = null;//用户登录信息
        try {
            uie = loginVice.getLoginInfo(session);
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            e.printStackTrace();
            return vcp;
        }
        projectAuditId = Integer.parseInt(String.valueOf(param.get("projectAuditId")));
        auditContent = String.valueOf(param.get("auditContent"));
        if (param.containsKey("projectInfoState") &&
                param.get("projectInfoState") != null &&
                !param.equals("null")) {//判断是否需要更改项目状态
            Integer projectInfoState;//项目状态
            projectInfoState = Integer.parseInt(String.valueOf(param.get("projectInfoState")));
            projectAuditService.auditProject(projectAuditId, Integer.valueOf(uie.getUser_id()),
                    Integer.valueOf(uie.getUser_role()), auditContent,projectInfoState);
        } else {
            projectAuditService.auditProject(projectAuditId, Integer.valueOf(uie.getUser_id()),
                    Integer.valueOf(uie.getUser_role()), auditContent);
        }
        return vcp;
    }

    /**
     * 能源局审核项目
     * @param  param auditUserId 评审人id auditUserRole 评审人角色
     *      auditContent 评审内容 projectInfoState 评审状态 2通过 3不通过
     *     auditTime 评审时间   projectInfoId 评审的项目id
     */
    @PostMapping("auditProjectBureau")
    public VisitConsequenceParent auditProjectBureau(@RequestBody Map param, HttpServletRequest request){
        Integer auditUserId = null;//评审人id
        Integer auditUserRole= null;//评审人角色
        String auditContent= null;//评审内容
        Integer projectInfoState= null;//评审状态 2通过 3不通过
        Long auditTime= null;//评审时间
        Integer projectInfoId= null;//项目id
        VisitConsequenceParent vcp =new VisitConsequenceParentImpl();
        try {
            UserInfoLoginEntity uie=loginVice.getLoginInfo(request.getSession());
            auditUserId= Integer.valueOf(uie.getUser_id());
            auditUserRole= Integer.valueOf(uie.getUser_role());
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            return vcp;
        }
        auditContent= String.valueOf(param.get("auditContent"));
        projectInfoState=Integer.valueOf(String.valueOf(param.get("projectInfoState")));
        projectInfoId=Integer.valueOf(String.valueOf(param.get("projectInfoId")));
        if(!param.containsKey("auditTime")||param.get("auditTime")==null||param.get("auditTime").equals(0)||param.get("auditTime").equals(null)||param.get("auditTime").equals("")||param.get("auditTime").equals(" ")){//如果前端没有传入这里设置默认值
            auditTime=new Date().getTime();
        }else{
            auditTime=Long.valueOf(String.valueOf(param.get("auditTime")));
        }
        if(!param.containsKey("auditContent")||param.get("auditContent")==null||param.get("auditContent").equals(null)){//如果前端没有传入这里设置默认值
            auditContent="未填写内容";
        }else{
            auditContent=String.valueOf(param.get("auditContent"));
        }
        projectAuditService.auditProjectBureau(auditUserId,auditUserRole,auditContent,projectInfoState,auditTime,projectInfoId);
        return vcp;
    }

    /**
     * 查询专家对项目的评审信息
     * @param param projectInfoId 项目id
     * @return
     */
    @RequestMapping("findEaListExper")
    public VisitConsequenceParent findEaListExper( @RequestBody Map param) {
        Integer projectInfoId= Integer.valueOf(String.valueOf(param.get("projectInfoId")));
        List<Map>listp=projectAuditService.findEaListByPiidlAtAur(projectInfoId,2,1);
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        vcp.setObject(listp);
        return vcp;
    }

}
