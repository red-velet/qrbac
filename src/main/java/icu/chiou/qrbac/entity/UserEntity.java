package icu.chiou.qrbac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author redvelet
 * @since 2024-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
