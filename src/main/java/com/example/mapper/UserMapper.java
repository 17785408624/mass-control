package com.example.mapper;

import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import com.example.entity.user.UserEntity;
import com.example.entity.user.UserInfoAuditEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	Integer insertBatchOrganizationInfoCareermanEntity(List<OrganizationInfoCareermanEntity> oiceList);

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



}
