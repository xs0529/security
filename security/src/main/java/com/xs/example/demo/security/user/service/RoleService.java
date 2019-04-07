package com.xs.example.demo.security.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.example.demo.security.user.entity.Role;
import com.xs.example.demo.security.user.pojo.AddRolePermissionDTO;
import com.xs.example.demo.security.user.pojo.AddUserRoleDTO;

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

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    Set<Role> listByUserId(Long userId);

    /**
     * 为角色添加权限信息
     * @param addRolePermissionDTO
     * @return
     */
    boolean addRolePermission(AddRolePermissionDTO addRolePermissionDTO);

    /**
     * 为角色更新权限信息
     * @param addRolePermissionDTO
     * @return
     */
    boolean updateRolePermission(AddRolePermissionDTO addRolePermissionDTO);
}
