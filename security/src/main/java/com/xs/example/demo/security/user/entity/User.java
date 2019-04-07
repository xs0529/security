package com.xs.example.demo.security.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xs.example.demo.web_common.common.validated.Insert;
import com.xs.example.demo.web_common.common.validated.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 谢霜
 * @since 2019-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId("id")
    @NotNull(groups = {Update.class}, message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    @NotBlank(message = "用户名不能为空", groups = {Insert.class, Update.class})
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "1启用，0禁用")
    @TableField("enable")
    private Boolean enable;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @NotBlank(message = "名称不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
