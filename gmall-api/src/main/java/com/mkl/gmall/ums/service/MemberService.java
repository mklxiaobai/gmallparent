package com.mkl.gmall.ums.service;

import com.mkl.gmall.ums.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
public interface MemberService extends IService<Member> {

    Member login(String username, String password);
}
