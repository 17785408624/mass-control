package com.example.demo;

import com.example.mapper.UserInfoAuditMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserInfoAuditMapper UserInfoAuditMapper;

    @Test
    public void contextLoads() {

//        sys_roleExample sys_roleExample = new sys_roleExample();
//        sys_roleExample.Criteria criteria1=sys_roleExample.createCriteria();
//        criteria1.andIdEqualTo(2);
//
//        sys_roleExample.setOrderByClause("id");
//
//        List<sys_role> li = sys_roleMapper.selectByExample(sys_roleExample);
//        //FileInfoeEntity f=UserInfoAuditMapper.selectFileInfoeEntityFullByFileId(3);
//        System.out.println();


    }

}
