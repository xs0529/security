package com.xs.example.demo.security;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@MapperScan("com.xs.example.demo.security.user.mapper")
public class SecurityAppConfig {
}
