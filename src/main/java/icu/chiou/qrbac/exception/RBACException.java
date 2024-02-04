package icu.chiou.qrbac.exception;

import icu.chiou.qrbac.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 统一异常处理类
 */
@RestControllerAdvice(basePackages = "icu.chiou.qrbac")
public class RBACException {
    @ExceptionHandler(Exception.class)
    public R exceptionHandleAll(Exception e) {
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }
}
