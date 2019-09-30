package com.example.service;

import com.example.common.exceptiondefine.LoginException;
import com.example.common.exceptiondefine.RegException;
import com.example.entity.requstparam.PageOderRequest;
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
	);

	/**
	 * 用户注销登录
	 * @param httpSession
	 * @return
	 */
	boolean userLoginOut(HttpSession httpSession);

	/**
	 * 查询第三方机构id和名字信息列表
	 * @return
	 */
	List<Map>  findOINameIdListCertified();

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
	List<Map>findExperList(Map conditions,PageOderRequest por);

}
