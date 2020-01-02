package com.example.entity.user;

import com.example.common.exceptiondefine.LoginException;
import com.example.mapper.UserMapper;
import com.example.service.vice.LoginVice;
import com.util.EncryptUtil;
import com.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 用户登录后保存的session信息
 */

public class UserInfoLoginSession {

    private LoginVice  loginVice;
    private UserEntity ue;
    private Integer user_id;
    private String user_mobile_phone;//移动电话手机
    private Long user_register_time;//注册时间
    private String user_password;//用户密码
    private int user_role;//用户角色： 1专家 2第三方机构 3煤监局 4能源局 5超级管理员
    private Integer info_id;//用户信息
    private Integer user_state;//用户状态  1未认证审核 2已认证审核  3解聘

    public UserEntity getUe() {
        return ue;
    }

    public void setUe(UserEntity ue) {
        this.ue = ue;
    }

    /**
     *
     * @param session
     * @throws LoginException
     */
    public UserInfoLoginSession(HttpSession session) throws LoginException {
        loginVice= (LoginVice) SpringContextUtil.getBean("loginVice");
        UserEntity userEntity;
        try {
            userEntity =loginVice.getLoginInfo(session);//获取用户登录信息
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException("登录信息错误");
        }
        if (userEntity == null) {
            throw new LoginException("登录信息错误");
        }

        ue = userEntity;
    }


    //获取当前用户id
    public Integer getUser_id() {
        return Integer.valueOf(EncryptUtil.decode(String.valueOf(ue.getUser_id())));
    }

    public String getUser_mobile_phone() {
        return ue.getUser_mobile_phone();
    }

    public Long getUser_register_time() {
        return ue.getUser_register_time();
    }

    public String getUser_password() {
        return ue.getUser_password();
    }
    //获取当前用户角色
    public int getUser_role() {
        return ue.getUser_role();
    }

    public Integer getInfo_id() {
        return ue.getInfo_id();
    }

    public Integer getUser_state() {
        return ue.getUser_state();
    }
}
