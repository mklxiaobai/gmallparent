package com.mkl.gmall.pms.service;

import com.mkl.gmall.pms.entity.ProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mkl.gmall.vo.PageInfoVo;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {

    PageInfoVo roductAttributeCategoryPageInfo(Integer pageNum, Integer pageSize);
}
