package com.event.lr.mapper;

import com.event.lr.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLSelect;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username =#{username}")
    User findByUsername(String username);
    //添加用户
    @Insert("insert into user(username,password) values(#{username},#{md5Password})")
    void add(String username, String md5Password);
}
