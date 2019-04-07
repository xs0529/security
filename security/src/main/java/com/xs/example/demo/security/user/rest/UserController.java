package com.xs.example.demo.security.user.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.example.demo.security.user.entity.User;
import com.xs.example.demo.security.user.pojo.AddUserRoleDTO;
import com.xs.example.demo.security.user.service.UserService;
import com.xs.example.demo.web_common.common.validated.Insert;
import com.xs.example.demo.web_common.common.validated.Update;
import com.xs.example.demo.web_common.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @description : User 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-04-07
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @PostMapping("/list")
    @ApiOperation(value = "用户分页列表", nickname = "user:list")
    public Result<IPage<User>> getUserList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                           @RequestParam(value = "size",defaultValue = "10") Integer size) {
        Page<User> userpage = new Page<>(page,size);
        return Result.ok(userService.page(userpage,null));
    }

    /**
     * @description : 通过id获取User
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取用户信息", nickname = "user:get")
    public Result<User> getUserById(@PathVariable(value = "id") String id) {
        try {
            User param= userService.getById(id);
            return Result.ok(param);
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("获取信息失败");
        }
    }

    /**
     * @description : 通过id删除User
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", nickname = "user:del")
    public Result deleteUserById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        try{
            if (userService.removeByIds(list)){
                return Result.ok();
            } else {
                return Result.fail();
            }
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("删除信息失败");
        }
    }

    /**
     * @description : 更新User
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新用户", nickname = "user:update")
    public Result updateUserById(@Validated({Update.class}) User param) {
        User user = param.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, param.getUsername()));
        if (user != null){
            return Result.fail("用户名已存在");
        }
        try{
            if (userService.updateById(param)){
                return Result.ok();
            } else {
                return Result.fail();
            }
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("修改信息失败");
        }
    }

    /**
     * @description : 新增User
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "新增用户信息", nickname = "user:add")
    @PostMapping("/add")
    public Result addUser(@Validated({Insert.class}) User param) {
        User user = param.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, param.getUsername()));
        if (user != null){
            return Result.fail("用户名已存在");
        }
        if (StringUtils.isBlank(param.getPassword())){
            param.setPassword("123456");
        }
        //进行加密
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        param.setPassword(encoder.encode(param.getPassword().trim()));
        try{
            if (userService.save(param)){
                return Result.ok(param);
            } else {
            return Result.fail();
            }
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("添加信息失败", e);
        }
    }

    @ApiOperation(value = "为用户新增角色", nickname = "user:role:add")
    @PostMapping("/role/add")
    public Result addRole(@Validated AddUserRoleDTO dto){
        boolean b = userService.addUserRole(dto);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "为用户更新角色", nickname = "user:role:update")
    @PostMapping("/role/update")
    public Result delRole(@Validated AddUserRoleDTO dto){
        boolean b = userService.addUserRole(dto);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}