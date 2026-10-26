package com.event.lr.controller;

import com.event.lr.cfg.usercfg;
import com.event.lr.pojo.User;
import com.event.lr.service.UserService;
import com.event.lr.utils.Md5Util;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.event.lr.pojo.Result;

import java.util.List;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private usercfg u;
    private String username;


    @PostMapping("/register")
    public Result register(String username,String password) {
        Result checkResult = userService.check(username, password, u);
        if (checkResult.getCode().equals(200)) {
            //查询用户是否存在
            User user = userService.findByUsername(username);
            if (user == null) {
                //不存在
                userService.register(username, password);
                return Result.success();
            } else {
                return Result.error(-1, "用户名已存在", null);//用户名已存在
            }//注册用户
        }
        else return checkResult;
    }

    @PostMapping("/login")
    public Result login(String username,String password) {
        Result checkResult = userService.check(username, password, u);
        if (checkResult.getCode().equals(200)) {
            User loginUser = userService.findByUsername(username);
            if (loginUser == null) {
                return Result.error(404, "用户不存在", null);//用户不存在
            }
            if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
                return Result.success("JWT token令牌");//登录成功
            } else return Result.error(401, "密码错误", null);//密码错误
        }
        else return checkResult;
    }
    @GetMapping("/getUserName")
    public List<String> getUserName() {
        return userService.getAllUserName();
    }
}
