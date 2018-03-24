package com.love.lavender.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 24/03/2018.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    @Select("select id, name from user")
    List<User> findAll();

    @Insert("insert into user(id, name) values(#{id}, #{name})")
    int insert(@Param("id") long id, @Param("name") String name);


    @Insert("insert into user(id, name) values(#{id, jdbcType=INTEGER}, #{name, jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);


    @Insert("insert into user(id, name) values(#{id}, #{name})")
    int insertByUser(User user);

    @Update("update user set name=#{name} where id=#{id}")
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void delete(long id);
}
