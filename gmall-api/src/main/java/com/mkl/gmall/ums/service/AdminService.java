package com.mkl.gmall.ums.service;

import com.baomidou.mybatisplus.extension.service.*;
import com.mkl.gmall.ums.entity.Admin;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 * extends IService<Admin>
 */
public interface AdminService extends IService<Admin> {
    Admin login(String username, String password);

    Admin getUserInfo(String userName);
}
