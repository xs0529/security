package com.xs.example.demo.permission.user.service;

import com.xs.example.demo.permission.user.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
public interface PermissionService extends IService<Permission> {
    Set<Permission> allPermissionSet();
}
