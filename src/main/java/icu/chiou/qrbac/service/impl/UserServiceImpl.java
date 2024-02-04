package icu.chiou.qrbac.service.impl;

import icu.chiou.qrbac.entity.UserEntity;
import icu.chiou.qrbac.mapper.UserMapper;
import icu.chiou.qrbac.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author redvelet
 * @since 2024-01-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
