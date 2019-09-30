package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.ProjectReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("ProjectReplyController")
public class ProjectReplyController {
    @Autowired
    ProjectReplyService projectReplyService;

    /**
     * 项目批复操作
     * @param session
     * @param param
     * @return
     */
    @PostMapping("ReplyProject")
    public VisitConsequenceParent ReplyProject(HttpSession session, @RequestBody Map param){
        VisitConsequenceParent vcp =new VisitConsequenceParentImpl();
        Integer userId=null;//用户id
        String replyContent=null;//批复内容
        Integer projectInfoId=null;//项目id
        Long replyTime=null;//批复时间
        replyContent= String.valueOf(param.get("replyContent"));
        projectInfoId=Integer.parseInt(String.valueOf(param.get("projectInfoId")));
        try {
            userId=new UserInfoLoginSession(session).getUser_id();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        //判断用户是否传入批复时间
        if(param.containsKey("replyTime")&&param.get("replyTime")!=null&&!param.get("replyTime").equals("0")){
            replyTime=Long.parseLong(String.valueOf(param.get("replyTime")));
            projectReplyService.ReplyProject(projectInfoId,replyContent,userId,replyTime);
        }else{
            projectReplyService.ReplyProject(projectInfoId,replyContent,userId);
        }

        return vcp;
    }

}
