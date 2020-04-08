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
     * 新增用户信息
     *
     * @param userEntity
     * @return
     */
    Integer insertUserEntity(UserEntity userEntity);

    /**
     * 通过用户手机号查找用户信息
     *
     * @return
     */
    List<UserEntity> selectUserEntityByMobilePhone(@Param("user_mobile_phone") String user_mobile_phone);

    /**
     * 添加第三方机构信息
     */
    Integer insertOrganizationInfoEntity(OrganizationInfoEntity organizationInfoEntityrganizationInfoEntity);

    /**
     * 批量添加机构专业人员
     *
     * @param oiceList
     * @return
     */
    void insertBatchOrganizationInfoCareermanEntity(@Param("oiceList") List<OrganizationInfoCareermanEntity> oiceList,
                                                    @Param("organization_info_id") int organization_info_id);

    /**
     * 添加审核信息申请信息
     *
     * @param userInfoAuditEntity
     * @return
     */
    Integer insertUserInfoAuditEntity(UserInfoAuditEntity userInfoAuditEntity);

    /**
     * 查询用户信息 通过用户手机号
     *
     * @param user_mobile_phone
     * @param user_password
     * @return
     */
    UserEntity selectUserEntityByMobilePhone_Password(String user_mobile_phone, String user_password);

    /**
     * 添加专家信息
     *
     * @param expertInfoEntity
     * @return
     */
    Integer insertExpertInfoEntity(ExpertInfoEntity expertInfoEntity);

    /**
     * 通过用户的信息id修改用户的状态和现存信息
     *
     * @param info_id    信息id
     * @param user_state 用户状态 为0则不做修改   1未认证审核 2已认证审核  3解聘
     * @return
     */
    Integer updateUserInfoIdAUserStateByinfo_id(
            @Param("info_id") Integer info_id,
            @Param("user_state") Integer user_state);

    /**
     * 通过用户id查询用户信息
     *
     * @param user_id
     * @return
     */
    UserEntity selectUserEntityByUId(Integer user_id);

    /**
     * 查询第三方机构id和名字信息列表
     *
     * @param user_state 用户状态  1未认证审核 2已认证审核  3解聘
     * @return organization_name, 机构名
     * organization_info_id, 机构id
     * user_id 用户id
     */
    List<Map> selectOrganizationInfoNameIdListByState(
            @Param("user_state") Integer user_state);

    /**
     * 查询第三方机构信息列表
     *
     * @param user_state    用户状态  1未认证审核 2已认证审核  3解聘
     * @param orderRequests 排序参数
     * @param searchField   搜索字段
     * @return
     */
    List<Map> selectOrganizationListByState(
            @Param("user_state") Integer user_state, @Param("orderRequests") OrderRequest[] orderRequests, @Param("searchField") String searchField);


    /**
     * 查询用户数量
     *
     * @param user_role  用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     * @param user_state 用户状态  1未认证审核 2已认证审核  3解聘
     * @return
     */
    Integer selectUserNum(Integer user_role,
                          Integer user_state);

    /**
     * 范围查询第三方机构信息列表
     *
     * @param user_state  用户状态  1未认证审核 2已认证审核  3解聘
     * @param begin       范围开始索引
     * @param end         结束
     * @param excludeUids 排除的用户id
     * @return
     */
    List<Map> selectOrganizationInfoLimit(@Param("user_state") Integer user_state,
                                          @Param("begin") Integer begin, @Param("end") Integer end,
                                          @Param("excludeUids") Integer[] excludeUids);


    /**
     * 查询专家信息列表
     *
     * @param orderRequest
     * @param user_state   用户状态是否审核 1未认证审核 2已认证审核  3解聘
     * @return
     */
    List<Map> selectExpertInfoList(@Param("orderRequests") OrderRequest[] orderRequest, @Param("user_state") Integer user_state);

    /**
     * 改变用户状态
     *
     * @param uIds      用户id
     * @param userState 状态 1未认证审核 2已认证审核  3解聘
     * @return
     */
    int updateUserState(@Param("uIds") Integer[] uIds, @Param("userState") Integer userState);

    /**
     * 通过用户角色和状态查询用户Id
     *
     * @param user_role              用户角色  1专家 2第三方机构 3煤监局 4能源局 5超级管理员
     * @param user_state             用户状态  1未认证审核 2已认证审核  3解聘
     * @param expert_info_educations 专家学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
     * @param expert_info_workmajors 专家从事专业  1采矿工程2通风安全3供电4四大件5水文地质6总平面工程7造价8环保节能9土建工程
     * @return
     */
    Integer[] selectUserIdArrayByUrUs(@Param("user_role") Integer user_role,
                                      @Param("user_state") Integer user_state,
                                      @Param("expert_info_educations") Integer[] expert_info_educations,
                                      @Param("expert_info_workmajors") Integer[] expert_info_workmajors);

    //查询专家id和名字信息列表
    List<Map> selectExpertInfoListById(@Param("Uids") Integer[] Uids);

    /**
     * 通过参与项目id查询第三方机构信息
     *
     * @param ProjectInfoId 参与项目id
     * @return
     */
    Map selectOieByPpProjectid(Integer ProjectInfoId);

    /**
     * 查询用户列表(专家)
     *
     * @param conditions    查询条件
     * @param orderRequests 排序规则
     * @return
     */
    List<Map> selectExperList(@Param("conditions") String conditions,
                              @Param("orderRequests") OrderRequest[] orderRequests);

    /**
     * 查询用户列表(第三方机构)
     *
     * @param conditions
     * @param orderRequests
     * @return
     */
    List<Map> selectOrganizationList(@Param("conditions") String conditions,
                                     @Param("orderRequests") OrderRequest[] orderRequests);

    /**
     * 通过用户id查询用户专家信息
     *
     * @param Uid 用户id
     * @return
     */
    ExpertInfoEntity selectEiByUid(Integer Uid);

    /**
     * 通过用户id修改密码
     *
     * @param userId   用户手机号或者是id
     * @param password 用户密码
     * @return
     */
    int updatePasswordByUserId(@Param("userId") Integer userId, @Param("password") String password);

    /**
     * 通过用户手机号码修改密码
     *
     * @param userId   用户手机号或者是id
     * @param password 用户密码
     * @return
     */
    int updatePasswordByUseruserMobilePhone(@Param("userMobilePhone") String userId, @Param("password") String password);

    /**
     * 物理删除用户数据，删除后不可恢复
     *
     * @param Uid 用户id
     * @return
     */
    int deleteUserByUid(@Param("Uid") Integer[] Uid);

    /**
     * 通过专家用户状态查询用户信息
     * @param userStates 用户状态 1未认证审核 2已认证审核  3解聘 多个以or进行链接 为null则视为不添加条件
     * @param orderRequests 排序参数
     * @param condition 查询搜索条件
     * @return
     */
    List selectExpersByState(@Param("userStates") Object[] userStates,
                               @Param("orderRequests") OrderRequest[] orderRequests,
                               @Param("condition") String condition);

    /**
     * 通过第三方机构用户用户状态查询用户信息
     * @param userStates 用户状态 1未认证审核 2已认证审核  3解聘 多个以or进行链接 为null则视为不添加条件
     * @param orderRequests 排序参数
     * @param condition 查询搜索条件
     * @return
     */
    List selectOrganizationByState(@Param("userStates") Object[] userStates,
                             @Param("orderRequests") OrderRequest[] orderRequests,
                             @Param("condition") String condition);

    /**
     *  查询用户信息列表
     * @param queryFields 需要查询的字段
     * @param userStates 用户状态 1未认证审核 2已认证审核  3解聘 多个以or进行链接 为null则视为不添加条件
     * @return
     */
    List selectExperInfoByQueryFields(@Param("queryFields") String[]queryFields,@Param("userStates") Object[]userStates);
    /**
     *  查询第三方机构信息列表
     * @param queryFields 需要查询的字段
     * @param userStates 用户状态 1未认证审核 2已认证审核  3解聘 多个以or进行链接 为null则视为不添加条件
     * @return
     */
    List selectOrganizationByQueryFields(@Param("queryFields") String[]queryFields,@Param("userStates") Object[]userStates);

    /**
     * 查询所有专家的公司名
     * @param repetition 是否显示多个重复公司名
     * @return
     */
    String[] selectExpertExpertCompanyname(@Param("repetition") Boolean repetition);

    /**
     * 查询各个所学专业从事专业的总人数
     * @param types expert_info_learnmajor所学专业 expert_info_workmajor从事专业，多个已or进行链接
     * @param majors 专业code  1采矿，2露采，3选煤，4矿山机电，5机械，6电气-供配电，7电气-自动控制，8电气-通信，9电气-信号，10建筑，11结构，
     *                12给排水，13暖通空调，14环保，15总图，16运输
     * @return
     */
    List selectExpertMajorSum(@Param("types") Object[] types,@Param("majors")Object[] majors, @Param("expertInfoEducations") Object[]expertInfoEducations,@Param("excludeCompanyNames")Object[]excludeCompanyNames);

    /**
     * 查询各个申报专业的总人数
     * @param types expert_info_declaredesign_design 技术报告咨询审查类 ,expert_info_declaredesign_safety  安全生产检查类
     * @param declaredesigns 1采矿工程2通风安全3供电4四大件5水文地质6总平面工程7造价8环保节能9土建工程
     * @param expertInfoEducations 学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
     * @param excludeCompanyNames 回避的公司名字
     * @return
     */
    List selectExpertdeclaredesignSum(@Param("types") Object[] types,@Param("declaredesigns")Object[] declaredesigns,
                                      @Param("expertInfoEducations") Object[]expertInfoEducations,@Param("excludeCompanyNames")Object[]excludeCompanyNames);

    /**
     * 专业作为条件进行专家表分组查询
     * @param domain
     *  declaredesign申报专业技术报告咨询审查类和申报专业安全生产检查类
     * declaredesign_design 申报专业技术报告咨询审查类 declaredesign_safety  申报专业安全生产检查类
     * major 所学专业和从事专业
     *learnmajor所学专业 workmajor从事专业
     * 申报专业不能和所学专业与从事专业进行同时分组。
     * @param expert_info_educations 学历 1小学 2初中 3高中 4大学 5硕士 6博士 7更多
     * @param excludeCompanyNames 排除查询数据的公司名
     * @return
     */
    List selectExpertGroupDomain(@Param("domain")String domain,@Param("expert_info_educations") Object[] expert_info_educations,
                                 @Param("excludeCompanyNames")Object[] excludeCompanyNames);

    /**
     * 查询专家数据中的公司信息
     * @param containMec 是否包含查询保存的相似公司数据
     * @return
     */
    List selectExpertCompany(@Param("containMec") boolean containMec);

    /**
     * 添加专家公司合并信息数据
     * @param companyMergeList 合并的专家信息集合 mergeExpertName 合并后的名字
     *mergeExpertIds 合并的专家信息id
     */
    void insertMergeSimilarityCompanyStr(@Param("companyMergeList") List companyMergeList);
    /**
     * 添加专家公司合并信息数据
     * @param companyMergeList 合并的专家信息集合 mergeExpertName 合并后的名字
     *mergeExpertIds 合并的专家信息id
     */
    void insertMergeSimilarityCompany(@Param("companyMergeList") List companyMergeList);

    /**
     *查询合并后的公司名字,重复值只显示一条数据
     */
    String[] selectSecDistinct();

    /**
     * 清除专家公司名合并信息数据
     */
    void deleteData();

}
