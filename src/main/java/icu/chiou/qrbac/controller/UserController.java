package icu.chiou.qrbac.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.chiou.qrbac.entity.RoleEntity;
import icu.chiou.qrbac.entity.UserEntity;
import icu.chiou.qrbac.entity.UserRoleEntity;
import icu.chiou.qrbac.service.RoleService;
import icu.chiou.qrbac.service.UserRoleService;
import icu.chiou.qrbac.service.UserService;
import icu.chiou.qrbac.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author redvelet
 * @since 2024-01-23
 */
@RestController
@RequestMapping("/authorize/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService urService;

    @Autowired
    RoleService roleService;

    @GetMapping("list")
    public R listPage(
            @RequestParam(value = "page", defaultValue = "1") Long current,
            @RequestParam(value = "limit", defaultValue = "15") Long limit,
            @RequestParam(required = false) String name
    ) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(name)) {
            wrapper.like(UserEntity::getName, name);
        }
        Page<UserEntity> page = userService.page(new Page<>(current, limit), wrapper);
        page.getRecords()
                .forEach(user -> {
                    // 根据用户id获取角色id
                    List<Integer> rIds = urService.list(
                                    new QueryWrapper<UserRoleEntity>()
                                            .eq("u_id", user.getId())
                                            .select("r_id"))
                            .stream()
                            .map(UserRoleEntity::getRId)
                            .collect(Collectors.toList());

                    if (!ObjectUtils.isEmpty(rIds)) {
                        // 根据角色表查出对应的角色名字
                        List<String> roleNames = roleService.listByIds(rIds)
                                .stream().map(RoleEntity::getName)
                                .collect(Collectors.toList());
                        user.setRoleName(roleNames);
                    }
                });
        return R.ok().data("data", page.getRecords()).count(page.getTotal());
    }
}
