package com.event.lr.service;

import com.event.lr.cfg.usercfg;
import com.event.lr.pojo.Result;
import com.event.lr.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    jakarta.servlet.http.HttpServletResponse HttpServletResponse = null;


    //根据用户名查询用户
    User findByUsername(String username);
    //注册用户
    Result register(String username, String password, usercfg u);
    //登录用户
    Result login(String username, String password, usercfg u, String token);
    //获取所有用户名
    Result getAllUserNameByPage(int page, int size,String token);
    //统计页数
    long countAll();
}
