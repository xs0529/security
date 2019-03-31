package com.xs.example.demo.security.config.security;

import com.xs.example.demo.permission.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author xieshuang
 * @date 2019-03-31 13:56
 */
public class MyUserDetails implements UserDetails {

    private User user;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User user, Collection<? extends GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    public MyUserDetails(User user){
        this.user = user;
    }

    public MyUserDetails(){

    }

    public void setUser(User user){
        this.user = user;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnable();
    }
}
