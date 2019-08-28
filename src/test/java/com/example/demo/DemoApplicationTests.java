//package com.example.demo;
//
//import com.example.entity.ProjectInfoEntityWithBLOBs;
//import com.example.entity.requstparam.OrderRequest;
//import com.example.mapper.ProjectInfoEntityMapper;
//import com.example.mapper.UserInfoAuditMapper;
//import com.example.mapper.UserMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DemoApplicationTests {
//    @Autowired
//    ProjectInfoEntityMapper projectInfoEntityMapper;
//    @Autowired
//    UserMapper userMapper;
//
//    @Test
//    public void contextLoads() {
//
////        sys_roleExample sys_roleExample = new sys_roleExample();
////        sys_roleExample.Criteria criteria1=sys_roleExample.createCriteria();
////        criteria1.andIdEqualTo(2);
////
////        sys_roleExample.setOrderByClause("id");
////
////        List<sys_role> li = sys_roleMapper.selectByExample(sys_roleExample);
////        //FileInfoeEntity f=UserInfoAuditMapper.selectFileInfoeEntityFullByFileId(3);
////        System.out.println();
//        int []i={1,2};
//        OrderRequest o=new OrderRequest();
//        o.setOrderName("project_info_id");
//        o.setOrderRule("ASC");
//        OrderRequest o1=new OrderRequest();
//        o1.setOrderName("project_info_invest");
//        o1.setOrderRule("DESC");
//
//        OrderRequest[]ii={o,o1};
//        List<ProjectInfoEntityWithBLOBs>l=
//                projectInfoEntityMapper.selectListByPiProgress(i,ii);
//        System.out.println();
//
//
//    }
//
//}
