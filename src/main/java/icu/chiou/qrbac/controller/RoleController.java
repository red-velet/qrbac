package icu.chiou.qrbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.chiou.qrbac.entity.*;
import icu.chiou.qrbac.service.RolePermissionService;
import icu.chiou.qrbac.service.RoleService;
import icu.chiou.qrbac.service.UserRoleService;
import icu.chiou.qrbac.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 角色管理-接口
 */
@RestController
@RequestMapping("/authorize/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    RolePermissionService rpService;

    @GetMapping("list")
    public R listPage(
            @RequestParam(value = "page", defaultValue = "1") Long current,
            @RequestParam(value = "limit", defaultValue = "15") Long limit,
            @RequestParam(required = false) String name
    ) {
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(name)) {
            wrapper.like(RoleEntity::getName, name);
        }
        Page<RoleEntity> page = roleService.page(new Page<>(current, limit), wrapper);
        return R.ok().data("data", page.getRecords()).count(page.getTotal());
    }


    @PostMapping("/add")
    public R add(@RequestBody RoleEntity role) {
        roleService.save(role);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody RoleEntity role) {
        roleService.updateById(role);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public R delete(@PathVariable String id) {
        rpService.remove(new QueryWrapper<RolePermissionEntity>().eq("r_id", id));
        roleService.removeById(id);
        return R.ok();
    }

    @GetMapping("/treeList")
    public List<Tree> authorizeTreeList() {
        List<Tree> treeList = roleService.getAuthorizeTreeList();
        return treeList;
    }

    @PostMapping("/authority")
    @Transactional
    public R authority(@RequestBody AuthorityDTO authorityDTO) {
        rpService.remove(new QueryWrapper<RolePermissionEntity>().eq("r_id", authorityDTO.getRid()));
        List<RolePermissionEntity> list = new ArrayList<>();
        Integer rid = authorityDTO.getRid();
        for (Integer pId : authorityDTO.getPid()) {
            RolePermissionEntity rolePermission = new RolePermissionEntity();
            rolePermission.setRId(rid);
            rolePermission.setPId(pId);
            list.add(rolePermission);
        }
        rpService.saveBatch(list);
        return R.ok();
    }

    @GetMapping("/getPermission/{id}")
    public Integer[] getPermission(@PathVariable Integer id) {
        return rpService.list(new QueryWrapper<RolePermissionEntity>()
                        .eq("r_id", id)
                        .select("p_id"))
                .stream().map(RolePermissionEntity::getPId).toArray(Integer[]::new);
    }

    @GetMapping("/initRole")
    public List<Map<String, Object>> initRole() {
        // 查出所有角色
        return roleService.list().stream()
                .map(role -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("value", role.getId());
                    data.put("title", role.getName());
                    return data;
                }).collect(Collectors.toList());
    }

    @Autowired
    UserRoleService urService;

    @GetMapping("/getRole/{id}")
    public List<Integer> getRole(@PathVariable Integer id) {
        return urService.list(
                        new QueryWrapper<UserRoleEntity>()
                                .eq("u_id", id)
                                .select("r_id"))
                .stream()
                .map(UserRoleEntity::getRId)
                .collect(Collectors.toList());
    }

    @PostMapping("/assignRole")
    @Transactional
    public R assignRole(@RequestBody AssignRoleDTO assignRoleDTO) {
        Integer uId = assignRoleDTO.getUId();
        urService.remove(new QueryWrapper<UserRoleEntity>().eq("u_id", uId));
        List<UserRoleEntity> userRoles = new ArrayList<>();
        for (Integer id : assignRoleDTO.getRId()) {
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUId(uId);
            userRole.setRId(id);
            userRoles.add(userRole);
        }
        urService.saveBatch(userRoles);
        return R.ok();
    }
}
