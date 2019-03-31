package com.xs.example.demo.security.config.security.login;

import com.xs.example.demo.web_common.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xieshuang
 * @date 2019-03-26 11:17
 */
@Component
@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseUtils.out(response, ResponseUtils.resultMap(false,403,"抱歉，您没有访问权限"));
    }

}
