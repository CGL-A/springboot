package com.chbtc.springboot.mapper;

import com.chbtc.springboot.model.UserRole;
import com.chbtc.springboot.model.UserRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    @Insert({"insert into user_role (uid, rid)",
            "values (#{uid,jdbcType=INTEGER}, #{rid,jdbcType=INTEGER})"})
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);
}