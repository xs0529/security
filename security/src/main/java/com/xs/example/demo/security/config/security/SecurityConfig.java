package com.xs.example.demo.security.config.security;

import com.xs.example.demo.security.config.security.Verification.MyFilterInvocationSecurityMetadataSource;
import com.xs.example.demo.security.config.security.Verification.MyFilterSecurityInterceptor;
import com.xs.example.demo.security.config.security.jwt.JWTAuthenticationFilter;
import com.xs.example.demo.security.config.security.jwt.MySecurityConstant;
import com.xs.example.demo.security.config.security.jwt.TokenService;
import com.xs.example.demo.security.config.security.login.AuthenticationFailHandler;
import com.xs.example.demo.security.config.security.login.AuthenticationSuccessHandler;
import com.xs.example.demo.security.config.security.login.MyUserDetailsServiceImpl;
import com.xs.example.demo.security.config.security.login.RestAccessDeniedHandler;
import com.xs.example.demo.web_common.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2019-01-06
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailHandler failHandler;
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;
    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private MySecurityProperties properties;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http
                .authorizeRequests();

        if (!properties.isEnableMySecurity()){
            urlRegistry.antMatchers("/**").permitAll();
        }

        urlRegistry
                .and()
                .formLogin()
                .loginPage("/needLogin")
                .loginProcessingUrl("/login")
                .permitAll()
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication)->{
                    String token = request.getHeader(MySecurityConstant.HEADER);
                    if(StringUtils.isEmpty(token)){
                        token = request.getParameter(MySecurityConstant.HEADER);
                    }
                    if (!StringUtils.isEmpty(token)){
                        tokenService.removeToken(token);
                        ResponseUtils.out(response, ResponseUtils.resultMap(true,200,"注销成功"));
                    }
                })
                .permitAll()
                .and()
                .authorizeRequests()
                //任何请求
                .anyRequest()
                //需要身份认证
                .authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //自定义权限拒绝处理类
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                // FilterSecurityInterceptor是默认的权限处理过滤器，为了实现根据角色来控制路径的访问，需要在此之前进行处理，加载自己的FilterInvocationSecurityMetadataSource
                .addFilterBefore(myFilterSecurityInterceptor,FilterSecurityInterceptor.class)
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));

        resourceService.loaderUrlAndRole();
    }
}
