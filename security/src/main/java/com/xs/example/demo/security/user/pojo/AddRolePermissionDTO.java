package com.xs.example.demo.security.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xieshuang
 * @date 2019-04-07 17:14
 */
@Data
@ApiModel("为角色添加权限的DTO")
public class AddRolePermissionDTO {

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "权限id集合")
    @NotEmpty(message = "权限id集合不能为空")
    private List<Long> permissionIds;
}
