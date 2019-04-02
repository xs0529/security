package com.xs.example.demo.security.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author 谢霜
 * @since 2019-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    @NotNull(message = "用户名不能为空")
    @Length(min = 6 ,max = 20, message = "用户名长度在6到20个字符")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    @NotNull(message = "密码不能为空")
    @Length(min = 6 ,max = 20, message = "密码长度在6到20个字符")
    private String password;

    /**
     * 1启用，0禁用
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 创建时间
     */
    @ApiModelProperty(readOnly = true)
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ApiModelProperty(readOnly = true)
    private Date updateTime;

    /**
     * 名称
     */
    @TableField("name")
    @NotNull(message = "名称不能为空")
    private String name;

    /**
     * 是否删除
     */
    @ApiModelProperty(hidden = true)
    @TableField("is_del")
    @TableLogic
    private Boolean isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
