package com.event.lr.service.impl;
import com.event.lr.cfg.PasswordConfig;
import com.event.lr.cfg.usercfg;
import com.event.lr.mapper.UserMapper;
import com.event.lr.pojo.Result;
import com.event.lr.pojo.User;
import com.event.lr.service.UserService;
import com.event.lr.utils.JwtUtil;
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
    @Autowired
    private jakarta.servlet.http.HttpServletRequest HttpServletRequest;
    @Autowired
    private jakarta.servlet.http.HttpServletResponse HttpServletResponse;
    @Autowired
    private PasswordConfig passwordConfig;


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
        String codedPassword = passwordConfig.passwordEncoder().encode(password);
        //添加用户
        userMapper.add(username, codedPassword);
        return Result.success(codedPassword);//注册成功
    }
    //登录用户
    @Override
    public Result login(String username, String password, usercfg u, String token) {
        if(token == null) {
            Result checkResult = check(username, password, u);
            if (!checkResult.getCode().equals(200)) {
                return checkResult;
            }
            User loginUser = findByUsername(username);
            if (loginUser == null) {
                return Result.error(404, "用户不存在", null);//用户不存在
            }
            if (passwordConfig.passwordEncoder().matches(password, loginUser.getPassword())) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", loginUser.getId());
                claims.put("username", username);
                token = JwtUtil.genToken(claims);//生成JWT token
                return Result.success(token);//登录成功，并返回token
            }
        }
        else try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("近12h内登录过，实现免登录");
        } catch (Exception e) {
            HttpServletResponse.setStatus(401);
            return Result.error(401, "未登录", null);//未登录
        }
        return Result.error(401, "密码错误", null);//密码错误
    }

    //获取所有用户名
    @Override
    public Result getAllUserNameByPage(int page, int size, String token) {
        if(page < 1) page = 1;
        if(size < 1) size = 10;//默认每一页最少输出量
        if(size > 100) size = 100;//防止每一页输出太多
        int offset = (page - 1) * size;
        List list = userMapper.getAllUserNameByPage(offset, size);
        long userCount = userMapper.countAll();
        Map<String, Object> information = new HashMap<>();//使用哈希映射存储信息
        information.put("userCount", userCount);
        information.put("encoder", passwordConfig.getEncoder());
        information.put("page", page);
        information.put("list", list);
        //方法业务部分

        //JWT令牌验证部分
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success(information);
        } catch (Exception e) {
            HttpServletResponse.setStatus(401);
            return Result.error(401, "未登录", null);//未登录
        }
    }

    //输入条件判断
    private Result check(String username, String password, usercfg u) {
        if (username == null || username.isEmpty()) {
            return Result.error(400, "用户名不能为空", null);//用户名不能为空
        }
        if (password == null || password.isEmpty()) {
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
