package com.mkl.gmall.pms.service;

import com.mkl.gmall.pms.entity.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mkl.gmall.vo.PageInfoVo;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum);
}
