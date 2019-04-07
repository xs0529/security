package com.xs.example.demo.security.config.security.login;

import com.xs.example.demo.web_common.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Exrickx
 */
@Slf4j
@RestController
@RequestMapping
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    public Result<Object> needLogin(){
        return Result.fail(401, "您还未登录");
    }
}
