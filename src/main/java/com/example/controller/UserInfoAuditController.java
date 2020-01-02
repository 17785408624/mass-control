package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.resultsparam.ExpertInfoResults;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.UserEntity;
import com.example.entity.user.UserInfoAuditEntity;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.UserInfoService;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserInfoAudit")
public class UserInfoAuditController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserService userService;

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
     * @param
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
        boolean userInfoAuditSubmit=false;//用户是否提交过审核申请
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
     * 查询用户已提交的第一条审核信息
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

    /**
     * 审核用户初次提交的信息
     * @param map int info_id 信息id
     * int user_info_audit_state 审核的操作，状态 2拒绝 3通过
     * @return
     */
     @PostMapping("operationUserInfoAuditFirst")
    public VisitConsequenceParent operationUserInfoAuditFirst(
            @RequestBody Map map){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        int info_id= (int) map.get("info_id");
        int user_info_audit_state= (int) map.get("user_info_audit_state");
        userInfoService.operationUserInfoAuditFirst(info_id,user_info_audit_state);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }
    /**
     * 审核用户提交的更改信息
     * @param map int info_id 信息id
     * int user_info_audit_state 审核的操作，状态 2拒绝 3通过
     * @return
     */
    @PostMapping("operationUserInfoAudit")
    public VisitConsequenceParent operationUserInfoAudit(
            @RequestBody Map map){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        int info_id= (int) map.get("info_id");
        int user_info_audit_state= (int) map.get("user_info_audit_state");
        userInfoService.operationUserInfoAudit(info_id,user_info_audit_state);
        return vcp;
    }

    /**
     * 查询非初次未审核的专家信息列表
     *
     * @param
     * @return
     */
    @GetMapping("findAuditExpertInfoNone")
    public VisitConsequenceParent findAuditExpertInfoNone() {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        int user_info_audit_state = 1;
        int user_info_audit_type = 2;
        List<Map<String,Object>> uiae=  userInfoService.
                findUserInfoAuditExpert(user_info_audit_state,
                        user_info_audit_type);
        vcp.setObject(uiae);
        return vcp;
    }

    /**
     * 查询非初次提交申请未审核的第三方机构信息列表
     *
     * @param
     * @return
     */
    @RequestMapping("findAuditOrganizationNone")
    public VisitConsequenceParent findAuditOrganizationNone( ) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        int user_info_audit_state = 1;
        int user_info_audit_type = 2;
        vcp.setObject(
                userInfoService.
                        findUserInfoAuditOrganization(user_info_audit_state,
                                user_info_audit_type));


        return vcp;

    }

    /**
     * 查询专家完整信息
     *
     * @param param
     * @return
     */
    @RequestMapping("findUserInfoExpertInfoResultsFull")
    public VisitConsequenceParent findUserInfoExpertInfoResultsFull(@RequestBody Map param) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        int expert_info_id = Integer.parseInt(param.get("info_id").toString());
        //int expert_info_id = (int) param.get("info_id");
        ExpertInfoResults eir = userInfoService.findExpertInfoFull(expert_info_id);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(eir);
        return vcp;
    }

    /**
     * 查询用户最近提交的一条审核信息，如果是未审核用户当状态变为审核通过将用户信息重新登录
     *
     * @param
     * @param httpSession
     * @return
     */
    @GetMapping ("findUserInfoAuditRecentlyByUid")
    public VisitConsequenceParent findUserInfoAuditRecentlyByUid(HttpSession httpSession, HttpServletRequest request) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uils = null;
        try {
            uils = new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        UserInfoAuditEntity uia = userInfoService.
                findUserInfoAuditRecentlyByUid(uils.getUser_id());//查询用户提交的第一条审核信息
        Map map = new HashMap();
        map.put("uia", uia);
        vcp.setObject(map);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        return vcp;
    }
    /**
     * 分页查询未审批的专家认证信息列表
     *
     * @param
     * @return
     */
    @PostMapping("findAuditExpertInfoNonePage")
    public VisitConsequenceParent findAuditExpertInfoNonePage(
            @RequestBody PageOderRequestMap porm) {
        VisitConsequenceParent vcp = new VisitConsequencePage();
        Map param=porm.getParam();//前端传入除开分页的请求参数
        List<Map<String,Object>> uiae=new ArrayList<>();
        int user_info_audit_state = 1;
        int user_info_audit_type = 1;
        if(param!=null&&
                param.containsKey("condition")&&
                param.get("condition")!=null){//判断是否传入条件
            String condition= String.valueOf(param.get("condition"));//查询条件
            uiae =  userInfoService.
                    findUserInfoAuditExpert(porm.getPageRequest(),user_info_audit_state,
                            user_info_audit_type,condition);
        }else{
            uiae =  userInfoService.
                    findUserInfoAuditExpert(porm.getPageRequest(),user_info_audit_state,
                            user_info_audit_type);
        }
        PageInfo a=new PageInfo<Map<String,Object>>(uiae);
        vcp= PageUtils.getVisitConsequencePage(a);
        return vcp;
    }


}
