package com.mkl.gmall.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.ums.entity.Admin;
import com.mkl.gmall.ums.mapper.AdminMapper;
import com.mkl.gmall.ums.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@com.alibaba.dubbo.config.annotation.Service
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<Admin> wrapper = new QueryWrapper<Admin>().eq("username", username).eq("password", md5password);
        Admin admin=adminMapper.selectOne(wrapper);
        return admin;
    }

//    获取用户详情
    @Override
    public Admin getUserInfo(String userName) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",userName));
    }
}
