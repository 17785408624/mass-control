package com.example.service.impl;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.RegException;
import com.example.config.LonginConf;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserEntity;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.util.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean regUser(UserEntity userEntity) throws RegException {//用户注册
        if(userEntity.getUser_role()==5){//不能注册添加超级管理员用户
            throw new RegException("不能注册添加超级管理员用户");
        }
        userEntity.setUser_register_time(new Date().getTime());
        userEntity.setUser_password(MyMD5Util.encrypt(userEntity.getUser_password()));
        List list=userMapper.selectUserEntityByMobilePhone(userEntity.getUser_mobile_phone());
        if(list!=null&&list.size()>0){
            throw new RegException("手机号重复");
        }
        userMapper.insertUserEntity(userEntity);
        return true;

    }

    //添加第三方机构信息审核申请
    @Override
    public int addOrganizationInfoAudit(OrganizationInfoEntity organizationInfoEntity, int user_id, int user_state) {

        return 0;
    }


    //用户登录
    @Override
    public UserEntity userLoginByMobilePhone(String user_mobile_phone, String user_password, HttpSession session) throws LoginException {
        user_password=MyMD5Util.encrypt(user_password);
        UserEntity ue= userMapper.selectUserEntityByMobilePhone_Password(user_mobile_phone,user_password);

        if(ue==null){
            throw new LoginException("账号/密码错误");
        }
        session.setAttribute(LonginConf.LONGIN_SESSION_KEY,ue);//将登录信息保存session
        ue.setUser_password(null);
        ue.setUser_id(0);
        return ue;
    }


}
