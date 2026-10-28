package com.event.lr.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "user1")
public class usercfg  {
    @Value("${user1.password.minlength}")
    private int passwordMinlength;
    @Value("${user1.username.minlength}")
    private int usernameMinlength;
    @Value("${user1.username.maxlength}")
    private int usernameMaxlength;
    @Value("${user1.username.allowedpattern}")
    private String allowedpattern;
    @Value("${web.security.encoder}")
    private String encoder;

    public String getEncoder() {
        return encoder;
    }

    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }

    public void setPasswordMinlength(int passwordMinlength) {
        this.passwordMinlength = passwordMinlength;
    }

    public void setUsernameMinlength(int usernameMinlength) {
        this.usernameMinlength = usernameMinlength;
    }

    public void setUsernameMaxlength(int usernameMaxlength) {
        this.usernameMaxlength = usernameMaxlength;
    }

    public void setAllowedpattern(String allowedpattern) {
        this.allowedpattern = allowedpattern;
    }

    public int getPasswordMinlength() {
        return passwordMinlength;
    }

    public int getUsernameMinlength() {
        return usernameMinlength;
    }

    public int getUsernameMaxlength() {
        return usernameMaxlength;
    }

    public String getAllowedpattern() {
        return allowedpattern;
    }
}
