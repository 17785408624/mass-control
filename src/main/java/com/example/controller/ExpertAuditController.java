package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.ExpertAudit;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.ExpertEvaluationMap;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.user.UserInfoLoginEntity;
import com.example.service.ExpertAuditService;
import com.example.service.vice.LoginVice;
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
@RequestMapping("ExpertAuditController")
public class ExpertAuditController {
    @Autowired
    ExpertAuditService expertAuditService;
    @Autowired
    private LoginVice loginVice;
    /**
     * 查询用户所属的专家评测信息详细记录列表
     *
     * @param session
     * @param pageOderRequestMap
     * @return
     */
    @PostMapping("findExpertAuditList")
    public VisitConsequenceParent findExpertAuditList(HttpSession session, @RequestBody PageOderRequestMap pageOderRequestMap) {
        VisitConsequenceParent vcp = new VisitConsequencePage();
        Map param = pageOderRequestMap.getParam();//用户查询所带条件参数
        Integer expertAuditInfoId = null;//专家评测信息id
        Integer auditState = null;
        Integer auditUserId = null;
        try {
            auditUserId = Integer.valueOf(loginVice.getLoginInfo(session).getUser_id());
        } catch (LoginException e) {
            e.printStackTrace();
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            return vcp;
        }
        PageOderRequest por = pageOderRequestMap;//分页排序信息
        if (pageOderRequestMap.getParam() != null) {
            if (param.containsKey("expertAuditInfoId") &&
                    param.get("expertAuditInfoId") != null &&
                    !param.get("expertAuditInfoId").equals("null")) {
                expertAuditInfoId = Integer.parseInt(String.valueOf(param.get("expertAuditInfoId")));
            }
            if (param.containsKey("auditState") &&
                    param.get("auditState") != null &&
                    !param.get("auditState").equals("null")) {
                auditState = Integer.parseInt(String.valueOf(param.get("auditState")));
            }

        }
        if (por.getPageRequest() != null) {//是否带有分页信息
            //查询被评测的专家信息、项目关联的专家评测数据列表
            List<Map> listM = expertAuditService.findExpertAuditList(expertAuditInfoId, auditState, por);
            PageInfo a = new PageInfo<Map>(listM);//分页信息
            vcp = PageUtils.getVisitConsequencePage(a);//将分页信息结果封装成返回结果
        } else {
            //查询被评测的专家信息、项目关联的专家评测数据列表
            List<Map> listM = expertAuditService.findExpertAuditList(expertAuditInfoId, auditState);
            vcp.setObject(listM);
        }
        return vcp;
    }

    /**
     * 评测专家信息
     * @param expertEvaluationMap
     * @param session
     * @return
     */
    @PostMapping("expertEvaluation")
    public VisitConsequenceParent expertEvaluation(@RequestBody ExpertEvaluationMap expertEvaluationMap, HttpSession session){
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        UserInfoLoginEntity uie=null;
        try {
            uie=loginVice.getLoginInfo(session);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        ExpertAudit[]expertAudits;//评测的专家信息组
        Integer userId = null;//用户id
        Integer userRole = null;//用户角色
        Integer projectInfoId = null;//评测信息关联项目id
        expertAudits=expertEvaluationMap.getExpertAudits();
        projectInfoId=expertEvaluationMap.getProjectInfoId();
        userRole= Integer.valueOf(uie.getUser_role());
        userId= Integer.valueOf(uie.getUser_id());
        //评测专家信息
        expertAuditService.expertEvaluation( expertAudits,projectInfoId, userId, userRole);
        return vcp;
    }

}
