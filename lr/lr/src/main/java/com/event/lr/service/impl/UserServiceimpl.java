package com.event.lr.service.impl;
import com.event.lr.cfg.usercfg;
import com.event.lr.mapper.UserMapper;
import com.event.lr.pojo.Result;
import com.event.lr.pojo.User;
import com.event.lr.service.UserService;
import com.event.lr.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public void register(String username, String password) {


            //加密密码
            String md5Password = Md5Util.getMD5String(password);
            //添加用户
            userMapper.add(username, md5Password);
    }
    @Override
    public List<String> getAllUserName() {
        return userMapper.getAllUserName();
    }
    @Override
    public Result check(String username, String password, usercfg u) {
        if (username.isEmpty()) {
            return Result.error(400, "用户名不能为空", null);//用户名不能为空
        }
        if (password.isEmpty()) {
            return Result.error(400, "密码不能为空", null);//密码不能为空
        }
        if (!username.matches(u.getAllowedpattern())) {
            return Result.error(400, "用户名格式错误,只能使用字母、数字和下划线", null);//用户名格式错误
        }
        if (username.length() < u.getUsernameMinlength() || username.length() > u.getUsernameMaxlength()) {
            return Result.error(400, "用户名长度错误,应该在"+u.getUsernameMinlength()+"-"+u.getUsernameMaxlength()+"之间", null);//用户名长度错误
        }
        if (password.length() < u.getPasswordMinlength()) {
            return Result.error(400, "密码长度错误,应该大于"+u.getPasswordMinlength() + "位", null);//密码长度错误
        }
        else return Result.success();//用户名或密码符合要求
    }
}
