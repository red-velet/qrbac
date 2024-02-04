package icu.chiou.qrbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.chiou.qrbac.entity.UserEntity;
import icu.chiou.qrbac.service.UserService;
import icu.chiou.qrbac.utils.JwtUtil;
import icu.chiou.qrbac.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * author: redvelet
 * createTime: 2024/1/23
 * description: 登录接口
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(String name, String password) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, name);
        UserEntity user = userService.getOne(wrapper);
        if (Objects.isNull(user)) {
            return R.error().message("账号不存在");
        } else if (Objects.isNull(password) || !user.getPassword().equals(password)) {
            return R.error().message("密码不匹配");
        }
        //返回token
        String jwtToken = JwtUtil.getJwtToken(String.valueOf(user.getId()), name);
        return R.ok().data("token", jwtToken).data("name", name);
    }
}
