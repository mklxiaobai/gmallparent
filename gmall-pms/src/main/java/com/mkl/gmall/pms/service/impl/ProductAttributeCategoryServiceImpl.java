package com.mkl.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.pms.entity.ProductAttributeCategory;
import com.mkl.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.mkl.gmall.pms.service.ProductAttributeCategoryService;
import com.mkl.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {
    @Autowired
    ProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public PageInfoVo roductAttributeCategoryPageInfo(Integer pageNum, Integer pageSize) {
        IPage<ProductAttributeCategory> productAttributeCategoryIPage = productAttributeCategoryMapper.selectPage(new Page<ProductAttributeCategory>(pageNum, pageSize),null);
        return PageInfoVo.getVo(productAttributeCategoryIPage,pageSize.longValue());
    }
}
