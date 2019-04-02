package com.xs.example.demo.security.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.example.demo.security.user.entity.Role;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
public interface RoleService extends IService<Role> {
    Set<Role> listByUserId(Long userId);
}
