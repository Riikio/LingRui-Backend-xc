package com.event.lr.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PasswordConfig {

    @Value("${web.security.encoder:bcrypt}")//添加默认值，防止encoder为null导致异常
    private String encoder;

    public String getEncoder() {
        return encoder;
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        Map<String, org.springframework.security.crypto.password.PasswordEncoder> encoders = new HashMap<>();

        // 注册你允许使用的算法，key 就是密文前缀里的 {id}
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());//用于已有密码，不加密，防止旧帐户无法登录
        // encoder 决定新密码用哪个算法编码
        DelegatingPasswordEncoder delegating = new DelegatingPasswordEncoder(encoder, encoders);//加密时用第一个参数指定算法，校验时根据密文前缀从第二个参数的 Map 里选算法
        return delegating;
    }
}