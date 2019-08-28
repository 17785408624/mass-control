package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.RegException;
import com.example.config.LonginConf;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.AddOrganizationInfoAudit;
import com.example.entity.resultsparam.ExpertInfoResults;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.*;
import com.example.service.UserInfoService;
import com.example.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 用户注册
     */
    @RequestMapping("/reg")
    public VisitConsequenceParent reg(@RequestBody UserEntity user) {//用户注册
    	VisitConsequenceParentImpl visitConsequenceParent = new VisitConsequenceParentImpl();
        try {
            userService.regUser(user);
        } catch (RegException e) {
            visitConsequenceParent.setMessage(e.getMessage());
            visitConsequenceParent.setState(1);
            //e.printStackTrace();
            return visitConsequenceParent;
        }
        visitConsequenceParent.setMessage("请求成功");
		visitConsequenceParent.setState(0);
		return visitConsequenceParent;
    	
    }

    /**
     * 用户登录
     * @param httpServletRequest
     * @param map
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public VisitConsequenceParent login(HttpServletRequest httpServletRequest,
                                        @RequestBody Map map
                                        ) {//用户登录
        String user_mobile_phone=map.get("user_mobile_phone").toString();
        String user_password=map.get("user_password").toString();
        VisitConsequenceParentImpl visitConsequenceParent = new VisitConsequenceParentImpl();
        UserEntity ue=null;
        try {
            ue=userService.userLoginByMobilePhone(user_mobile_phone,user_password,httpServletRequest.getSession());
        } catch (LoginException e) {
            visitConsequenceParent.setState(1);
            visitConsequenceParent.setMessage(e.getMessage());
            return visitConsequenceParent;
        }
        visitConsequenceParent.setMessage("请求成功");
        visitConsequenceParent.setState(0);
        visitConsequenceParent.setObject(ue);
        return visitConsequenceParent;

    }

    /**
     *
     * @param addOia 添加第三方机构资料审核信息
     * @return
     */
    @PostMapping("/addOrganizationInfoAudit")
    public VisitConsequenceParent addOrganizationInfoAudit(@RequestBody AddOrganizationInfoAudit addOia,
                                                           HttpSession session){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uIls = null;
        try {
            uIls=new UserInfoLoginSession(session);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        int user_info_audit_id=userService.addOrganizationInfoAudit(
                addOia.getOrganizationInfo(),
                addOia.getOceList(),
                uIls.getUser_id(),
                uIls.getUser_state());
        //userService.addOrganizationInfoAudit(addOia);

        //map.put("user_info_audit_id","100003");
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(user_info_audit_id);
        return vcp;
    }

    /**
     * 添加专家信息审核申请
     * @param eie
     * @param session
     * @return
     */
    @PostMapping("/addExpertInfoAudit")
    public VisitConsequenceParent addExpertInfoAudit(@RequestBody ExpertInfoEntity eie,
                                                           HttpSession session){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uIls = null;
        try {
            uIls=new UserInfoLoginSession(session);
        } catch (LoginException e) {
            e.printStackTrace();
            vcp.setMessage("请求失败"+e.getMessage());
            vcp.setState(0);
            return vcp;
        }
        int user_info_audit_id=userService.addExpertInfoAudit(eie,
                uIls.getUser_id(),
                uIls.getUser_state());//添加审核信息与需要被审核的资料信息返回审核编号
        String user_info_audit_state ="1";//状态。。。。。
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(user_info_audit_state);
        return vcp;

    }

    /**
     *  用户注销登录
     * @param httpSession
     * @return
     */
    @GetMapping("userLoginOut")
    public VisitConsequenceParent userLoginOut(HttpSession httpSession) {//用户登录
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        userService.userLoginOut(httpSession);
        vcp.setMessage("请求成功");
        vcp.setState(0);
        return  vcp;
    }
    /**
     * 查询用户完整信息(专家)
     *
     * @param
     * @return
     */
    @GetMapping("findUserInfoFullExpert")
    public VisitConsequenceParent findUserInfoFullExpert(HttpSession httpSession) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession userInfoLoginSession = null;
        try {
             userInfoLoginSession=
                    new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            e.printStackTrace();
        }

        ExpertInfoResults eir= userInfoService.findExpertInfoFullNowsave(userInfoLoginSession.getUser_id());

        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(eir);
        return vcp;
    }
    /**
     * 查询用户完整信息(第三方机构)
     *
     * @param
     * @return
     */
    @GetMapping("findUserInfoFullOrganization")
    public VisitConsequenceParent findUserInfoFullOrganization(HttpSession httpSession) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession userInfoLoginSession = null;
        try {
            userInfoLoginSession=
                    new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            e.printStackTrace();
        }
        OrganizationAuditResults oar =
                userInfoService.findOrganizationInfoFullNowsave(
                        userInfoLoginSession.getUser_id()
                );
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(oar);
        return vcp;
    }

    /**
     * 查询第三方机构id和名字信息列表
     * @return
     */
    @GetMapping("findOrganizationInfoNameIdList")
    public VisitConsequenceParent findOrganizationInfoNameIdList() {
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        List<Map> listM=userService.findOrganizationInfoNameIdList();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(listM);
        return  vcp;
    }

}
