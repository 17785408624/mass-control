package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationServiceException;
import com.example.common.exceptiondefine.RegException;
import com.example.common.exceptiondefine.SedMessagesException;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.AddOrganizationInfoAudit;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.resultsparam.ExpertInfoResults;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.ExpertInfoEntity;
import com.example.entity.user.UserEntity;
import com.example.entity.user.UserInfoLoginEntity;
import com.example.service.UserInfoAuditService;
import com.example.service.UserService;
import com.example.service.vice.LoginVice;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import com.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private UserInfoAuditService userInfoService;
    @Autowired
    private LoginVice loginVice;

    /**
     * 用户注册
     */
    @RequestMapping("/reg")
    public VisitConsequenceParent reg(@RequestBody UserEntity user) {//用户注册
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        if (!loginVice.isboundPhoneNum(user.getUser_mobile_phone())) {//注册的手机号码是否绑定验证
            vcp.setState(1);
            vcp.setMessage("请先绑定手机号码");
            return vcp;
        }
        ;
        try {
            userService.regUser(user);//新增注册用户
        } catch (RegException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            //e.printStackTrace();
            return vcp;
        }
        vcp.setMessage("请求成功");
        vcp.setState(0);
        return vcp;

    }

    /**
     * 用户登录
     *
     * @param httpServletRequest
     * @param map
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public VisitConsequenceParent login(HttpServletRequest httpServletRequest,
                                        @RequestBody Map map
    ) {//用户登录
        String user_mobile_phone = map.get("user_mobile_phone").toString();
        String user_password = map.get("user_password").toString();
        VisitConsequenceParentImpl visitConsequenceParent = new VisitConsequenceParentImpl();
        UserEntity ue = null;
        try {
            ue = userService.userLoginByMobilePhone(user_mobile_phone, user_password);
        } catch (LoginException e) {
            visitConsequenceParent.setState(1);
            visitConsequenceParent.setMessage(e.getMessage());
            return visitConsequenceParent;
        }
        loginVice.saveLoginInfo(ue, httpServletRequest.getSession());//保存用户登录信息
        visitConsequenceParent.setMessage("请求成功");
        visitConsequenceParent.setState(0);
        visitConsequenceParent.setObject(ue);
        return visitConsequenceParent;

    }

    /**
     * @param addOia 添加第三方机构资料审核信息
     * @return
     */
    @PostMapping("/addOrganizationInfoAudit")
    public VisitConsequenceParent addOrganizationInfoAudit(@RequestBody AddOrganizationInfoAudit addOia,
                                                           HttpSession session) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginEntity uie = null;
        try {
            uie = loginVice.getLoginInfo(session);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        int user_info_audit_id = userService.addOrganizationInfoAudit(
                addOia.getOrganizationInfo(),
                addOia.getOceList(),
                Integer.valueOf(uie.getUser_id()),
                Integer.valueOf(uie.getUser_state()));
        //userService.addOrganizationInfoAudit(addOia);

        //map.put("user_info_audit_id","100003");
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(user_info_audit_id);
        return vcp;
    }

    /**
     * 添加专家信息审核申请
     *
     * @param eie
     * @param session
     * @return
     */
    @PostMapping("/addExpertInfoAudit")
    public VisitConsequenceParent addExpertInfoAudit(@RequestBody ExpertInfoEntity eie,
                                                     HttpSession session) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginEntity uie = null;
        try {
            uie = loginVice.getLoginInfo(session);
        } catch (LoginException e) {
            e.printStackTrace();
            vcp.setMessage("请求失败" + e.getMessage());
            vcp.setState(0);
            return vcp;
        }
        try {
            int user_info_audit_id = userService.addExpertInfoAudit(eie,
                    Integer.valueOf(uie.getUser_id()),
                    Integer.valueOf(uie.getUser_state()));//添加审核信息与需要被审核的资料信息返回审核编号
        } catch (OperationServiceException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        String user_info_audit_state = "1";//状态。。。。。
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(user_info_audit_state);
        return vcp;

    }

    /**
     * 用户注销登录
     *
     * @param httpSession
     * @return
     */
    @GetMapping("userLoginOut")
    public VisitConsequenceParent userLoginOut(HttpSession httpSession) {//用户登录
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        if (userService.userLoginOut()) {
            vcp.setMessage("请求成功");
            vcp.setState(0);
        }
        ;
        return vcp;
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
        UserInfoLoginEntity uie = null;
        try {
            uie = loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            e.printStackTrace();
        }

        ExpertInfoResults eir = userInfoService.findExpertInfoFullNowsave(Integer.valueOf(uie.getUser_id()));

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
        UserInfoLoginEntity uie = null;
        try {
            uie = loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            e.printStackTrace();
        }
        OrganizationAuditResults oar =
                userInfoService.findOrganizationInfoFullNowsave(
                        Integer.valueOf(uie.getUser_id())
                );
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(oar);
        return vcp;
    }

    /**
     * 查询已审核第三方机构id和名字信息列表
     *
     * @return
     */
    @GetMapping("findOINameIdListCertified")
    public VisitConsequenceParent findOINameIdListCertified() {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        List<Map> listM = userService.findOINameIdListCertified();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(listM);
        return vcp;
    }

    /**
     * 分页查询专家信息列表
     *
     * @param pageOderRequest
     * @return
     */
    @PostMapping("findExpertInfoListPage_2")
    public VisitConsequenceParent findExpertInfoListPage(
            @RequestBody PageOderRequest pageOderRequest) {
        List<Map> listMap = userService.findExpertInfoListPage(pageOderRequest, 2);
        PageInfo a = new PageInfo<Map>(listMap);
        VisitConsequenceParent vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    ;

    /**
     * 查询专家信息列表
     *
     * @param
     * @return
     */
    @GetMapping("findExpertInfoList_2")
    public VisitConsequenceParent findExpertInfoListPage() {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        List<Map> listMap = userService.findExpertInfoList(2);
        vcp.setObject(listMap);
        return vcp;
    }

    ;

    /**
     * 查询审核项目的第三方机构信息
     *
     * @param param
     * @return
     */
    @PostMapping("findProjectAuditOi")
    public VisitConsequenceParent findProjectAuditOi(@RequestBody Map param) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        Integer projectInfoId = Integer.parseInt(String.valueOf(param.get("projectInfoId")));
        Map map = userService.findProjectAuditOi(projectInfoId);
        vcp.setObject(map);
        return vcp;
    }

    ;

    /**
     * 查询审核通过的用户信息列表(专家)
     *
     * @param porm
     * @return
     */
    @RequestMapping("findExperList")
    public VisitConsequenceParent findExperList(@RequestBody PageOderRequestMap porm) {
        String conditions = null;
        if (porm.getParam() != null && porm.getParam().get("conditions") != null) {
            conditions = porm.getParam().get("conditions").toString();//获取搜索条件
        }
        VisitConsequenceParent vcp = new VisitConsequencePage();
        List<Map> listM = userService.findExperList(conditions, porm);
        PageInfo a = new PageInfo<Map>(listM);
        vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    ;

    /**
     * 密码重置为默认
     *
     * @param pamar userCode 用户id或者手机号
     * @return
     */
    @RequestMapping("resetPasswordDefault")
    public VisitConsequenceParent resetPasswordDefault(@RequestBody Map pamar) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        String userCode = String.valueOf(pamar.get("userCode"));
        userService.resetPassword(userCode);


        return vcp;
    }

    /**
     * 查询用户详细信息(专家)
     *
     * @param param user_id用户id
     * @return
     */
    @PostMapping("findUserInfoFullE")
    public VisitConsequenceParent findUserInfoFullE(@RequestBody Map param) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        Integer user_id = Integer.valueOf(param.get("user_id").toString());
        ExpertInfoResults eir = userInfoService.findExpertInfoFullNowsave(user_id);
        eir.setUser_id(user_id);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(eir);
        return vcp;
    }

    /**
     * 查询用户详细信息(第三方机构)
     *
     * @param param
     * @return
     */
    @PostMapping("findUserInfoFullO")
    public VisitConsequenceParent findUserInfoFullO(@RequestBody Map param) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        Integer user_id = Integer.valueOf(param.get("user_id").toString());

        OrganizationAuditResults oar =
                userInfoService.findOrganizationInfoFullNowsave(user_id);
        oar.setUser_id(user_id);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(oar);
        return vcp;
    }

    /**
     * 用户获取绑定手机的验证码
     *
     * @param param
     * @return
     */
    @PostMapping("getPhoneBoundCod")
    public VisitConsequenceParent getPhoneBoundCod(@RequestBody Map param, HttpServletRequest request) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        String phoneNum = param.get("phoneNum").toString();
        try {
            userService.getPhoneBoundCod(phoneNum);//用户获取绑定手机验证码
        } catch (SedMessagesException e) {
            e.printStackTrace();
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
        }
        return vcp;
    }

    /**
     * 用户绑定手机号
     *
     * @param param
     * @param request
     * @return
     */
    @PostMapping("boundPhone")
    public VisitConsequenceParent boundPhone(@RequestBody Map param, HttpServletRequest request) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        String phoneNum = param.get("phoneNum").toString();
        String boundPhoneCode = param.get("boundPhoneCode").toString();
        UserInfoLoginEntity ue = null;
        try {
            ue = loginVice.getLoginInfo(request.getSession());
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            e.printStackTrace();
            return vcp;
        }
        userService.boundPhone(phoneNum, boundPhoneCode, Integer.valueOf(ue.getUser_id()));
        return vcp;
    }

    /**
     * 获取手机号是否已经绑定过
     *
     * @param param phoneNum手机号码
     * @return
     */
    @PostMapping("isBoundPhoneNum")
    public VisitConsequenceParent isBoundPhoneNum(@RequestBody Map param) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        String phoneNum = param.get("phoneNum").toString();
        Boolean b = loginVice.isboundPhoneNum(phoneNum);
        vcp.setObject(b);
        return vcp;
    }

    /**
     * 查询第三方机构信息列表
     *
     * @param pageOderRequestMap
     * @return
     */
    @PostMapping("findOI")
    public VisitConsequenceParent findOI(@RequestBody PageOderRequestMap pageOderRequestMap) {
        List<Map> listM = userService.findOINameIdListCertified(pageOderRequestMap);
        VisitConsequenceParent vcp;
        PageInfo a = new PageInfo<Map>(listM);
        vcp = PageUtils.getVisitConsequencePage(a);
        vcp.setMessage("请求成功");
        vcp.setState(0);
        vcp.setObject(listM);
        return vcp;
    }

    /**
     * 解聘用户
     * @param param
     * @return
     */
    @PostMapping("dismissUser")
    public VisitConsequenceParent dismissUser(@RequestBody Map<String, Integer[]> param) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        Integer[] uIds = param.get("uIds");
        if (!userService.dismissUser(uIds)) {
            vcp.setMessage("解聘用户请求错误");
            vcp.setState(1);
        } ;
        return vcp;
    }

    /**
     * 返聘用户
     * @param param
     * @return
     */
    @PostMapping("rehireUser")
    public VisitConsequenceParent rehireUser(@RequestBody Map<String, Integer[]> param) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        Integer[] uIds = param.get("uIds");
        if (!userService.rehireUser(uIds)) {
            vcp.setMessage("返聘用户请求错误");
            vcp.setState(1);
        } ;
        return vcp;
    }

    /**
     * 查询专家用户信息列表
     * @param porm
     * @return
     */
    @RequestMapping("findExperListBstate")
    public VisitConsequenceParent findExperListBstate(@RequestBody PageOderRequestMap porm) {
       Map param=porm.getParam();//前端传入的请求参数
        String condition=param.get("condition").toString();//搜索条件
        Object []userStates=null;//用户状态 1未认证审核 2已认证审核  3解聘
        if(!PublicUtil.mapKeyIsNull_keyString(param,"userStates")){//前端是否传入用户状态参数
            userStates= ((List) param.get("userStates")).toArray();

        }
        VisitConsequenceParent vcp = new VisitConsequencePage();
        List listM=userService.findExperList(porm,userStates,condition);
        PageInfo a = new PageInfo<Map>(listM);
        vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }


}
