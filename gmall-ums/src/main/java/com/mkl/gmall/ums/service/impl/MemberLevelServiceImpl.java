package com.mkl.gmall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.ums.entity.MemberLevel;
import com.mkl.gmall.ums.mapper.MemberLevelMapper;
import com.mkl.gmall.ums.service.MemberLevelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {

}
