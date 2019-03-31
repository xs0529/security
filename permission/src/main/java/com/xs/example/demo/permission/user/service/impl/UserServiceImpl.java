package com.xs.example.demo.permission.user.service.impl;

import com.xs.example.demo.permission.user.entity.User;
import com.xs.example.demo.permission.user.mapper.UserMapper;
import com.xs.example.demo.permission.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
