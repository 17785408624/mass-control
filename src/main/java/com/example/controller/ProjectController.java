package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.user.UserInfoLoginSession;
import com.example.service.ProjectInfoService;
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
@RequestMapping("ProjectController")
public class ProjectController {
    @Autowired
    ProjectInfoService projectInfoService;

    /**
     * 添加项目信息
     * @param projectInfoEntityWithBLOBs
     * @return
     */
    @PostMapping("addProjectInfo")
    public VisitConsequenceParent addProjectInfo(@RequestBody ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs,
                                                 HttpSession httpSession){
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginSession uils = null;
        try {
            uils=new UserInfoLoginSession(httpSession);
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            e.printStackTrace();
            return vcp;
        }
        projectInfoService.addProjectInfo(uils.getUser_id(),projectInfoEntityWithBLOBs);
        //vcp.setObject(projectInfoEntityWithBLOBs.getProjectInfoId());//项目信息主键id 项目编号
        return vcp;
    };

    /**
     *  分页查询项目审核进度为选择机构和选择专家组项目信息列表
     * @param pageOderRequest
     * @return
     */
    @PostMapping("findProjectInfoChooseByProgressOE_Page")
    public VisitConsequenceParent findProjectInfoChooseByProgressOE_Page(
            @RequestBody PageOderRequest pageOderRequest){
        List<ProjectInfoEntityWithBLOBs> listP=
                projectInfoService.findProjectInfoChooseByProgressOE(pageOderRequest);
        PageInfo a=new PageInfo<ProjectInfoEntityWithBLOBs>(listP);
        VisitConsequenceParent vcp=PageUtils.getVisitConsequencePage(a);
        return vcp;
    };
    /**
     *  通过项目id查询项目全部信息
     * @param param projectInfoId:
     * @return
     */
    @PostMapping("findProjectInfoFullByPid")
    public VisitConsequenceParent findProjectInfoFullByPid(
            @RequestBody Map param){
        Integer projectInfoId=Integer.parseInt(
                String.valueOf(param.get("projectInfoId")));
        ProjectInfoEntityWithBLOBs pe=projectInfoService.findProjectInfoFullByPid(projectInfoId);
        VisitConsequenceParent vcp= new VisitConsequenceParentImpl();
        vcp.setObject(pe);
        return vcp;
    };

    /**
     * 抽取审核项目的第三方机构
     * @param param
     * @return
     */
    @PostMapping("extractionProjectOrganization")
    public VisitConsequenceParent extractionProjectOrganization(
            @RequestBody Map param){
        VisitConsequenceParent vcp= new VisitConsequenceParentImpl();
        Integer[]excludeUids=null;//排除回避的单位，单位用户id数组
        if(param.containsKey("excludeUids")&&param.get("excludeUids")!=null&&!param.get("excludeUids").equals("")){
            List<Integer> list=(List)param.get("excludeUids");
            excludeUids= new Integer[list.size()];
            for(int i=0;i<list.size();i++){
                excludeUids[i]=list.get(i);
            }
        }
        Map map=projectInfoService.extractionProjectOrganization(excludeUids);
        vcp.setObject(map);
        return vcp;
    };


}