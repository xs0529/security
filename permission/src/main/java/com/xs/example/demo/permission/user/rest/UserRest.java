package com.xs.example.demo.permission.user.rest;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.example.demo.permission.user.entity.User;
import com.xs.example.demo.permission.user.service.UserService;
import com.xs.example.demo.web_common.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieshuang
 * @date 2019-03-31 16:01
 */
@RestController
@RequestMapping("user")
@Api(description = "用户相关接口")
public class UserRest extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Override
    public IService<User> getService() {
        return userService;
    }

}
