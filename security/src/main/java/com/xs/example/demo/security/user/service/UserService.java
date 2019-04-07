package com.xs.example.demo.security.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.example.demo.security.user.entity.User;
import com.xs.example.demo.security.user.pojo.AddUserRoleDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
public interface UserService extends IService<User> {

    /**
     * 为用户添加角色信息
     * @param addUserRoleDTO
     * @return
     */
    boolean addUserRole(AddUserRoleDTO addUserRoleDTO);

    /**
     * 为用户更新角色信息
     * @param addUserRoleDTO
     * @return
     */
    boolean updateUserRole(AddUserRoleDTO addUserRoleDTO);
}
