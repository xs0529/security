package com.xs.example.demo.permission.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色代码，验证权限时使用
     */
    @TableField("symbol")
    private String symbol;

    /**
     * 是否删除
     */
    @TableField("is_del")
    @TableLogic
    private Boolean isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
