package com.example.config;

/**
 * 业务配置
 */
public class ServiceConfig {
   public static Integer
           AUDIT_INVITE_EXPIRATION=3*24*60*60*1000;//审核邀请过期时间
    public static Integer
           PROJECT_AUDIT_EXPIRATION=3*24*60*60*1000;//项目审核过期时间
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

}
