package com.example.common.service;

import com.example.mapper.ProjectInfoEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 */
@Slf4j
@Component
public class ScheduledService {
    @Autowired
    ProjectInfoEntityMapper projectInfoEntityMapper;
    /**
     * 判断项目状态是否需要改变为等待批复
     */
    @Scheduled(cron  = "0 0 0 * * ?")//每天0点执行
    public void isProjectProcessReply(){
        Integer[]projectInfoIds= projectInfoEntityMapper.
                selectPidToReviewExpiration();//查询需要改变状态的项目id 查询超过工作时间点的项目
        //修改项目状态为等待批复
        projectInfoEntityMapper.updateProjectInfoStateByPIidBatch(projectInfoIds,5);

    }
}
