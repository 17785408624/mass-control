package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.ProjectAuditService;
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

@RestController
@RequestMapping("ProjectAuditController")
public class ProjectAuditController {
    @Autowired
    ProjectAuditService projectAuditService;

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
        UserInfoLoginSession uils = null;
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
            uils = new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            e.printStackTrace();
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        List<Map> listM = projectAuditService.findProjectAuditListByUser(uils.getUser_id(), auditState, isExpiration, pageOderRequest);
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
        UserInfoLoginSession uils = null;//用户登录信息
        try {
            uils = new UserInfoLoginSession(session);
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
            projectAuditService.auditProject(projectAuditId, uils.getUser_id(), uils.getUser_role(), auditContent,projectInfoState);
        } else {
            projectAuditService.auditProject(projectAuditId, uils.getUser_id(), uils.getUser_role(), auditContent);
        }
        return vcp;
    }


}
