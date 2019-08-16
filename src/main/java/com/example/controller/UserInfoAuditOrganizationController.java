package com.example.controller;

import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.service.UserInfoAuditOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/UserInfoAuditOrganization")
public class UserInfoAuditOrganizationController {
    @Autowired
    UserInfoAuditOrganizationService userInfoAuditOrganizationService;

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
     * 查询未审核的第三方机构信息
     * @param param
     * @return
     */
    @RequestMapping("findNotAuditUserInfoAuditOrganizationFirst")
    public VisitConsequenceParent findNotAuditUserInfoAuditOrganizationFirst(@RequestBody Map param){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        vcp.setMessage("请求成功");
        vcp.setState(0);
        int user_info_audit_state= 1;
        int user_info_audit_type= 1;
        vcp.setObject(
                userInfoAuditOrganizationService.
                        findUserInfoAuditOrganization(user_info_audit_state,
                                user_info_audit_type));


        return vcp;

    }

    /**
     * 查询第三方机构完整信息
     * @param param
     * @return
     */
    @RequestMapping("findUserInfoAuditOrganizationFull")
    public VisitConsequenceParent findUserInfoAuditOrganizationFull(@RequestBody Map param){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        int organization_info_id = (int) param.get("info_id");
        OrganizationAuditResults oar=userInfoAuditOrganizationService.findOrganizationInfoFull(organization_info_id);
        vcp.setState(0);
        vcp.setMessage("请求成功");
        vcp.setObject(oar);
        return vcp;
    }



}
