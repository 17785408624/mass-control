package com.example.service;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.RegException;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserEntity;

import javax.servlet.http.HttpSession;


public interface UserService {


	boolean regUser(UserEntity userEntity) throws RegException;//用户注册

	/**
	 * 添加第三方机构信息审核
	 * @param organizationInfoEntity 第三方机构信息
	 * @param user_id 申请者用户id
	 * @param user_state 申请者用户状态
	 * @return 审核表id
	 */
	int addOrganizationInfoAudit(OrganizationInfoEntity organizationInfoEntity,
								 int user_id,
								 int user_state
	);//添加第三方机构信息审核

	UserEntity userLoginByMobilePhone(String user_mobile_phone,
									  String user_password,
									  HttpSession session) throws LoginException;

}
