package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.requstparam.FindtEaiListUser_pageR;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.user.UserInfoLoginEntity;
import com.example.service.ExpertAuditInfoService;
import com.example.service.vice.LoginVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ExpertAuditInfoController")
public class ExpertAuditInfoController {
    @Autowired
    ExpertAuditInfoService expertAuditInfoService;
    @Autowired
    private LoginVice loginVice;
    /**
     * 第三方机构用户分页查询专家评测信息列表
     * @return
     */
    @RequestMapping("findtEaiListUserO_page")
    public VisitConsequenceParent findtEaiListUserO_page(HttpSession session, @RequestBody FindtEaiListUser_pageR fr){
        VisitConsequenceParent vcp=new VisitConsequencePage();
        Boolean isContainExpiration=fr.getContainExpiration();//是否包含工作时间已过期的数据
        Boolean isContainAuditnumZero=fr.getContainAuditnumZero();//是否包含未审核人数为0的数据
        PageOderRequest pageOderRequest=fr.getPageOderRequest();//分页排序信息
        UserInfoLoginEntity uie=null;
        try {
            uie=loginVice.getLoginInfo(session);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            e.printStackTrace();
            return vcp;
        }
        //查询专家评测信息列表
        List<Map> listM=expertAuditInfoService.findtEaiList_page(
                Integer.valueOf(uie.getUser_id()),4,isContainExpiration,isContainAuditnumZero,pageOderRequest);
        vcp.setObject(listM);
        return vcp;
    }
}
