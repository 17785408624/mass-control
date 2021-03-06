package com.example.controller;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationServiceException;
import com.example.entity.ProjectInfoEntityWithBLOBs;
import com.example.entity.common.VisitConsequencePage;
import com.example.entity.common.VisitConsequenceParent;
import com.example.entity.common.VisitConsequenceParentImpl;
import com.example.entity.requstparam.ExtractionPERequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.user.UserInfoLoginEntity;
import com.example.service.ProjectInfoService;
import com.example.service.vice.LoginVice;
import com.github.pagehelper.PageInfo;
import com.util.PageUtils;
import com.util.PublicUtil;
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
    @Autowired
    LoginVice loginVice;

    /**
     * 添加项目信息
     *
     * @param projectInfoEntityWithBLOBs
     * @return
     */
    @PostMapping("addProjectInfo")
    public VisitConsequenceParent addProjectInfo(@RequestBody ProjectInfoEntityWithBLOBs projectInfoEntityWithBLOBs,
                                                 HttpSession httpSession) {
        VisitConsequenceParentImpl vcp = new VisitConsequenceParentImpl();
        UserInfoLoginEntity uie = null;
        try {
            uie =loginVice.getLoginInfo(httpSession);
        } catch (LoginException e) {
            vcp.setMessage(e.getMessage());
            vcp.setState(1);
            e.printStackTrace();
            return vcp;
        }
        projectInfoService.addProjectInfo(Integer.parseInt(uie.getUser_id()), Integer.valueOf(uie.getUser_role()), projectInfoEntityWithBLOBs);
        //vcp.setObject(projectInfoEntityWithBLOBs.getProjectInfoId());//项目信息主键id 项目编号
        return vcp;
    }

    ;

    /**
     * 分页查询项目审核进度为选择机构和选择专家组项目信息列表
     *
     * @param pageOderRequest
     * @return
     */
    @PostMapping("findProjectInfoChooseByProgressOE_Page")
    public VisitConsequenceParent findProjectInfoChooseByProgressOE_Page(
            @RequestBody PageOderRequestMap pageOderRequest) {
        List<ProjectInfoEntityWithBLOBs> listP =
                projectInfoService.findProjectInfoChooseByProgressOE(pageOderRequest);
        PageInfo a = new PageInfo<ProjectInfoEntityWithBLOBs>(listP);
        VisitConsequenceParent vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    ;

    /**
     * 通过项目id查询项目全部信息
     *
     * @param param projectInfoId:
     * @return
     */
    @PostMapping("findProjectInfoFullByPid")
    public VisitConsequenceParent findProjectInfoFullByPid(
            @RequestBody Map param) {
        Integer projectInfoId = Integer.parseInt(
                String.valueOf(param.get("projectInfoId")));
        ProjectInfoEntityWithBLOBs pe = projectInfoService.findProjectInfoFullByPid(projectInfoId);
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        vcp.setObject(pe);
        return vcp;
    }

    ;

    /**
     * 抽取审核项目的第三方机构
     *
     * @param param
     * @return
     */
    @PostMapping("extractionProjectOrganization")
    public VisitConsequenceParent extractionProjectOrganization(
            @RequestBody Map param) {
        VisitConsequenceParent vcp = new VisitConsequenceParentImpl();
        Integer[] excludeUids = null;//排除回避的单位，单位用户id数组
        if (param.containsKey("excludeUids") && param.get("excludeUids") != null && !param.get("excludeUids").equals("")) {
            List<Integer> list = (List) param.get("excludeUids");
            excludeUids = new Integer[list.size()];
            for (int i = 0; i < list.size(); i++) {
                excludeUids[i] = list.get(i);
            }
        }
        Map map = projectInfoService.extractionProjectOrganization(excludeUids);
        vcp.setObject(map);
        return vcp;
    }

    ;

    /**
     * 分页查询 进程为选择专家组组长的项目信息列表
     *
     * @param pageOderRequest
     * @return
     */
    @PostMapping("O_findProjectInfoProgressLeader_page")
    public VisitConsequenceParent findProjectInfoProgressLeader_page(HttpSession session,
            @RequestBody PageOderRequestMap pageOderRequest) {
        VisitConsequenceParent vcp=new VisitConsequencePage();
        Map param=pageOderRequest.getParam();
        String searchCondition=null;//搜索条件
        if(!PublicUtil.mapKeyIsNull_keyString(param,"searchCondition")){
            searchCondition= String.valueOf(param.get("searchCondition"));
        }
        UserInfoLoginEntity uie = null;
        try {
            uie = loginVice.getLoginInfo(session);
        } catch (LoginException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }
        List<ProjectInfoEntityWithBLOBs> listP =
                projectInfoService.findProjectInfoProgressLeader(pageOderRequest,Integer.valueOf(uie.getUser_id()),searchCondition);
        PageInfo a = new PageInfo<ProjectInfoEntityWithBLOBs>(listP);
        vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    ;

    /**
     * 抽取专家
     *
     * @param param
     * @return
     */
    @PostMapping("extractionProjectExpert")
    public VisitConsequenceParent extractionProjectExpert(@RequestBody ExtractionPERequest param) {
        Integer extractNum;//抽取人数
        Integer[] expert_info_educations =null;//专家学历
        Integer[] expert_info_workmajors=null;//专家从事专业
        extractNum= param.getExtractNum();
        if(param.getExpert_info_educations()!=null&&param.getExpert_info_educations().length>0){
            expert_info_educations=param.getExpert_info_educations();
        }
        if(param.getExpert_info_workmajors()!=null&&param.getExpert_info_workmajors().length>0){
            expert_info_workmajors=param.getExpert_info_workmajors();
        }
        List<Map>listMap= null;
        VisitConsequenceParent vcp=new VisitConsequenceParentImpl();
        try {
            listMap = projectInfoService.extractionProjectExpert(extractNum,
                    expert_info_educations,
                    expert_info_workmajors);
        } catch (OperationServiceException e) {
            vcp.setState(1);
            vcp.setMessage(e.getMessage());
            return vcp;
        }

        vcp.setObject(listMap);
        return vcp;
    }

    /**
     * 分页查询 进程为等待批复的项目信息
     * @param porm
     * @return
     */
    @PostMapping("findProjectInfoToProgress5_Page")
    public VisitConsequenceParent findProjectInfoToProgress5_Page(
            @RequestBody PageOderRequestMap porm) {
        List<ProjectInfoEntityWithBLOBs> listP ;
        if(porm.getParam()!=null&&porm.getParam().containsKey("projectInfoName")
                &&porm.getParam().get("projectInfoName")!=null&&!porm.getParam().get("projectInfoName").equals("null")){
            listP= projectInfoService.findProjectInfoToProgress5(porm, (String) porm.getParam().get("projectInfoName"));
        }else{
            listP= projectInfoService.findProjectInfoToProgress5(porm);
    }
        PageInfo a = new PageInfo<ProjectInfoEntityWithBLOBs>(listP);
        VisitConsequenceParent vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    /**
     * 查询 进程为评审项目的项目信息
     * @param porm
     * @return
     */
    @PostMapping("findProjectInfoToProgress4_Page")
    public VisitConsequenceParent findProjectInfoToProgress4_Page(
            @RequestBody PageOderRequestMap porm) {
        List<ProjectInfoEntityWithBLOBs> listP ;
        listP= projectInfoService.findProjectInfoToProgress4(porm);
        PageInfo a = new PageInfo<ProjectInfoEntityWithBLOBs>(listP);
        VisitConsequenceParent vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    /**
     * 查询所有项目信息
     * @param porm
     * @return
     */
    @PostMapping("findPiAll")
    public VisitConsequenceParent findPiAll(@RequestBody PageOderRequestMap porm){
        //String conditionSearch=pageOderRequest.getParam().;
        Map param=porm.getParam();
        String conditionSearch=null;//查询条件
        if(param!=null&&param.containsKey("conditionSearch")&&param.get("conditionSearch")!=null
                &&!param.get("conditionSearch").equals("")&&!param.get("conditionSearch").equals(null)&&!param.get("conditionSearch").equals(" ")){//判断是否传入搜索条件
            conditionSearch=String.valueOf(param.get("conditionSearch"));
        }
        List listp=projectInfoService.findPiAll(porm,conditionSearch);//查询所有项目
        PageInfo a = new PageInfo<Map>(listp);
        VisitConsequenceParent vcp = PageUtils.getVisitConsequencePage(a);
        return vcp;
    }

    /**
     * 查询项目历程
     * @param param
     * @return
     */
    @PostMapping("findProjectCourse")
    public VisitConsequenceParent findProjectCourse(@RequestBody Map param){
        Integer projectInfoId= Integer.valueOf(String.valueOf(param.get("projectInfoId")));
        VisitConsequenceParent vcp =new VisitConsequenceParentImpl();
        Map map=projectInfoService.findProjectCourse(projectInfoId);
        vcp.setObject(map);
        return vcp;
    }

    /**
     * 查询项目审核邀请信息
     * @param param
     * @return
     */
    @PostMapping("findProjectAuditInfo")
    public VisitConsequenceParent findProjectAuditInfo(@RequestBody Map param){
        Integer projectInfoId= Integer.valueOf(String.valueOf(param.get("projectInfoId")));//项目id
        Integer projectInfoProgress=Integer.valueOf(String.valueOf(param.get("projectInfoProgress")));//进程1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成
        VisitConsequenceParent vcp =new VisitConsequenceParentImpl();
        Map map=projectInfoService.findProjectAuditInfo(projectInfoId,projectInfoProgress);
        vcp.setObject(map);
        return vcp;
    }


}
