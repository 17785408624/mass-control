package com.example.config;

/**
 * 业务配置
 */
public class ServiceConfig {
   public static Integer
           AUDIT_INVITE_EXPIRATION=3*24*60*60*1000;//审核邀请过期时间
    public static Integer
           PROJECT_AUDIT_EXPIRATION=3*24*60*60*1000;//项目审核过期时间
    public static Integer[] PROJECT_PARTICIPANT_DECISION={2,3,4,5};//可以操作项目是否通的角色 1专家 2第三方机构 3煤监局 4能源局 5超级管理员 6组长
    public static Integer[]PROJECT_PARTICIPANT_PRIORITY={5,4,3,2};//项目操作优先级 从左往右开始 优先级从大到小
    /**
     *获取审核邀请过期时间
     * @param startDate 邀请添加时间
     * @return
     */
    public static Long getAUDIT_INVITE_EXPIRATION(Long startDate){
        return  AUDIT_INVITE_EXPIRATION+startDate;
    }
    /**
     * 获取项目审核过期时间
     * @param startDate 项目审核开始时间
     * @return
     */
    public static Long getPROJECT_AUDIT_EXPIRATION(Long startDate)
    {
        return  AUDIT_INVITE_EXPIRATION+startDate;
    }

    /**
     * 判断项目参与者是否可以对项目状态进行操作
     * @param participantUserRole 参与者角色
     * @return ture为可以操作 false为不能操作
     */
    Boolean isDecision(Integer participantUserRole){
        for (Integer role:PROJECT_PARTICIPANT_DECISION
             ) {
            if(role==participantUserRole){
                return true;
            }
        }
        return false;
     }
}
