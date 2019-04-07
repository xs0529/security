package com.xs.example.demo.security.user.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.example.demo.security.user.entity.Role;
import com.xs.example.demo.security.user.service.RoleService;
import com.xs.example.demo.web_common.common.validated.Insert;
import com.xs.example.demo.web_common.common.validated.Update;
import com.xs.example.demo.web_common.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @description : Role 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-04-07
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = "角色接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "角色列表", nickname = "role:list")
    @PostMapping("/list")
    public Result<IPage<Role>> getRoleList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                           @RequestParam(value = "size",defaultValue = "10") Integer size) {
        Page<Role> rolepage = new Page<>(page,size);
        return Result.ok(roleService.page(rolepage,null));
    }

    /**
     * @description : 通过id获取Role
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "获取角色信息", nickname = "role:get")
    @GetMapping("/get/{id}")
    public Result<Role> getRoleById(@PathVariable(value = "id") String id) {
        try {
            Role param= roleService.getById(id);
            return Result.ok(param);
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("获取信息失败");
        }
    }

    /**
     * @description : 通过id删除Role
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "删除角色信息", nickname = "role:del")
    @PostMapping("/delete/{id}")
    public Result deleteRoleById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        try{
            if (roleService.removeByIds(list)){
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
     * @description : 更新Role
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "更新角色信息", nickname = "role:update")
    @PostMapping("/update")
    public Result updateRoleById(@Validated({Update.class}) Role param) {
        try{
            if (roleService.updateById(param)){
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
     * @description : 新增Role
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "新增角色信息", nickname = "role:add")
    @PostMapping("/add")
    public Result addRole(@Validated({Insert.class}) Role param) {
        try{
            if (roleService.save(param)){
                return Result.ok(param);
            } else {
            return Result.fail();
            }
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("添加信息失败", e);
        }
    }
}