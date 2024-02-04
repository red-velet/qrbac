package icu.chiou.qrbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.chiou.qrbac.entity.PermissionEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author redvelet
 * @since 2024-01-24
 */
public interface PermissionService extends IService<PermissionEntity> {

    Map<String, Object> getUserMenuList(HttpServletRequest request);

    List<PermissionEntity> treeSelect();

    void removeMenu(Integer id);
}
