package com.event.lr.service.impl;
import com.event.lr.cfg.usercfg;
import com.event.lr.mapper.UserMapper;
import com.event.lr.pojo.Result;
import com.event.lr.pojo.User;
import com.event.lr.service.UserService;
import com.event.lr.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public long countAll() {
        return userMapper.countAll();
    }

    @Override
    public Result register(String username, String password, usercfg u) {
        Result checkResult = check(username, password, u);
        if (!checkResult.getCode().equals(200)) {
            return checkResult;
        }
        User user = findByUsername(username);
        if (user != null) {
            return Result.error(-1, "用户名已存在", null);//用户名已存在
        }
        //加密密码
        String md5Password = Md5Util.getMD5String(password);
        //添加用户
        userMapper.add(username, md5Password);
        return Result.success();//注册成功
    }
    //登录用户
    @Override
    public Result login(String username, String password, usercfg u) {
        Result checkResult = check(username, password, u);
        if (!checkResult.getCode().equals(200)) {
            return checkResult;
        }
        User loginUser = findByUsername(username);
        if (loginUser == null) {
            return Result.error(404, "用户不存在", null);//用户不存在
        }
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
            return Result.success("JWT token令牌");//登录成功
        } else return Result.error(401, "密码错误", null);//密码错误
    }

    //获取所有用户名
    @Override
    public Result getAllUserNameByPage(int page, int size) {
        if(page < 1) page = 1;
        if(size < 1) size = 10;//默认每一页最少输出量
        if(size > 100) size = 100;//防止每一页输出太多
        int offset = (page - 1) * size;
        List list = userMapper.getAllUserNameByPage(offset, size);
        long userCount = userMapper.countAll();
        Map<String, Object> information = new HashMap<>();//使用哈希映射存储信息
        information.put("userCount", userCount);
        information.put("page", page);
        information.put("list", list);
        return Result.success(information);
    }

    //输入条件判断
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
