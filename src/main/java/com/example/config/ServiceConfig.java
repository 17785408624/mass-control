package com.example.config;

/**
 * 业务配置
 */
public class ServiceConfig {
   public static Integer
           AUDIT_ORGANIZATION_INVITE_EXPIRATION=5*24*60*60*1000;//第三方机构审核邀请过期时间

    /**
     * 获取第三方机构审核邀请过期时间
     * @return
     */
    public static Integer getAUDIT_ORGANIZATION_INVITE_EXPIRATION(){
        return  AUDIT_ORGANIZATION_INVITE_EXPIRATION;
    }
}
