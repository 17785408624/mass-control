package com.example.demo;


import com.example.entity.phoneMessages.PhoneMessagesEntity;
import com.example.entity.phoneMessages.PhoneMessagesType;
import com.example.mapper.PhoneMessagesMapper;
import com.example.mapper.ProjectInfoEntityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    @Autowired
    PhoneMessagesMapper phoneMessagesMapper;
    @Test
    public void test() {
        PhoneMessagesEntity pm=new PhoneMessagesEntity();
        pm.setPhoneMessagesType(PhoneMessagesType.messagesBoundCod);
        pm.setPhoneNum("1333333l");
        pm.setSendDate(Instant.now().getEpochSecond());
        pm.setSendData(new String[]{"内容1","内容2","内容1","内容1","内容1",});
        pm.setFullContent("全部内容");
        phoneMessagesMapper.insertPhoneMessages(pm);


    }

}
