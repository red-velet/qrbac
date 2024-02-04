package icu.chiou.qrbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author redvelet
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("permission")
public class PermissionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级/上级目录id
     */
    private Integer pId;

    /**
     * 路径
     */
    private String path;

    /**
     * 权限标识
     */
    private String href;

    /**
     * 图标	图标
     */
    private String icon;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 是否是菜单
     * -1:根目录 0:菜单  1:按钮
     */
    private Integer isMenu;

    /**
     * 目标
     */
    private String target;

    /**
     * 状态
     */
    private Integer state;

    @TableField(exist = false)
    private List<PermissionEntity> children;

}
