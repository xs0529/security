package com.xs.example.demo.security.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.example.demo.security.user.entity.Permission;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<Permission> allPermissionSet();
}
