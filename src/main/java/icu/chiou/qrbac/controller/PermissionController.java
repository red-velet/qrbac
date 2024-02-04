package icu.chiou.qrbac.controller;

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

    @GetMapping("/initMenu")
    public Map<String, Object> initMenu(HttpServletRequest request) {
        //返回树形菜单
        Map<String, Object> menuList = permissionService.getUserMenuList(request);
        return menuList;
    }

    @GetMapping("/list")
    public List<PermissionEntity> list() {
        return permissionService.list();
    }

    @GetMapping("/treeSelect")
    public List<PermissionEntity> treeSelect() {
        List<PermissionEntity> data = permissionService.treeSelect();
        return data;
    }


    @PostMapping("/add")
    public R add(@RequestBody PermissionEntity permission) {
        permission.setIcon("fa " + permission.getIcon());
        permissionService.save(permission);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody PermissionEntity permission) {
        permission.setIcon("fa " + permission.getIcon());
        permissionService.updateById(permission);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {
        permissionService.removeMenu(id);
        return R.ok().message("删除成功");
    }
}
