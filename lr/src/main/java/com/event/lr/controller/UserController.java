package com.event.lr.controller;

import com.event.lr.cfg.usercfg;
import com.event.lr.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.event.lr.pojo.Result;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private usercfg u;
    @Autowired
    private jakarta.servlet.http.HttpServletRequest HttpServletRequest;

    @PostMapping("/register")
    public Result register(String username,String password) {
        return userService.register(username, password, u);
    }

    @PostMapping("/login")
    public Result login(String username,String password,@RequestHeader("loginToken") String token) {
        return userService.login(username, password, u, token);
    }

    @GetMapping("/getUserName")
    public Result getAllUserNameByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestHeader("UserNameListToken") String token, HttpServletResponse response) {
        return userService.getAllUserNameByPage(page, size, token);
    }
}
