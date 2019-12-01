package com.mkl.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.pms.entity.AlbumPic;
import com.mkl.gmall.pms.mapper.AlbumPicMapper;
import com.mkl.gmall.pms.service.AlbumPicService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 画册图片表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class AlbumPicServiceImpl extends ServiceImpl<AlbumPicMapper, AlbumPic> implements AlbumPicService {

}
