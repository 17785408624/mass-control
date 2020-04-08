package com.example.service;

import com.example.common.exceptiondefine.*;
import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.user.ExpertInfoEntity;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserEntity;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public interface UserService {

	/**
	 * y用户注册
	 * @param userEntity
	 * @return
	 * @throws RegException
	 */
	boolean regUser(UserEntity userEntity) throws RegException;//用户注册

	/**
	 *
	 * @param userEntity 用户信息
	 * @param isboundPhoneNum 是否绑定手机号码，ture需要绑定
	 * @return
	 * @throws RegException
	 */
	boolean regUser(UserEntity userEntity,Boolean isboundPhoneNum) throws RegException;//用户注册

	/**
	 * 添加第三方机构信息审核
	 * @param organizationInfoEntity 第三方机构信息
	 * @param oceList 第三方机构信息   人员信息
	 * @param user_id 申请者用户id
	 * @param user_state 申请者用户状态
	 * @return 审核表id
	 */
	int addOrganizationInfoAudit(OrganizationInfoEntity organizationInfoEntity,
								 List<OrganizationInfoCareermanEntity> oceList,
								 int user_id,
								 int user_state
	);//添加第三方机构信息审核

	/**
	 *  用户通过手机号登录
	 * @param user_mobile_phone
	 * @param user_password
	 * @return
	 * @throws LoginException
	 */
	UserEntity userLoginByMobilePhone(String user_mobile_phone,
									  String user_password) throws LoginException;

	/**
	 * 用户添加专家审核信息
	 * @param expertInfoEntity
	 * @param user_id
	 * @param user_state
	 * @return
	 */
	int addExpertInfoAudit (
			ExpertInfoEntity expertInfoEntity,
			int user_id,
			int user_state
	) throws OperationServiceException;



	/**
	 * 用户注销登录
	 * @return
	 */
	boolean userLoginOut();

	/**
	 * 查询第三方机构id和名字信息列表
	 * @return
	 */
	List<Map>  findOINameIdListCertified();
	/**
	 * 分页查询第三方机构信息列表
	 * @return
	 */
	List<Map>  findOINameIdListCertified(PageOderRequestMap pageOderRequestMap);

	/**
	 *
	 */
	/**
	 * 分页查询专家信息列表
	 * @param pageOderRequest 分页排序信息
	 * @param user_state 用户状态 1未认证 2已审核
	 * @return
	 */
	List<Map>findExpertInfoListPage(PageOderRequest pageOderRequest,Integer user_state);

	/**
	 * 查询专家信息列表
	 * @return
	 */
	List<Map>findExpertInfoList(Integer user_state);

	/**
	 * 查询审核项目的第三方机构信息
	 * @param ProjectInfoId 项目信息id
	 * @return
	 */
	Map findProjectAuditOi(Integer ProjectInfoId);

	/**
	 * 查询用户信息列表(专家)
	 * @param conditions 查询条件
	 * @return
	 */
	List<Map>findExperList(String conditions,PageOderRequest por);

	/**
	 * 查询用户信息列表(第三方机构)
	 * @param conditions
	 * @param por
	 * @return
	 */
	List<Map>findOrganizationList(String conditions,PageOderRequest por);

	/**
	 * 用户重新登录
	 * @param ue
	 * @return
	 */
	UserEntity againLoginByMobilePhone(UserEntity ue);
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

	/**
	 * 用户获取绑定手机号验证码
	 * @param phoneNum 手机号
	 * @return
	 * @throws SedMessagesException
	 */
	Boolean getPhoneBoundCod(String phoneNum) throws SedMessagesException;

	/**
	 * 用户绑定手机号
	 * @param phoneNum
	 * @param phoneBoundCod
	 * @return
	 */
	Boolean boundPhone(String phoneNum,String phoneBoundCod,int user_id);

	/**
	 * 解聘用户
	 * @param uIds 用户id
	 * @return
	 */
	Boolean dismissUser(Integer[]uIds);

	/**
	 *返聘用户
	 * @param uIds
	 * @return
	 */
	Boolean rehireUser(Integer[]uIds);

	/**
	 * 查询专家用户信息
	 * @param pageOderRequest 分页和排序参数
	 * @param userStates 需要查询的用户状态 1未认证审核 2已认证审核  3解聘
	 * @param condition 查询关键字
	 * @return
	 */
	List findExperList(PageOderRequest pageOderRequest,Object[] userStates,String condition);

	/**
	 * 查询第三方机构用户信息
	 * @param pageOderRequest 分页和排序参数
	 * @param userStates 需要查询的用户状态 1未认证审核 2已认证审核  3解聘
	 * @param condition 查询关键字
	 * @return
	 */
	List findOrganizationList(PageOderRequest pageOderRequest,Object[] userStates,String condition);

	/**
	 * 查询所有专家的公司名
	 * @param repetition 是否显示多个重复公司名
	 * @return
	 */
	String[] getExpertCompanyname(Boolean repetition);

	/**
	 * 查询所学专业从事专业的总人数
	 * @param types expert_info_learnmajor 所学专业 expert_info_workmajor 从事专业，多个已or进行链接
	 *              为null则视为查询全部
	 * @param majors 专业code  1采矿，2露采，3选煤，4矿山机电，5机械，6电气-供配电，7电气-自动控制，8电气-通信，9电气-信号，10建筑，11结构，
	 *                 12给排水，13暖通空调，14环保，15总图，16运输 为null则视为查询全部
	 * @param expert_info_educations 学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
	 * @param excludeCompanyNames 回避的公司名字
	 * @return
	 */
	List findExpertMajorSum(Object[] types,Object[] majors,Object[]expert_info_educations,Object[]excludeCompanyNames);

	/**
	 * 查询各个申报专业的总人数
	 * @param types expert_info_declaredesign_design 技术报告咨询审查类 ,expert_info_declaredesign_safety  安全生产检查类
	 *              为null则视为查询全部
	 * @param declaredesigns 1采矿工程2通风安全3供电4四大件5水文地质6总平面工程7造价8环保节能9土建工程
	 *                       为null则视为查询全部
	 * @param expert_info_educations 学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
	 * @param excludeCompanyNames 回避的公司名字
	 * @return
	 */
	List findExpertdeclaredesignSum(Object[] types,Object[] declaredesigns,Object[]expert_info_educations,Object[]excludeCompanyNames);
    /**
	 * 合并名字相似的专家公司信息
	 * @param saveType 保持类型
	 * @param isRenewal 是否清除现有保存的相似公司名数据重新保存
	 */
    void mergeSimilarityExpertCompany(String saveType,boolean isRenewal);

	/**
	 * 查询相似公司名称合并后的专家信息的公司名
	 * @return
	 */
	String[] findSimilarityExpertCompany();




}
