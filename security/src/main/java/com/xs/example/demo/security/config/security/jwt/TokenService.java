package com.xs.example.demo.security.config.security.jwt;

import com.alibaba.fastjson.JSONObject;
import com.xs.example.demo.security.config.redis.RedisUtils;
import com.xs.example.demo.web_common.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2019-01-07
 */
@Service
public class TokenService {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 如果用户已存在token，则将其加入黑名单
     * @param username
     */
    public void removeTokenByUsername(String username){
        String key = MySecurityConstant.USER_TOKEN + ":" + username;
        if (redisUtils.hasKey(key)){
            String key2 = (String) redisUtils.get(key);
            redisUtils.del(MySecurityConstant.TOKEN_USER +":" + key2);
        }
    }

    /**
     * 如果用户已存在token，则将其加入黑名单
     * @param token
     */
    public void removeToken(String token){
        redisUtils.del(MySecurityConstant.TOKEN_USER +":" + token);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(MySecurityConstant.HEADER);
        if (StringUtils.isBlank(token)){
            token = request.getParameter(MySecurityConstant.HEADER);
        }
        if (StringUtils.isNotBlank(token)) {
            String tokenKey = MySecurityConstant.TOKEN_USER + ":" + token;
            try {
                JSONObject jsonObject = (JSONObject) redisUtils.get(tokenKey);
                if (jsonObject==null){
                    ResponseUtils.out(response, ResponseUtils.resultMap(false,401,"token已失效"));
                    return null;
                }else {
                    if (redisUtils.getExpire(tokenKey, TimeUnit.MINUTES)<20){
                        redisUtils.expire(tokenKey,3600);
                    }
                    String username = jsonObject.getString("username");
                    //获取权限
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    String authority = jsonObject.get(MySecurityConstant.AUTHORITIES).toString();

                    if(StringUtils.isNotBlank(authority)){
                        List<String> list = JSONObject.parseObject(authority,ArrayList.class);
                        for(String ga : list){
                            authorities.add(new SimpleGrantedAuthority(ga));
                        }
                    }
                    User principal = new User(username, "", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, null, authorities);
                }
            }catch (Exception e){
                ResponseUtils.out(response, ResponseUtils.resultMap(false,500,"系统错误"));
                return null;
            }
        }
        return null;
    }
}
