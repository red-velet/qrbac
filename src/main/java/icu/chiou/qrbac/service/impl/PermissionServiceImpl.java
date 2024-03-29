package icu.chiou.qrbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.chiou.qrbac.entity.*;
import icu.chiou.qrbac.mapper.PermissionMapper;
import icu.chiou.qrbac.service.PermissionService;
import icu.chiou.qrbac.service.RolePermissionService;
import icu.chiou.qrbac.service.UserRoleService;
import icu.chiou.qrbac.utils.AuthorityUtil;
import icu.chiou.qrbac.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author redvelet
 * @since 2024-01-24
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Autowired
    @Lazy
    PermissionService permissionService;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    UserRoleService urService;

    @Autowired
    RolePermissionService rpService;

    @Override
    public Map<String, Object> getUserMenuList(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();

        //获取用户id
        String userId = JwtUtil.getMemberIdByJwtToken(request);

        //查询用户对应角色所拥有的 权限列表 转为菜单对象
        // 根据用户id查询对应的角色id
        List<Integer> rIds = urService.list(
                        new QueryWrapper<UserRoleEntity>()
                                .eq("u_id", userId)
                                .select("r_id"))
                .stream()
                .map(UserRoleEntity::getRId)
                .collect(Collectors.toList());

        // 根据角色查询对应的权限id
        List<Integer> pIds = rpService.list(
                        new QueryWrapper<RolePermissionEntity>()
                                .in("r_id", rIds)
                                .select("p_id")).stream()
                .map(RolePermissionEntity::getPId)
                .collect(Collectors.toList());

        // 根据权限id查出权限
        List<Menu> allMenu = new ArrayList<>();
        permissionService
                .list(new QueryWrapper<PermissionEntity>().in("id", pIds))
                .forEach(permissionEntity -> {
                    Menu menu = new Menu();
                    BeanUtils.copyProperties(permissionEntity, menu);
                    menu.setTitle(permissionEntity.getName());
                    allMenu.add(menu);
                });


        //转为树形菜单
        //1.获取根节点菜单
        List<Menu> rootMenu = new ArrayList<>();
        for (Menu menu : allMenu) {
            if (menu.getPId() == 0) {
                menu.setChild(new ArrayList<>());
                rootMenu.add(menu);
            }
        }
        //2.根据根节点查找对应所有子节点
        //初始化当前用户权限标识列表
        HashSet<String> set = new HashSet<>();
        for (Menu menu : rootMenu) {
            menu.getChild().add(findChildrenNode(menu, allMenu, set));
        }
        AuthorityUtil.setAuthority(userId, set);

        // 保存用户权限
        MenuKey menuKey1 = new MenuKey();
        MenuKey menuKey2 = new MenuKey();
        menuKey1.setTitle("首页");
        menuKey1.setHref("page/welcome.html?t=1");
        menuKey2.setTitle("后台管理");
        menuKey2.setImage("images/logo.png");
        data.put("homeInfo", menuKey1);
        data.put("logoInfo", menuKey2);
        data.put("menuInfo", rootMenu);
        return data;
    }

    @Override
    public List<PermissionEntity> treeSelect() {
        List<PermissionEntity> data = new ArrayList<>();
        //查出所有非按钮权限
        List<PermissionEntity> permissions = getAllPermissionExcludeBtn();

        //找到根节点权限
        for (PermissionEntity permission : permissions) {
            if (permission.getPId() == 0) {
                permission.setChildren(new ArrayList<>());
                data.add(permission);
            }
        }

        //获取根节点的子菜单
        for (PermissionEntity datum : data) {
            datum.getChildren().add(findTreeSelectChildren(datum, permissions));
        }

        return data;
    }

    @Override
    public void removeMenu(Integer id) {
        //获取对应id的所有子菜单id和自己本身
        List<Integer> ids = new ArrayList<>();

        findPermissionIds(id, ids);
        ids.add(id);

        //删除
        permissionService.removeByIds(ids);
    }

    private void findPermissionIds(Integer id, List<Integer> ids) {
        permissionService.list(
                        new QueryWrapper<PermissionEntity>()
                                .eq("p_id", id)
                                .select("id"))
                .forEach(
                        permission -> {
                            ids.add(permission.getId());
                            findPermissionIds(permission.getId(), ids);
                        }
                );

    }

    public PermissionEntity findTreeSelectChildren(PermissionEntity datum, List<PermissionEntity> permissionList) {
        datum.setChildren(new ArrayList<>());
        for (PermissionEntity permission : permissionList) {
            if (permission.getPId() == datum.getId()) {
                datum.getChildren().add(findTreeSelectChildren(permission, permissionList));
            }
        }
        return datum;
    }

    public List<PermissionEntity> getAllPermissionExcludeBtn() {
        LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(PermissionEntity::getIsMenu, 1);
        return permissionMapper.selectList(wrapper);
    }

    private Menu findChildrenNode(Menu menu, List<Menu> menus, HashSet<String> set) {
        menu.setChild(new ArrayList<>());
        for (Menu m : menus) {
            if (m.getPath() != null) {
                set.add(m.getPath());
            }
            if (m.getIsMenu() != 1) {
                if (menu.getId() == m.getPId()) {
                    menu.getChild().add(findChildrenNode(m, menus, set));
                }
            }
        }
        return menu;
    }
}
