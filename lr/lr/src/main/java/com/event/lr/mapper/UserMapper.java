package com.event.lr.mapper;

import com.event.lr.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username =#{username}")
    User findByUsername(String username);
    //添加用户
    @Insert("insert into user(username,password) values(#{username},#{Password})")
    void add(String username, String Password);
    //获取所有用户名
    @Select("select username from user limit #{offset}, #{size}")
    List<String> getAllUserNameByPage(int offset, int size);
    //统计页数
    @Select("select count(*) from user")
    long countAll();
}
