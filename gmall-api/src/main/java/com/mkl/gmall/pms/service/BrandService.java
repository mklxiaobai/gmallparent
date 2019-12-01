package com.mkl.gmall.pms.service;

import com.mkl.gmall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mkl.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
public interface BrandService extends IService<Brand> {

    PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
