package com.xs.example.demo.security.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xieshuang
 * @date 2019-04-07 11:30
 */
@ConfigurationProperties(
        prefix = "mysecurity"
)
@Component
public class MySecurityProperties {

    private boolean enableMySecurity = false;
    /**
     * 登录失败5分钟后再登录
     */
    private Integer loginAfterTime = 5;
    /**
     * 登录失败次数
     */
    private Integer loginTimeLimit = 5;
    /**
     * 保存登录时长 7天
     */
    private Integer saveLoginTime = 7;
    /**
     * token过期时间 1小时
     */
    private Integer tokenExpireTime = 1;

    public Integer getLoginAfterTime() {
        return loginAfterTime;
    }

    public void setLoginAfterTime(Integer loginAfterTime) {
        this.loginAfterTime = loginAfterTime;
    }

    public Integer getLoginTimeLimit() {
        return loginTimeLimit;
    }

    public void setLoginTimeLimit(Integer loginTimeLimit) {
        this.loginTimeLimit = loginTimeLimit;
    }

    public Integer getSaveLoginTime() {
        return saveLoginTime;
    }

    public void setSaveLoginTime(Integer saveLoginTime) {
        this.saveLoginTime = saveLoginTime;
    }

    public Integer getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Integer tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public boolean isEnableMySecurity() {
        return enableMySecurity;
    }

    public void setEnableMySecurity(boolean enableMySecurity) {
        this.enableMySecurity = enableMySecurity;
    }
}
