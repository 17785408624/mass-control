package com.example.service;

import com.example.common.exceptiondefine.*;
import com.example.entity.requstparam.PageOderRequest;
import com.example.entity.requstparam.PageOderRequestMap;
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
	 *
	 * @param uIds
	 * @return
	 */
	Boolean rehireUser(Integer[]uIds);

}
