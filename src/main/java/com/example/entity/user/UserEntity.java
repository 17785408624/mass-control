package com.example.entity.user;

/**
 * 用户类
 */
public class UserEntity {
    private int user_id;//数据主键
    private String user_mobile_phone;//移动电话手机
    private Long user_register_time;//注册时间
    private String user_password;//用户密码
    private int user_role;//用户角色： 1专家 2第三方机构 3煤监局 4能源局 5超级管理员
    private String info_id;//用户信息
    private Integer user_state;//用户状态  1未认证审核 2已认证审核  3解聘

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_mobile_phone() {
        return user_mobile_phone;
    }

    public void setUser_mobile_phone(String user_mobile_phone) {
        this.user_mobile_phone = user_mobile_phone;
    }

    public Long getUser_register_time() {
        return user_register_time;
    }

    public void setUser_register_time(Long user_register_time) {
        this.user_register_time = user_register_time;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }



    public int getUser_state() {
        return user_state;
    }

    public void setUser_state(int user_state) {
        this.user_state = user_state;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }
}
