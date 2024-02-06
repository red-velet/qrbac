package icu.chiou.qrbac.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: redvelet
 * createTime: 2024/2/6
 * description: 自定义异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RBACException extends RuntimeException {
    private static final long serialVersionUID = 1752874655926172987L;
    private Integer code;

    private String msg;
}
