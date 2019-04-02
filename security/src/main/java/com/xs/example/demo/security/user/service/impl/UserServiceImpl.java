package com.xs.example.demo.security.user.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.example.demo.security.user.entity.User;
import com.xs.example.demo.security.user.mapper.UserMapper;
import com.xs.example.demo.security.user.service.UserService;
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
