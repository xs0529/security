package com.xs.example.demo.security.config.security.jwt;

import com.xs.example.demo.web_common.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 谢霜
 * @Date: 2018/09/13 上午 10:26
 * @Description: JWT过滤器
 */

@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    /*
       在doFilterInternal方法中，从http头的Authorization 项读取token数据，
       然后用Jwts包提供的方法校验token的合法性。
       如果校验通过，就认为这是一个取得授权的合法请求。
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取
        String header = request.getHeader(MySecurityConstant.HEADER);
        if(StringUtils.isBlank(header)){
            header = request.getParameter(MySecurityConstant.HEADER);
        }
        if (StringUtils.isBlank(header)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = SpringUtils.getBean(TokenService.class).getAuthentication(request, response);
            if (authentication!=null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            }
        }catch (Exception e){
            log.error("doFilterInternal出错："+e);
        }
    }
}
