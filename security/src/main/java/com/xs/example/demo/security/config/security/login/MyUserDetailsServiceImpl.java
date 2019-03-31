package com.xs.example.demo.security.config.security.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.example.demo.security.config.security.MyUserDetails;
import com.xs.example.demo.permission.user.entity.Role;
import com.xs.example.demo.permission.user.entity.User;
import com.xs.example.demo.permission.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xieshuang
 * @date 2019-03-26 11:57
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = new User();
        user = user.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, s));

        if(user == null){
            throw new UsernameNotFoundException("用户名错误");
        }else {
            Long userId = user.getId();
            Set<Role> roles = roleService.listByUserId(userId);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (!CollectionUtils.isEmpty(roles)){
                roles.forEach(role -> {
                    if (role != null && role.getSymbol() != null){
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getSymbol()));
                    }
                });
            }
            return new MyUserDetails(user, authorities);
        }
    }
}
