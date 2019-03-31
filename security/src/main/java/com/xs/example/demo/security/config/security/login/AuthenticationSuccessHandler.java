package com.xs.example.demo.security.config.security.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xs.example.demo.security.config.redis.RedisUtils;
import com.xs.example.demo.security.config.security.jwt.MySecurityConstant;
import com.xs.example.demo.security.config.security.jwt.TokenService;
import com.xs.example.demo.web_common.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author: 谢霜
 * @Date: 2018/09/13 上午 10:26
 * @Description: 认证成功的处理类
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${tokenExpireTime}")
    private Integer tokenExpireTime;
    @Value("${saveLoginTime}")
    private Integer saveLoginTime;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TokenService tokenService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //用户选择保存登录状态几天
        String saveTime = request.getParameter(MySecurityConstant.SAVE_LOGIN);
        if(StringUtils.isNotBlank(saveTime)&&Boolean.valueOf(saveTime)){
            tokenExpireTime = saveLoginTime * 24;
        }
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        Collection<SimpleGrantedAuthority> collection = (Collection<SimpleGrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
        List<String> authorities = new ArrayList<>();
        for(SimpleGrantedAuthority g : collection){
            authorities.add(g.getAuthority());
        }
        JSONObject jsonObject  =new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("authorities", JSON.toJSONString(authorities));
        String token = UUID.randomUUID().toString();
        // 将token放入redis，值为用户数据
        redisUtils.set(MySecurityConstant.TOKEN_USER + ":" +token,jsonObject,tokenExpireTime * 3600);
        // 删除之前的token
        tokenService.removeTokenByUsername(username);
        // 更新当前用户的token
        redisUtils.set(MySecurityConstant.USER_TOKEN+":"+ username, token);
        response.setHeader("accessToken",token);
        ResponseUtils.out(response, ResponseUtils.resultMap(true,200,"登录成功",token));
    }
}