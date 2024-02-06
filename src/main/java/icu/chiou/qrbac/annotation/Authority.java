package icu.chiou.qrbac.annotation;

import java.lang.annotation.*;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 权限标识注解
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    //权限标识
    String[] value();
}
