package icu.chiou.qrbac.aop;

import icu.chiou.qrbac.annotation.Authority;
import icu.chiou.qrbac.utils.AuthorityUtil;
import icu.chiou.qrbac.utils.JwtUtil;
import icu.chiou.qrbac.utils.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 接口权限校验 - 切面
 */
@Aspect
@Component
@SuppressWarnings("all")
public class AuthorityAOP {

    @Autowired
    HttpServletRequest request;

    /**
     * 环绕通知：用户拥有权限和接口权限标识校验
     */
    @Around("@annotation(authority)")
    public Object around(ProceedingJoinPoint joinPoint, Authority authority) throws Throwable {
        //获取接口的权限和用户id的权限
        String id = JwtUtil.getMemberIdByJwtToken(request);
        String[] interfaceAuthorities = authority.value();
        for (String interfaceAuthority : interfaceAuthorities) {
            if (!AuthorityUtil.verify(id, interfaceAuthority)) {
                return R.error().count(403).message("权限不足");
            }
        }
        return joinPoint.proceed();
    }
}
