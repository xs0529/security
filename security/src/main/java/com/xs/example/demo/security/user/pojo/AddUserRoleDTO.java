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
@ApiModel("为用户添加角色的DTO")
public class AddUserRoleDTO {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "角色id集合")
    @NotEmpty(message = "角色id集合不能为空")
    private List<Long> RoleIds;
}
