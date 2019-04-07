package com.xs.example.demo.security.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
import java.util.Set;

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
@TableName("sys_permission")
@ApiModel(value="Permission对象", description="")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {Update.class}, message = "id不能为空")
    @ApiModelProperty(value = "id")
    @TableId("id")
    private Long id;

    @NotBlank(groups = {Update.class, Insert.class}, message = "名称不能为空")
    @ApiModelProperty(value = "权限名称")
    @TableField("name")
    private String name;

    @NotBlank(groups = {Update.class, Insert.class}, message = "标识不能为空")
    @ApiModelProperty(value = "权限标识")
    @TableField("symbol")
    private String symbol;

    @NotBlank(groups = {Update.class, Insert.class}, message = "父级ID不能为空")
    @ApiModelProperty(value = "父级id")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty(value = "权限类型，0菜单，1接口")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "链接")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "是否启用（1是0否）")
    @TableField("enable")
    private Boolean enable;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @TableField(exist=false)
    Set<Role> roleSet;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
