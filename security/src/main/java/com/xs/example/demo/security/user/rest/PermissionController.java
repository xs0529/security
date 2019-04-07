package com.xs.example.demo.security.user.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.example.demo.security.user.entity.Permission;
import com.xs.example.demo.security.user.service.PermissionService;
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
 *  @description : Permission 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-04-07
 */
@Slf4j
@RestController
@RequestMapping("/permission")
@Api(tags = "权限接口")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "权限列表", nickname = "permission:list")
    @PostMapping("/list")
    public Result<IPage<Permission>> getPermissionList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size",defaultValue = "10") Integer size) {
        Page<Permission> permissionpage = new Page<>(page,size);
        return Result.ok(permissionService.page(permissionpage,null));
    }

    /**
     * @description : 通过id获取Permission
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "获取权限信息", nickname = "permission:get")
    @GetMapping("/get/{id}")
    public Result<Permission> getPermissionById(@PathVariable(value = "id") String id) {
        try {
            Permission param= permissionService.getById(id);
            return Result.ok(param);
        } catch (Exception e) {
            log.info("异常信息:{}"+e.getMessage());
            return Result.fail("获取信息失败");
        }
    }

    /**
     * @description : 通过id删除Permission
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "删除权限信息", nickname = "permission:del")
    @PostMapping("/delete/{id}")
    public Result deletePermissionById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        try{
            if (permissionService.removeByIds(list)){
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
     * @description : 更新Permission
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "更新权限信息", nickname = "permission:update")
    @PostMapping("/update")
    public Result updatePermissionById(@Validated({Update.class}) Permission param) {
        try{
            if (permissionService.updateById(param)){
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
     * @description : 新增Permission
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-04-07
     */
    @ApiOperation(value = "新增权限信息", nickname = "permission:add")
    @PostMapping("/add")
    public Result addPermission(@Validated({Insert.class}) Permission param) {
        try{
            if (permissionService.save(param)){
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