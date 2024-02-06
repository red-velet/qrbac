package icu.chiou.qrbac.controller;

import icu.chiou.qrbac.annotation.Authority;
import icu.chiou.qrbac.entity.PermissionEntity;
import icu.chiou.qrbac.service.PermissionService;
import icu.chiou.qrbac.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * author: redvelet
 * createTime: 2024/1/24
 * description: 权限管理-接口
 */
@RestController
@RequestMapping("/authorize/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    /**
     * 初始化菜单
     *
     * @param request 携带用户token的请求
     * @return 用户拥有权限所对应的菜单
     */
    @GetMapping("/initMenu")
    public Map<String, Object> initMenu(HttpServletRequest request) {
        //返回树形菜单
        Map<String, Object> menuList = permissionService.getUserMenuList(request);
        return menuList;
    }

    /**
     * 查询权限菜单列表
     *
     * @return 权限菜单列表树形集合
     */
    @Authority(value = "permission:list")
    @GetMapping("/list")
    public List<PermissionEntity> list() {
        return permissionService.list();
    }

    /**
     * 创建授权时所选上级菜单下拉列表显示
     *
     * @return 下列列表
     */
    @Authority("permission:treeSelect")
    @GetMapping("/treeSelect")
    public List<PermissionEntity> treeSelect() {
        List<PermissionEntity> data = permissionService.treeSelect();
        return data;
    }


    /**
     * 新增权限
     *
     * @param permission 权限信息
     * @return 统一返回格式R
     */
    @Authority("permission:add")
    @PostMapping("/add")
    public R add(@RequestBody PermissionEntity permission) {
        permission.setIcon("fa " + permission.getIcon());
        permissionService.save(permission);
        return R.ok();
    }


    /**
     * 修改权限信息
     *
     * @param permission 权限
     * @return 统一返回格式R
     */
    @Authority("permission:update")
    @PutMapping("/update")
    public R update(@RequestBody PermissionEntity permission) {
        permission.setIcon("fa " + permission.getIcon());
        permissionService.updateById(permission);
        return R.ok();
    }

    /**
     * 删除权限信息-逻辑删除
     *
     * @param id 权限id
     * @return 统一返回格式R
     */
    @Authority("permission:delete")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {
        permissionService.removeMenu(id);
        return R.ok().message("删除成功");
    }
}
