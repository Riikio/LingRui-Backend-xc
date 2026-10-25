package com.event.lr.service.impl;
import com.event.lr.mapper.UserMapper;
import com.event.lr.pojo.User;
import com.event.lr.service.UserService;
import com.event.lr.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
