package com.xs.example.demo.security.config.security.jwt;

/**
 * @author: 谢霜
 * @Date: 2018/09/13 上午 11:30
 * @Description:
 */
public interface MySecurityConstant {
    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * 已经登陆的token key
     */
    String USER_TOKEN = "usertoken";

    /**
     * 通过token获取用户的key
     */
    String TOKEN_USER = "tokenuser";
}
