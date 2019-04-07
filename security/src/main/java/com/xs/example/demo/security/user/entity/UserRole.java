package com.xs.example.demo.security.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("sys_user_role")
@ApiModel(value="UserRole对象", description="")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    @TableId("id")
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "user_id")
    @TableField("user_id")
    private Long userId;

    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "role_id")
    @TableField("role_id")
    private Long roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
