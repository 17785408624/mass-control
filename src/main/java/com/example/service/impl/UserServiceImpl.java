package com.example.service.impl;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.RegException;
import com.example.config.LonginConf;
import com.example.entity.user.*;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.util.EncryptUtil;
import com.util.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean regUser(UserEntity userEntity) throws RegException {//用户注册
        if (userEntity.getUser_role() == 5) {//不能注册添加超级管理员用户
            throw new RegException("不能注册添加超级管理员用户");
        }
        userEntity.setUser_register_time(new Date().getTime());
        userEntity.setUser_password(MyMD5Util.encrypt(userEntity.getUser_password()));
        List list = userMapper.selectUserEntityByMobilePhone(userEntity.getUser_mobile_phone());
        if (list != null && list.size() > 0) {
            throw new RegException("手机号重复");
        }
        userMapper.insertUserEntity(userEntity);
        return true;

    }

    //添加第三方机构信息审核申请
    @Override
    public int addOrganizationInfoAudit(OrganizationInfoEntity organizationInfoEntity,
                                        List<OrganizationInfoCareermanEntity> oceList,
                                        int user_id, int user_state) {
        userMapper.insertOrganizationInfoEntity(organizationInfoEntity);//添加第三方机构信息
        userMapper.insertBatchOrganizationInfoCareermanEntity(
                oceList, organizationInfoEntity.getOrganization_info_id());//添加第三方机构人员信息
        UserInfoAuditEntity userInfoAuditEntity = new UserInfoAuditEntity();//用户信息审核类
        userInfoAuditEntity.setUser_id_add(user_id);//申请人id
        userInfoAuditEntity.setUser_info_audit_addtime(new Date().getTime());//添加时间
        userInfoAuditEntity.setInfo_id(organizationInfoEntity.getOrganization_info_id());//审核的资料信息id
        userInfoAuditEntity.setUser_info_audit_content(1);//审核内容为第三方机构信息
        switch (user_state) {//判断用户状态
            case 1://用户未认证审核
                userInfoAuditEntity.setUser_info_audit_type(1);//审核类型设置为初审
                break;
            case 2://用户已认证审核
                userInfoAuditEntity.setUser_info_audit_type(2);//审核类型设置为变更
                break;
            default://解聘、其它
                userInfoAuditEntity.setUser_info_audit_type(2);//审核类型设置为变更
                break;
        }
        userInfoAuditEntity.setUser_id_add(user_id);//添加人id
        userInfoAuditEntity.setUser_info_audit_state(1);//审核状态 为未审核
        userMapper.insertUserInfoAuditEntity(userInfoAuditEntity);//添加审核申请
        return userInfoAuditEntity.getUser_info_audit_id();//返回申请信息id 申请编号
    }


    //用户登录
    @Override
    public UserEntity userLoginByMobilePhone(String user_mobile_phone, String user_password, HttpSession session) throws LoginException {
        user_password = MyMD5Util.encrypt(user_password);
        UserEntity ue = userMapper.selectUserEntityByMobilePhone_Password(user_mobile_phone, user_password);

        if (ue == null) {
            throw new LoginException("账号/密码错误");
        }
        session.setAttribute(LonginConf.LONGIN_SESSION_KEY, ue);//将登录信息保存session
        ue.setUser_password(EncryptUtil.encrypt(ue.getUser_password()));
        ue.setUser_id(Integer.parseInt(EncryptUtil.encrypt(String.valueOf(ue.getUser_id()))));
        return ue;
    }
    //添加专家审核信息申请
    @Override
    public int addExpertInfoAudit(ExpertInfoEntity expertInfoEntity, int user_id, int user_state) {
        userMapper.insertExpertInfoEntity(expertInfoEntity);
        UserInfoAuditEntity userInfoAuditEntity = new UserInfoAuditEntity();//用户信息审核类
        userInfoAuditEntity.setUser_id_add(user_id);//申请人id
        userInfoAuditEntity.setUser_info_audit_addtime(new Date().getTime());//添加时间
        userInfoAuditEntity.setInfo_id(expertInfoEntity.getExpert_info_id());//审核的资料信息id
        userInfoAuditEntity.setUser_info_audit_content(1);//审核内容为第三方机构信息
        switch (user_state) {//判断用户状态
            case 1://用户未认证审核
                userInfoAuditEntity.setUser_info_audit_type(1);//审核类型设置为初审
                break;
            case 2://用户已认证审核
                userInfoAuditEntity.setUser_info_audit_type(2);//审核类型设置为变更
                break;
            default://解聘、其它
                userInfoAuditEntity.setUser_info_audit_type(2);//审核类型设置为变更
                break;
        }
        userInfoAuditEntity.setUser_id_add(user_id);//添加人id
        userInfoAuditEntity.setUser_info_audit_state(1);//审核状态 为未审核
        userMapper.insertUserInfoAuditEntity(userInfoAuditEntity);//添加用户审核信息
        return userInfoAuditEntity.getUser_info_audit_id();
    }


}
