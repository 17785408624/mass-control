package com.example.mapper;

import com.example.entity.requstparam.OrderRequest;
import com.example.entity.user.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {
	/**
	 *  新增用户信息
	 * @param userEntity
	 * @return
	 */
	Integer insertUserEntity(UserEntity userEntity);

	/**
	 * 通过用户手机号查找用户信息
	 * @return
	 */
	List<UserEntity> selectUserEntityByMobilePhone(@Param("user_mobile_phone") String user_mobile_phone);

	/**
	 *添加第三方机构信息
	 */
	Integer insertOrganizationInfoEntity(OrganizationInfoEntity organizationInfoEntityrganizationInfoEntity);

	/**
	 * 批量添加机构专业人员
	 * @param oiceList
	 * @return
	 */
	void insertBatchOrganizationInfoCareermanEntity(@Param("oiceList") List<OrganizationInfoCareermanEntity> oiceList,
													   @Param("organization_info_id")int organization_info_id);

	/**
	 * 添加审核信息申请信息
	 * @param userInfoAuditEntity
	 * @return
	 */
	Integer insertUserInfoAuditEntity(UserInfoAuditEntity userInfoAuditEntity);

	/**
	 * 查询用户信息 通过用户手机号
	 * @param user_mobile_phone
	 * @param user_password
	 * @return
	 */
	UserEntity selectUserEntityByMobilePhone_Password(String user_mobile_phone,String user_password);

	/**
	 * 添加专家信息
	 * @param expertInfoEntity
	 * @return
	 */
	Integer insertExpertInfoEntity(ExpertInfoEntity expertInfoEntity);

	/**
	 * 通过用户的信息id修改用户的状态和现存信息
	 * @param info_id 信息id
	 * @param user_state 用户状态
	 * @return
	 */
	Integer updateUserInfoIdAUserStateByinfo_id(
			@Param("info_id") Integer info_id,
			@Param("user_state")Integer user_state
	);

	/**
	 * 通过用户id查询用户信息
	 * @param user_id
	 * @return
	 */
	UserEntity selectUserEntityByUId(Integer user_id);

	/**
	 * 查询第三方机构id和名字信息列表
	 * @param user_state 用户状态  1未认证审核 2已认证审核  3解聘
	 * @return organization_name, 机构名
	 * 	 organization_info_id, 机构id
	 * 	 user_id 用户id
	 */
	List<Map> selectOrganizationInfoNameIdListByState(
			@Param("user_state") Integer user_state);

	/**
	 * 查询用户数量
	 * @param user_role 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
	 * @param user_state 用户状态  1未认证审核 2已认证审核  3解聘
	 * @return
	 */
	Integer selectUserNum(Integer user_role,
									  Integer user_state);

	/**
	 * 范围查询第三方机构信息列表
	 * @param user_state 用户状态  1未认证审核 2已认证审核  3解聘
	 * @param begin 范围开始索引
	 * @param end 结束
	 * @param excludeUids 排除的用户id
	 * @return
	 */
	List<Map>selectOrganizationInfoLimit(@Param("user_state") Integer user_state,
										 @Param("begin")Integer begin,@Param("end")Integer end,
										 @Param("excludeUids")Integer[]excludeUids);

	/**
	 * 查询专家信息列表
	 * @return
	 */
	List<Map>selectExpertInfoList(@Param("orderRequests") OrderRequest[] orderRequest);


}
