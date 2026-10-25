package com.event.lr;

import com.event.lr.cfg.usercfg;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@ConfigurationPropertiesScan("com.event.lr.cfg")
@MapperScan("com.event.lr.mapper")
public class LrApplication {

    public static void main(String[] args) {

        SpringApplication.run(LrApplication.class, args);
    }

}
