package icu.chiou.qrbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.chiou.qrbac.entity.RoleEntity;
import icu.chiou.qrbac.entity.Tree;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author redvelet
 * @since 2024-02-04
 */
public interface RoleService extends IService<RoleEntity> {

    List<Tree> getAuthorizeTreeList();

}
