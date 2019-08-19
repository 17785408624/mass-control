package com.example.service;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.RegException;
import com.example.entity.user.ExpertInfoEntity;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserEntity;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService {

	/**
	 * y用户注册
	 * @param userEntity
	 * @return
	 * @throws RegException
	 */
	boolean regUser(UserEntity userEntity) throws RegException;//用户注册

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
	 * @param session
	 * @return
	 * @throws LoginException
	 */
	UserEntity userLoginByMobilePhone(String user_mobile_phone,
									  String user_password,
									  HttpSession session) throws LoginException;

	int addExpertInfoAudit (
			ExpertInfoEntity expertInfoEntity,
			int user_id,
			int user_state
	);

	/**
	 * 用户注销登录
	 * @param httpSession
	 * @return
	 */
	boolean userLoginOut(HttpSession httpSession);

}
