package com.mkl.gmall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.ums.entity.Role;
import com.mkl.gmall.ums.mapper.RoleMapper;
import com.mkl.gmall.ums.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
