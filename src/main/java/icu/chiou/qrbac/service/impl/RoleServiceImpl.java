package icu.chiou.qrbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.chiou.qrbac.entity.RoleEntity;
import icu.chiou.qrbac.entity.Tree;
import icu.chiou.qrbac.mapper.RoleMapper;
import icu.chiou.qrbac.service.PermissionService;
import icu.chiou.qrbac.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author redvelet
 * @since 2024-02-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    PermissionService permissionService;

    @Override
    public List<Tree> getAuthorizeTreeList() {
        //获取所有权限
        List<Tree> data = new ArrayList<>();

        List<Tree> treeList = new ArrayList<>();
        permissionService.list().forEach(permission -> {
            Tree tree = new Tree();
            BeanUtils.copyProperties(permission, tree);
            tree.setTitle(permission.getName());
            tree.setSpread(true);
            treeList.add(tree);
        });

        for (Tree tree : treeList) {
            if (tree.getPId() == 0) {
                tree.setChildren(new ArrayList<>());
                data.add(tree);
            }
        }

        for (Tree datum : data) {
            datum.getChildren().add(findChildren(datum, treeList));
        }

        return data;
    }

    private Tree findChildren(Tree datum, List<Tree> treeList) {
        datum.setChildren(new ArrayList<>());
        for (Tree tree : treeList) {
            if (Objects.equals(tree.getPId(), datum.getId())) {
                datum.getChildren().add(findChildren(tree, treeList));
            }
        }
        return datum;
    }
}
