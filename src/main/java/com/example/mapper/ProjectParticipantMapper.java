package com.example.mapper;

import com.example.entity.ProjectParticipant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectParticipantMapper {
        /**
         *
         *  2019-08-29
         */
        int insert(ProjectParticipant record);

        /**
         *
         *  2019-08-29
         */
        int insertSelective(ProjectParticipant record);


        int insertBatch(ProjectParticipant[]projectParticipants);

        /**
         * 通过用户id和用户角色查询参与的项目id
         * @return
         */
        Integer[] selectPIidByUidUrole(@Param("user_id")int user_id,@Param("user_role") int user_role);
        /**
         * 通过项目id查询项目参与者用户信息 用户角色或者userAudit为空则视为不设置条件
         * @param projectInfoId 项目id
         * @param userRoles 用户角色 1专家 2第三方机构 3煤监局 4能源局 5超级管理员 6组长
         * @param userAudit 用户是否对项目进行过审核(1未进行过审核，2进行过审核)
         * @return
         */
        List<ProjectParticipant> selectPpByPiid(Integer projectInfoId, Integer[]userRoles,Integer userAudit);

        /**
         * 修改项目参与者表 用户是否对项目进行过审核
         * @param projectInfoId 项目id
         * @param userId 用户id
         * @param userAudit 用户是否对项目进行过审核(1未进行过审核，2进行过审核)
         * @return
         */
        int updateUaByUidPid(@Param("projectInfoId") Integer projectInfoId,
                             @Param("userId") Integer userId,
                             @Param("userAudit") Integer userAudit);


        }