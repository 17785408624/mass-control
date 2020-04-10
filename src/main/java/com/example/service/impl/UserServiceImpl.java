package com.example.service.impl;

import com.example.api.PhoneMessages;
import com.example.api.RonglianPhoneMessages;
import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.OperationServiceException;
import com.example.common.exceptiondefine.RegException;
import com.example.common.exceptiondefine.SedMessagesException;
import com.example.entity.UserAuthenticate;
import com.example.entity.UserAuthenticateExample;
import com.example.entity.phoneMessages.PhoneMessagesEntity;
import com.example.entity.phoneMessages.PhoneMessagesType;
import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.user.*;
import com.example.mapper.PhoneMessagesMapper;
import com.example.mapper.UserAuthenticateMapper;
import com.example.mapper.UserInfoAuditMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.redis.RedisUtils;
import com.example.service.UserService;
import com.example.service.vice.ExpertInfo;
import com.github.pagehelper.PageHelper;
import com.util.EncryptUtil;
import com.util.MyMD5Util;
import com.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
    RonglianPhoneMessages ronglianPhoneMessages;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    PhoneMessagesMapper phoneMessagesMapper;
    @Autowired
    UserAuthenticateMapper userAuthenticateMapper;
    @Autowired
    PhoneMessages phoneMessages;
    @Autowired
    ExpertInfo expertInfo;

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

    //用户注册
    @Override
    public boolean regUser(UserEntity userEntity, Boolean isboundPhoneNum) throws RegException {
        if (userEntity.getUser_role() == 5) {//不能注册添加超级管理员用户
            throw new RegException("不能注册添加超级管理员用户");
        }
        userEntity.setUser_register_time(new Date().getTime());
        userEntity.setUser_password(MyMD5Util.encrypt(userEntity.getUser_password()));
        List list = userMapper.selectUserEntityByMobilePhone(userEntity.getUser_mobile_phone());
        if (list != null && list.size() > 0) {
            throw new RegException("手机号重复");
        }
        if (isboundPhoneNum) {
            UserAuthenticateExample uae = new UserAuthenticateExample();
            UserAuthenticateExample.Criteria c = uae.createCriteria();
            c.andPhoneNumEqualTo(userEntity.getUser_mobile_phone());
            uae.or(c);
            List<UserAuthenticate> uaList = userAuthenticateMapper.selectByExample(uae);
            if (uaList == null || uaList.size() < 1) {
                throw new RegException("请先绑定手机号码");
            }
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
    public UserEntity userLoginByMobilePhone(String user_mobile_phone, String user_password) throws LoginException {
        user_password = MyMD5Util.encrypt(user_password);
        UserEntity ue = userMapper.selectUserEntityByMobilePhone_Password(user_mobile_phone, user_password);
        if (ue == null) {
            throw new LoginException("账号/密码错误");
        }
        //loginVice.saveLoginInfo(ue,session);//保存用户登录信息
        ue.setUser_password(EncryptUtil.encrypt(ue.getUser_password()));
        ue.setUser_id(Integer.parseInt(EncryptUtil.encrypt(String.valueOf(ue.getUser_id()))));
        return ue;
    }

    /**
     * 重新登录
     *
     * @return
     */
    @Override
    public UserEntity againLoginByMobilePhone(UserEntity ue) {
        //loginVice.updateLoginInfo(ue,session);//更新用户登录信息
        ue.setUser_password(EncryptUtil.encrypt(ue.getUser_password()));
        ue.setUser_id(Integer.parseInt(EncryptUtil.encrypt(String.valueOf(ue.getUser_id()))));
        return ue;
    }

    //添加专家审核信息申请
    @Override
    public int addExpertInfoAudit(ExpertInfoEntity expertInfoEntity, int user_id, int user_state) throws OperationServiceException {

        Integer num = userInfoAuditMapper.selectUiaByUid(user_id, 2);//查询用户未审核的信息
        if (num > 0) {
            throw new OperationServiceException("已提交过审核申请，请等待后台审核");
        }
        userMapper.insertExpertInfoEntity(expertInfoEntity);
        UserInfoAuditEntity userInfoAuditEntity = new UserInfoAuditEntity();//用户信息审核类
        userInfoAuditEntity.setUser_id_add(user_id);//申请人id
        userInfoAuditEntity.setUser_info_audit_addtime(new Date().getTime());//添加时间
        userInfoAuditEntity.setInfo_id(expertInfoEntity.getExpert_info_id());//审核的资料信息id
        userInfoAuditEntity.setUser_info_audit_content(2);//审核内容为专家信息
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
    public boolean userLoginOut() {
        //loginVice.cleanLoginInfo(httpSession);//清空登录信息
        return true;
    }

    //查询已审核认证的第三方机构id和名字信息列表
    @Override
    public List<Map> findOINameIdListCertified() {
        return userMapper.selectOrganizationInfoNameIdListByState(2);
    }

    //查询第三方机构信息列表
    @Override
    public List<Map> findOINameIdListCertified(PageOderRequestMap pageOderRequestMap) {
        int pageNum = pageOderRequestMap.getPageRequest().getPageNum();//当前页码
        int pageSize = pageOderRequestMap.getPageRequest().getPageSize();//每页数量
        OrderRequest[] orderRequest = pageOderRequestMap.getOrderRequests();
        if (orderRequest.length < 1) {
            orderRequest = null;
        }
        PageHelper.startPage(pageNum, pageSize);//调用分页
        String SearchField = pageOderRequestMap.getParam().get("SearchField").toString();//搜索字段
        return userMapper.selectOrganizationListByState(2, orderRequest, SearchField);
    }

    //分页查询专家信息列表
    @Override
    public List<Map> findExpertInfoListPage(PageOderRequest pageOderRequest, Integer user_state) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();
        int pageNum = pageRequest.getPageNum();//当前页面
        int pageSize = pageRequest.getPageSize();//每页长度
        PageHelper.startPage(pageNum, pageSize);//调用分页
        return userMapper.selectExpertInfoList(pageOderRequest.getOrderRequests(), user_state);

    }

    //查询专家信息列表
    @Override
    public List<Map> findExpertInfoList(Integer user_state) {
        return userMapper.selectExpertInfoList(null, user_state);
    }

    //查询审核项目的第三方机构信息
    @Override
    public Map findProjectAuditOi(Integer ProjectInfoId) {
        return userMapper.selectOieByPpProjectid(ProjectInfoId);
    }

    //查询用户信息列表(专家)
    @Override
    public List<Map> findExperList(String conditions, PageOderRequest por) {
        PageRequest pageRequest = por.getPageRequest();//分页信息
        OrderRequest[] orderRequests = por.getOrderRequests();
        Integer pageNum = pageRequest.getPageNum();//当前页
        Integer pageSize = pageRequest.getPageSize();//每页长度
        PageHelper.startPage(pageNum, pageSize);//调用分页
        List<Map> listM = userMapper.selectExperList(conditions, orderRequests);//查询
        return listM;
    }

    @Override
    public List<Map> findOrganizationList(String conditions, PageOderRequest por) {

        return null;
    }

    //重置密码为默认值
    @Override
    public Boolean resetPassword(String userCode) {
        String password = "123456";
        String passwordE = MyMD5Util.encrypt(password);
        Long userCodeL = Long.valueOf(userCode);
        if (userCodeL > 2147483647L) {//如果传入的值大于2147483647L说明不为int类型的自增用户id 则视为传入手机号
            userMapper.updatePasswordByUseruserMobilePhone(userCode, passwordE);//通过用户手机号改变密码
        } else {
            userMapper.updatePasswordByUserId(Integer.parseInt(userCode), passwordE);//通过用户id号改变密码
        }
        return true;
    }

    //重置密码
    @Override
    public Boolean resetPassword(String userCode, String password) {
        return null;
    }

    //用户获取绑定手机的验证码
    @Override
    public Boolean getPhoneBoundCod(String phoneNum) throws SedMessagesException {
        Integer codeValidity = 60 * 10;//验证码有效时间（秒）
        String chValidity = codeValidity / 60 + "分钟";//有效时间文字词（分钟）
        PhoneMessagesEntity pme = new PhoneMessagesEntity();
        pme.setPhoneNum(phoneNum);//接收的手机号码
        pme.setPhoneMessagesType(PhoneMessagesType.messagesBoundCod);//短信类型
        String codeNum = PublicUtil.getRandomNum(6);//短信随机验证码
        pme.setSendData(new String[]{codeNum, chValidity});//自定义的发送信息
        Map result = null;//发送短信后的返回结果
        try {
            result = phoneMessages.sedMessages(pme);//发送短信
        } catch (SedMessagesException e) {
            throw new SedMessagesException("错误码=" + result.get("statusCode") + " 错误信息：" + result.get("statusMsg"));

        }
        String templateContent = result.get("templateContent").toString();//短信完整内容
        Integer sendState;//发送状态 1成功 0失败
        redisUtils.set(pme.getPhoneNum() + pme.getPhoneMessagesType().getMessagesId(),
                codeNum, codeValidity);//随机验证码放入redis缓存
        phoneMessagesMapper.insertPhoneMessages(pme);//将发送信息写入数据库
        return true;
    }

    //绑定手机号码
    @Override
    public Boolean boundPhone(String phoneNum, String phoneBoundCod, int user_id) {
        Object phoneBoundCodc = redisUtils.
                get(phoneNum + PhoneMessagesType.messagesBoundCod.getMessagesId());//存入redis中正确的验证码
        if (phoneBoundCodc == null) {//缓存中不存在
            return false;
        }
        if (!phoneBoundCod.equals(phoneBoundCodc)) {//验证码错误
            return false;
        }
        UserAuthenticate ua = new UserAuthenticate();
        ua.setAuthenticateDate(Instant.now().getEpochSecond() * 1000);//验证时间
        ua.setPhoneNum(phoneNum);//手机号码
        ua.setUserId(user_id);//用户id
        userAuthenticateMapper.insert(ua);//绑定信息写入数据库
        return true;
    }

    //解聘用户
    @Override
    public Boolean dismissUser(Integer[] uIds) {
        Integer userState = 3;//状态 1未认证审核 2已认证审核  3解聘
        userMapper.updateUserState(uIds, userState);//改变用户状态
        return true;
    }

    //返聘用户
    @Override
    public Boolean rehireUser(Integer[] uIds) {
        Integer userState = 2;//状态 1未认证审核 2已认证审核  3解聘
        userMapper.updateUserState(uIds, userState);//改变用户状态
        return true;
    }

    //查询专家用户信息
    @Override
    public List findExperList(PageOderRequest pageOderRequest, Object[] userStates, String condition) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();//分页信息
        OrderRequest[] orderRequests = pageOderRequest.getOrderRequests();//排序参数
        Integer pageNum = pageRequest.getPageNum();//当前页
        Integer pageSize = pageRequest.getPageSize();//每页长度
        PageHelper.startPage(pageNum, pageSize);//调用分页
        List l = userMapper.selectExpersByState(userStates, orderRequests, condition);//查询专家用户信息
        return l;
    }

    //查询第三方机构用户信息
    @Override
    public List findOrganizationList(PageOderRequest pageOderRequest, Object[] userStates, String condition) {
        PageRequest pageRequest = pageOderRequest.getPageRequest();//分页信息
        OrderRequest[] orderRequests = pageOderRequest.getOrderRequests();//排序参数
        Integer pageNum = pageRequest.getPageNum();//当前页
        Integer pageSize = pageRequest.getPageSize();//每页长度
        PageHelper.startPage(pageNum, pageSize);//调用分页
        List list = userMapper.selectOrganizationByState(userStates, orderRequests, condition);//查询专家用户信息
        return list;
    }

    //查询所有的专家公司名
    @Override
    public String[] getExpertCompanyname(Boolean repetition) {

        String[] companynames = userMapper.selectExpertExpertCompanyname(repetition);
        return companynames;
    }

    //查询所学专业从事专业的总人数
    @Override
    public List findExpertMajorSum(Object[] types, Object[] majors, Object[] expertInfoEducations, Object[] excludeCompanyNames) {
        if (types == null || types.length < 1) {
            types = new Object[]{"expert_info_learnmajor", "expert_info_workmajor"};
        }
        if (majors == null || majors.length < 1) {
            majors = new Object[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        }
        return userMapper.selectExpertMajorSum(types, majors, expertInfoEducations, excludeCompanyNames);
    }

    //查询各个申报专业的总人数
    @Override
    public List findExpertdeclaredesignSum(Object[] types, Object[] declaredesigns, Object[] expertInfoEducations, Object[] excludeCompanyNames) {
        if (types == null || types.length < 1) {
            types = new Object[]{"expert_info_declaredesign_design", "expert_info_declaredesign_safety"};
        }
        if (declaredesigns == null || declaredesigns.length < 1) {
            declaredesigns = new Object[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        }
        if (expertInfoEducations != null && expertInfoEducations.length < 1) {
            expertInfoEducations = null;
        }
        if (excludeCompanyNames != null && excludeCompanyNames.length < 1) {
            excludeCompanyNames = null;
        }
        return userMapper.selectExpertdeclaredesignSum(types, declaredesigns, expertInfoEducations, excludeCompanyNames);
    }

    //合并名字相似的专家公司信息
    @Override
    public void mergeSimilarityExpertCompany(String saveType, boolean isRenewal) {
        List companyList;//专家公司信息
        if (isRenewal) {//是否更新现在保存的公司名合并信息
            userMapper.deleteData();
            companyList = userMapper.selectExpertCompany(false);
        } else {
            companyList = userMapper.selectExpertCompany(true);
        }
        List companyMergeList = expertInfo.mergeExpertCompany(companyList);//合并专家信息中公司名相似的信息
        switch (saveType) {//保存类型
            case "database"://数据库
                userMapper.insertMergeSimilarityCompany(companyMergeList);
                break;
            case "redis"://redis
                break;
        }
    }

    //查询合并后专家信息的公司名
    public String[] findSimilarityExpertCompany() {
        return userMapper.selectSecDistinct();
    }


}
