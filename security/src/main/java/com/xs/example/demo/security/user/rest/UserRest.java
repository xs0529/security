package com.xs.example.demo.security.user.rest;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.example.demo.security.user.entity.User;
import com.xs.example.demo.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieshuang
 * @date 2019-03-31 16:01
 */
@RestController
@RequestMapping("user")
public class UserRest {

    @Autowired
    private UserService userService;
}
