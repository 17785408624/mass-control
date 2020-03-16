package com.example.mapper;

import com.example.entity.requstparam.OrderRequest;
import com.example.entity.requstparam.PageRequest;
import com.example.entity.user.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

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
	 * @param user_state 用户状态 为0则不做修改   1未认证审核 2已认证审核  3解聘
	 * @return
	 */
	Integer updateUserInfoIdAUserStateByinfo_id(
			@Param("info_id") Integer info_id,
			@Param("user_state")Integer user_state);

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
	 * 查询第三方机构信息列表
	 * @param user_state  用户状态  1未认证审核 2已认证审核  3解聘
	 * @param orderRequests 排序参数
	 * @param searchField  搜索字段
	 * @return
	 */
	List<Map> selectOrganizationListByState(
			@Param("user_state") Integer user_state, @Param("orderRequests")OrderRequest[] orderRequests,@Param("searchField")String searchField);


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
	 * @param orderRequest
	 * @param user_state 用户状态是否审核 1未认证审核 2已认证审核  3解聘
	 * @return
	 */
	List<Map>selectExpertInfoList(@Param("orderRequests") OrderRequest[] orderRequest,@Param("user_state") Integer user_state);

	/**
	 * 改变用户状态
	 * @param uIds 用户id
	 * @param userState 状态 1未认证审核 2已认证审核  3解聘
	 * @return
	 */
	int updateUserState(@Param("uIds") Integer[]uIds,@Param("userState") Integer userState);

	/**
	 * 通过用户角色和状态查询用户Id
	 * @param user_role 用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
	 * @param user_state 用户状态  1未认证审核 2已认证审核  3解聘
	 * @param expert_info_educations 专家学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
	 * @param expert_info_workmajors 专家从事专业  1采矿工程2通风安全3供电4四大件5水文地质6总平面工程7造价8环保节能9土建工程
	 * @return
	 */
	Integer[] selectUserIdArrayByUrUs(@Param("user_role") Integer user_role,
									  @Param("user_state") Integer user_state,
									  @Param("expert_info_educations") Integer[]expert_info_educations,
									  @Param("expert_info_workmajors") Integer[]expert_info_workmajors);

	//查询专家id和名字信息列表
	List<Map> selectExpertInfoListById(@Param("Uids") Integer[]Uids);

	/**
	 * 通过参与项目id查询第三方机构信息
	 * @param ProjectInfoId 参与项目id
	 * @return
	 */
	Map selectOieByPpProjectid(Integer ProjectInfoId);

	/**
	 * 查询用户列表(专家)
	 * @param conditions 查询条件
	 * @param orderRequests 排序规则
	 * @return
	 */
	List<Map>selectExperList(@Param("conditions") String conditions,
							 @Param("orderRequests") OrderRequest[]orderRequests);

	/**
	 * 查询用户列表(第三方机构)
	 * @param conditions
	 * @param orderRequests
	 * @return
	 */
	List<Map>selectOrganizationList(@Param("conditions") String conditions,
							 @Param("orderRequests") OrderRequest[]orderRequests);

	/**
	 * 通过用户id查询用户专家信息
	 * @param Uid 用户id
	 * @return
	 */
	ExpertInfoEntity selectEiByUid(Integer Uid);

	/**
	 * 通过用户id修改密码
	 * @param userId 用户手机号或者是id
	 * @param password 用户密码
	 * @return
	 */
	int updatePasswordByUserId(@Param("userId")Integer  userId,@Param("password") String password);
	/**
	 * 通过用户手机号码修改密码
	 * @param userId 用户手机号或者是id
	 * @param password 用户密码
	 * @return
	 */
	int updatePasswordByUseruserMobilePhone(@Param("userMobilePhone")String userId,@Param("password") String password);

	/**
	 * 物理删除用户数据，删除后不可恢复
	 * @param Uid 用户id
	 * @return
	 */
	int deleteUserByUid(@Param("Uid") Integer[]Uid);



}
