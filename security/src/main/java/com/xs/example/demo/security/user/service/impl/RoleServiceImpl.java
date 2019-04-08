package com.xs.example.demo.security.user.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.example.demo.security.user.entity.Role;
import com.xs.example.demo.security.user.entity.RolePermission;
import com.xs.example.demo.security.user.mapper.RoleMapper;
import com.xs.example.demo.security.user.pojo.AddRolePermissionDTO;
import com.xs.example.demo.security.user.service.RoleService;
import com.xs.example.demo.web_common.common.exception.MyRuntimeException;
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

    @Override
    public boolean addRolePermission(AddRolePermissionDTO addRolePermissionDTO) {
        addRolePermissionDTO.getPermissionIds().forEach(aLong -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPId(aLong);
            rolePermission.setRoleId(addRolePermissionDTO.getRoleId());
            if (!rolePermission.insert()){
                throw new MyRuntimeException("插入角色权限信息时出错");
            }
        });
        return true;
    }

    @Override
    public boolean updateRolePermission(AddRolePermissionDTO addRolePermissionDTO) {
        return false;
    }
}
