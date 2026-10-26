package com.event.lr.service;

import com.event.lr.cfg.usercfg;
import com.event.lr.pojo.Result;
import com.event.lr.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //根据用户名查询用户
    User findByUsername(String username);
    //注册用户
    void register(String username, String password);
    //获取所有用户名
    List<String> getAllUserNameByPage(int page, int size);
    //输入条件判断
    Result check(String username, String password, usercfg u);
    //统计页数
    long countAll();
}
