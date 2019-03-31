package com.xs.example.demo.permission;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.xs.example.demo.permission.user.mapper")
@ComponentScan
public class PermissionConfig {

}
