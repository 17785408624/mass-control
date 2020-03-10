package com.example.mapper;

import com.example.entity.UserAuthenticate;
import com.example.entity.UserAuthenticateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticateMapper {
    /**
     *
     *  2020-01-10
     */
    int deleteByExample(UserAuthenticateExample example);

    /**
     *
     *  2020-01-10
     */
    int deleteByPrimaryKey(Integer userAuthenticatePhone);

    /**
     *
     *  2020-01-10
     */
    int insert(UserAuthenticate record);

    /**
     *
     *  2020-01-10
     */
    int insertSelective(UserAuthenticate record);

    /**
     *
     *  2020-01-10
     */
    List<UserAuthenticate> selectByExample(UserAuthenticateExample example);

    /**
     *
     *  2020-01-10
     */
    UserAuthenticate selectByPrimaryKey(Integer userAuthenticatePhone);

    /**
     *
     *  2020-01-10
     */
    int updateByExampleSelective(@Param("record") UserAuthenticate record, @Param("example") UserAuthenticateExample example);

    /**
     *
     *  2020-01-10
     */
    int updateByExample(@Param("record") UserAuthenticate record, @Param("example") UserAuthenticateExample example);

    /**
     *
     *  2020-01-10
     */
    int updateByPrimaryKeySelective(UserAuthenticate record);

    /**
     *
     *  2020-01-10
     */
    int updateByPrimaryKey(UserAuthenticate record);
}