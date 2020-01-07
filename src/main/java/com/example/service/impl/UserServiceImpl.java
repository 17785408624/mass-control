package com.example.service.impl;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationServiceException;
import com.example.common.exceptiondefine.RegException;
import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.user.*;
import com.example.mapper.UserInfoAuditMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.service.vice.LoginVice;
import com.github.pagehelper.PageHelper;
import com.util.EncryptUtil;
import com.util.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoAuditMapper userInfoAuditMapper;
    @Autowired
    LoginVice loginVice;
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
        userInfoAuditEntity.setUser_info_audit_state(1);//审核状态 1为未审核
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
        loginVice.saveLoginInfo(ue,session);//保存用户登录信息
        ue.setUser_password(EncryptUtil.encrypt(ue.getUser_password()));
        ue.setUser_id(Integer.parseInt(EncryptUtil.encrypt(String.valueOf(ue.getUser_id()))));
        return ue;
    }

    /**
     * 重新登录
     * @param session
     * @return
     */
    @Override
    public UserEntity againLoginByMobilePhone(UserEntity ue,HttpSession session){
        loginVice.updateLoginInfo(ue,session);//更新用户登录信息
        ue.setUser_password(EncryptUtil.encrypt(ue.getUser_password()));
        ue.setUser_id(Integer.parseInt(EncryptUtil.encrypt(String.valueOf(ue.getUser_id()))));
        return ue;
    }
    //添加专家审核信息申请
    @Override
    public int addExpertInfoAudit(ExpertInfoEntity expertInfoEntity, int user_id, int user_state) throws OperationServiceException {
        userMapper.insertExpertInfoEntity(expertInfoEntity);
        UserInfoAuditEntity userInfoAuditEntity = new UserInfoAuditEntity();//用户信息审核类
        userInfoAuditEntity.setUser_id_add(user_id);//申请人id
        userInfoAuditEntity.setUser_info_audit_addtime(new Date().getTime());//添加时间
        userInfoAuditEntity.setInfo_id(expertInfoEntity.getExpert_info_id());//审核的资料信息id
        userInfoAuditEntity.setUser_info_audit_content(2);//审核内容为专家信息
        Integer num=userInfoAuditMapper.selectUiaByUid(user_id,2);//查询用户未审核的信息
        if(num>0){
            throw new OperationServiceException("已提交过审核申请，请等待后台审核");
        }
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
        userInfoAuditEntity.setUser_info_audit_state(1);//审核状态 1为未审核
        userMapper.insertUserInfoAuditEntity(userInfoAuditEntity);//添加用户审核信息
        return userInfoAuditEntity.getUser_info_audit_id();
    }
    //注销登录
    @Override
    public boolean userLoginOut(HttpSession httpSession) {
        loginVice.cleanLoginInfo(httpSession);//清空登录信息
        return true;
    }
    //查询已审核认证的第三方机构id和名字信息列表
    @Override
    public List<Map> findOINameIdListCertified() {
        return userMapper.selectOrganizationInfoNameIdListByState(2);
    }

    //分页查询专家信息列表
    @Override
    public List<Map> findExpertInfoListPage(PageOderRequest pageOderRequest,Integer user_state) {
        PageRequest pageRequest=pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();//当前页面
        int pageSize = pageRequest.getPageSize();//每页长度
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return userMapper.selectExpertInfoList(pageOderRequest.getOrderRequests(),user_state);

    }
    //查询专家信息列表
    @Override
    public List<Map> findExpertInfoList(Integer user_state) {
        return userMapper.selectExpertInfoList(null,user_state);
    }
    //查询审核项目的第三方机构信息
    @Override
    public Map findProjectAuditOi(Integer ProjectInfoId) {
        return userMapper.selectOieByPpProjectid(ProjectInfoId);
    }
    //查询用户信息列表(专家)
    @Override
    public List<Map> findExperList(Map conditions, PageOderRequest por) {
        PageRequest pageRequest=por.getPageRequest();//分页信息
        OrderRequest[]orderRequests=por.getOrderRequests();
        Integer pageNum=pageRequest.getPageNum();//当前页
        Integer pageSize=pageRequest.getPageSize();//每页长度
        PageHelper.startPage(pageNum, pageSize);//调用分页
        List<Map>listM= userMapper.selectExperList(conditions,orderRequests);//查询
        return listM;
    }

    //重置密码为默认值
    @Override
    public Boolean resetPassword(String userCode) {
        String password="123456";
        String passwordE=MyMD5Util.encrypt(password);
        Long userCodeL=Long.valueOf(userCode);
        if(userCodeL>2147483647L){//如果传入的值大于2147483647L说明不为int类型的自增用户id 则视为传入手机号
            userMapper.updatePasswordByUseruserMobilePhone(userCode,passwordE);//通过用户手机号改变密码
        }else{
            userMapper.updatePasswordByUserId(Integer.parseInt(userCode),passwordE);//通过用户id号改变密码
        }
        return true;
    }
    //重置密码
    @Override
    public Boolean resetPassword(String userCode,String password) {
        return null;
    }


}
