package com.xs.example.demo.security.user.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.example.demo.security.user.entity.Role;
import com.xs.example.demo.security.user.mapper.RoleMapper;
import com.xs.example.demo.security.user.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> listByUserId(Long userId){
        return roleMapper.listByUserId(userId);
    }
}
