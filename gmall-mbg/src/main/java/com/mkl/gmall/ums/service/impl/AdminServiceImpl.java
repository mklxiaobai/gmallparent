package com.mkl.gmall.ums.service.impl;

import com.mkl.gmall.ums.entity.Admin;
import com.mkl.gmall.ums.mapper.AdminMapper;
import com.mkl.gmall.ums.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
