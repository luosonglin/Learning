package com.love.lavender.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by luosonglin on 24/03/2018.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("insert into user(id, name) values(#{id}, #{name})")
    int insert(@Param("id") long id, @Param("name") String name);

}
