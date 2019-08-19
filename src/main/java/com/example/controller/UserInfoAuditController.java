package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.UserInfoAuditEntity;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserInfoAudit")
public class UserInfoAuditController {
    @Autowired
    UserInfoService userInfoService;

//    public VisitConsequenceParent findNotAuditUserInfoAuditOrganization(@RequestBody Map param){
//        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
//        vcp.setMessage("请求成功");
//        vcp.setState(0);
//        int user_info_audit_state= (int) param.get("user_info_audit_state");
//        int user_info_audit_type= (int) param.get("user_info_audit_type");
//        vcp.setObject(
//                userInfoAuditOrganizationService.
//                        findUserInfoAuditOrganization(,));
//
//
//        return null;
//
//    }

    /**
     * 查询初次提交申请未审核的第三方机构信息
     *
     * @param
     * @return
     */
    @RequestMapping("findFirstAuditOrganizationNone")
    public VisitConsequenceParent findFirstAuditOrganizationNone( ) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        int user_info_audit_state = 1;
        int user_info_audit_type = 1;
        vcp.setObject(
                userInfoService.
                        findUserInfoAuditOrganization(user_info_audit_state,
                                user_info_audit_type));


        return vcp;

    }

    /**
     * 查询第三方机构完整信息
     *
     * @param param
     * @return
     */
    @RequestMapping("findUserInfoAuditOrganizationFull")
    public VisitConsequenceParent findUserInfoAuditOrganizationFull(@RequestBody Map param) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        int organization_info_id = (int) param.get("info_id");
        OrganizationAuditResults oar = userInfoService.findOrganizationInfoFull(organization_info_id);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(oar);
        return vcp;
    }

    /**
     * 查询用户已提交申请的审核信息
     *
     * @param param
     * @param httpSession
     * @return
     */
    @RequestMapping("findUserInfoAuditSubmit")
    public VisitConsequenceParent findUserInfoAuditSubmit(HttpSession httpSession) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uils = null;
        try {
            uils = new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        List<UserInfoAuditEntity> list = userInfoService.
                selectUserInfoAuditByUid(uils.getUser_id());//查询用户提交的审核信息
        Map map = new HashMap();

        map.put("userInfoAuditList", list);
        vcp.setObject(map);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }

    /**
     * 查询用户是否已提交过审核信息
     *
     * @param
     * @return 已提交返回true 未提交返回false
     */
    @RequestMapping("findUserInfoAuditSubmitState")
    public VisitConsequenceParent findUserInfoAuditSubmitState(HttpSession httpSession) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uils = null;
        try {
            uils = new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }

        Map map = new HashMap();
        boolean userInfoAuditSubmit;//用户是否提交过审核申请
        userInfoAuditSubmit = userInfoService.
                findUserInfoAuditSubmitState(uils.getUser_id());//查询用户是否提交过资料审核信息
        if (userInfoAuditSubmit) {
            UserInfoAuditEntity uie = userInfoService.
                    findUserInfoAuditSubmitFirst(uils.getUser_id());//查询用户提交的第一条审核信息
            int user_info_audit_state = uie.getUser_info_audit_state();//审核状态 1未审核 2拒绝 3 通过
            map.put("user_info_audit_state", user_info_audit_state);
        }

        map.put("userInfoAuditSubmit", userInfoAuditSubmit
        );//查询用户是否提交过资料审核信息
        vcp.setObject(map);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }

    /**
     * 查询用户已提交申请的第一条审核信息
     *
     * @param
     * @param httpSession
     * @return
     */
    @RequestMapping("findUserInfoAuditSubmitFirst")
    public VisitConsequenceParent findUserInfoAuditSubmitFirst(HttpSession httpSession) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uils = null;
        try {
            uils = new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        UserInfoAuditEntity uie = userInfoService.
                findUserInfoAuditSubmitFirst(uils.getUser_id());//查询用户提交的第一条审核信息
        Map map = new HashMap();

        map.put("userInfoAuditList", uie);
        vcp.setObject(map);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }

    /**
     * 查询初次提交申请未审核的专家信息列表
     *
     * @param
     * @return
     */
    @GetMapping("findFirstAuditExpertInfoNone")
    public VisitConsequenceParent findFirstAuditExpertInfoNone() {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        int user_info_audit_state = 1;
        int user_info_audit_type = 1;
        List<Map<String,Object>> uiae=  userInfoService.
                findUserInfoAuditExpert(user_info_audit_state,
                        user_info_audit_type);
        vcp.setObject(uiae);
        return vcp;
    }

}