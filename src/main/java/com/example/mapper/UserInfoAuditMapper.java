package com.example.mapper;

import com.example.entity.common.FileInfoeEntity;
import com.example.entity.resultsparam.OrganizationAuditResults;
import com.example.entity.user.OrganizationInfoCareermanEntity;
import com.example.entity.user.OrganizationInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoAuditMapper {
    //根据审核状态查询审核信息

    /**
     * 根据审核状态查询审核信息
     * @param user_info_audit_state
     * @param user_info_audit_type
     * @return
     */
    List<Map<String,Object>> selectUserInfoAuditOrganizationByStateAType(
            @Param("user_info_audit_state") int user_info_audit_state,
            @Param("user_info_audit_type") int user_info_audit_type);

    /**
     * 查询第三方机构完整信息
     * @param organization_info_id
     * @return
     */
    OrganizationInfoEntity selectOrganizationInfoEntityFull(int organization_info_id);

    /**
     * 通过第三方机构id查询第三方机构专业人员信息
     * @param organization_info_id
     * @return
     */
    List<OrganizationInfoCareermanEntity> selectOrganizationInfoCareermanFullByOrgInfoId(
            int organization_info_id);

    /**
     * 通过文件id查询文件信息
     * @param file_info_id
     * @return
     */
    FileInfoeEntity selectFileInfoeEntityFullByFileId(int file_info_id);

}
