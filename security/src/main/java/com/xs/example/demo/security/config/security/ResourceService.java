package com.xs.example.demo.security.config.security;

import com.xs.example.demo.permission.user.entity.Permission;
import com.xs.example.demo.permission.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2019-02-22
 */
@Service
public class ResourceService {

    @Autowired
    private PermissionService permissionService;

    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> loaderUrlAndRole() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
        Set<Permission> permissions = permissionService.allPermissionSet();
        if (!CollectionUtils.isEmpty(permissions)){
            permissions.forEach(permission -> {
                Boolean b = !StringUtils.isEmpty(permission.getUrl()) && !StringUtils.isEmpty(permission.getUrl().trim()) && !CollectionUtils.isEmpty(permission.getRoleSet());
                if (b){
                    RequestMatcher requestMatcher = new AntPathRequestMatcher(permission.getUrl());
                    List<ConfigAttribute> configAttributes = new ArrayList<>();
                    // 如果账号启用
                    if (permission.getEnable()){
                        // 添加需要的角色
                        permission.getRoleSet().forEach(role -> {
                            SecurityConfig securityConfig = new SecurityConfig("ROLE_"+role.getSymbol());
                            configAttributes.add(securityConfig);
                        });
                    }else {
                        // 添加乱的角色，保证所有人都没有，访问不了GG
                        configAttributes.add(new SecurityConfig("ROLE_"+System.currentTimeMillis()));
                    }
                    requestMap.put(requestMatcher, configAttributes);
                }
            });
            return requestMap;
        }else {
            return null;
        }
    }
}
