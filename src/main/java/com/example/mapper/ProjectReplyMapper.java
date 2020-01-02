package com.example.mapper;

import com.example.entity.ProjectReply;
import com.example.entity.ProjectReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectReplyMapper {
    /**
     *
     *  2019-09-26
     */
    int deleteByExample(ProjectReplyExample example);

    /**
     *
     *  2019-09-26
     */
    int insert(ProjectReply record);

    /**
     *
     *  2019-09-26
     */
    int insertSelective(ProjectReply record);

    /**
     *
     *  2019-09-26
     */
    List<ProjectReply> selectByExample(ProjectReplyExample example);

    /**
     *
     *  2019-09-26
     */
    int updateByExampleSelective(@Param("record") ProjectReply record, @Param("example") ProjectReplyExample example);

    /**
     *
     *  2019-09-26
     */
    int updateByExample(@Param("record") ProjectReply record, @Param("example") ProjectReplyExample example);
    ProjectReply selectPrFullByPiid(Integer Piid);
}