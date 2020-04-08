package com.example.common.service;

import com.example.mapper.ProjectInfoEntityMapper;
import com.example.mapper.UserMapper;
import com.example.service.ProjectInfoService;
import com.example.service.UserService;
import com.example.service.vice.ExpertInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 */
@Slf4j
@Component
public class ScheduledService  implements InitializingBean {
    @Autowired
    ProjectInfoService projectInfoService;
    @Autowired
    UserService userService;

    /**
     * 判断项目状态是否需要改变为等待批复
     */
    @Scheduled(cron = "0 0 0 * * ?")//每天0点执行
    public void Service0h() {
        projectInfoService.projectProcessReply();//将超过工作时间点的项目改变为等待批复
        userService.mergeSimilarityExpertCompany("database",true);//合并相似的专家公司名
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.mergeSimilarityExpertCompany("database",true);//合并相似的专家公司名

    }
}
