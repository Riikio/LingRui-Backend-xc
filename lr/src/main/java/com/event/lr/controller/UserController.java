package com.event.lr.controller;

import com.event.lr.cfg.usercfg;
import com.event.lr.pojo.User;
import com.event.lr.service.UserService;
import com.event.lr.utils.Md5Util;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.event.lr.pojo.Result;

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

    @PostMapping("/login")
    public Result login(String username,String password) {
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
        User loginUser = userService.findByUsername(username);
        if (loginUser == null) {
            return Result.error(404, "用户不存在", null);//用户不存在
        }
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
            return Result.success("JWT token令牌");//登录成功
        }
        else return Result.error(401, "密码错误", null);//密码错误
    }
}
