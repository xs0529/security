package com.xs.example.demo.permission.user.mapper;

import com.xs.example.demo.permission.user.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
public interface RoleMapper extends BaseMapper<Role> {

    Set<Role> listByUserId(@Param("userId") Long userId);
}
