package com.xs.example.demo.permission.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限标识
     */
    @TableField("symbol")
    private String symbol;

    /**
     * 父级id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 权限类型，0菜单，1接口
     */
    @TableField("type")
    private Integer type;

    /**
     * 链接
     */
    @TableField("url")
    private String url;

    /**
     * 是否启用（1是0否）
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    private Set<Role> roleSet;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
