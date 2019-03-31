package com.xs.example.demo.permission.user.service.impl;

import com.xs.example.demo.permission.user.entity.Permission;
import com.xs.example.demo.permission.user.mapper.PermissionMapper;
import com.xs.example.demo.permission.user.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<Permission> allPermissionSet(){
        return permissionMapper.allPermissionSet();
    }
}
