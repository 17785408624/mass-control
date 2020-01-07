package com.example.service;

public interface AdminService {
    /**
     * 重置密码为默认值
     * @param userCod 用户手机号或者是用户id
     * @return
     */
    Boolean resetPassword(String userCod);

    /**
     *  重置密码
     * @param userCod 用户手机号或者是用户id
     * @param password 重置后的密码
     * @return
     */
    Boolean resetPassword(String userCod,String password);
}
