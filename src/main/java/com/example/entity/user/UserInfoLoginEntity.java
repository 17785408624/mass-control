package com.example.entity.user;

import com.util.EncryptUtil;

/**
 * 用户登录信息
 */
public class UserInfoLoginEntity {
    private String user_id;//数据主键
    private String user_mobile_phone;//移动电话手机
    private String user_register_time;//注册时间
    private String user_password;//用户密码
    private String user_role;//用户角色： 1专家 2第三方机构 3煤监局 4能源局 5超级管理员
    private String info_id;//用户信息
    private String user_state;//用户状态  1未认证审核 2已认证审核  3解聘

    public String getUser_id() {
        return EncryptUtil.decode(user_id);
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_mobile_phone() {
        return EncryptUtil.decode(user_mobile_phone);
    }

    public void setUser_mobile_phone(String user_mobile_phone) {
        this.user_mobile_phone = user_mobile_phone;
    }

    public String getUser_register_time() {
        return EncryptUtil.decode(user_register_time);
    }

    public void setUser_register_time(String user_register_time) {
        this.user_register_time = user_register_time;
    }

    public String getUser_password() {
        return EncryptUtil.decode(user_password);
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_role() {
        return EncryptUtil.decode(user_role);
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getInfo_id() {
        return EncryptUtil.decode(info_id);
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getUser_state() {
        return EncryptUtil.decode(user_state);
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public UserInfoLoginEntity(UserEntity ue){
        setInfo_id(EncryptUtil.encrypt(ue.getInfo_id()));
        setUser_id(EncryptUtil.encrypt(ue.getUser_id()));
        setUser_mobile_phone(EncryptUtil.encrypt(ue.getUser_mobile_phone()));
        setUser_password(EncryptUtil.encrypt(ue.getUser_password()));
        setUser_register_time(EncryptUtil.encrypt(ue.getUser_register_time()));
        setUser_role(EncryptUtil.encrypt(ue.getUser_role()));
        setUser_state(EncryptUtil.encrypt(ue.getUser_state()));

    }
}
