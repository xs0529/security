package com.xs.example.demo.security.user.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.example.demo.security.user.entity.User;
import com.xs.example.demo.security.user.entity.UserRole;
import com.xs.example.demo.security.user.mapper.UserMapper;
import com.xs.example.demo.security.user.pojo.AddUserRoleDTO;
import com.xs.example.demo.security.user.service.UserService;
import com.xs.example.demo.web_common.exception.MyRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUserRole(AddUserRoleDTO addUserRoleDTO) {
        addUserRoleDTO.getRoleIds().forEach(aLong -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(aLong);
            userRole.setUserId(addUserRoleDTO.getUserId());
            if (!userRole.insert()){
                throw new MyRuntimeException("插入用户角色信息时出错");
            }
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserRole(AddUserRoleDTO addUserRoleDTO) {
        boolean delete = new UserRole().delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, addUserRoleDTO.getUserId()));
        if (delete){
            return addUserRole(addUserRoleDTO);
        }else {
            throw new MyRuntimeException("更新用户角色信息时出错");
        }
    }
}
