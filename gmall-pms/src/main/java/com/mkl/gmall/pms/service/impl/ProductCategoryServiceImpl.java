package com.mkl.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.pms.entity.ProductCategory;
import com.mkl.gmall.pms.mapper.ProductCategoryMapper;
import com.mkl.gmall.pms.service.ProductCategoryService;
import com.mkl.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@com.alibaba.dubbo.config.annotation.Service
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements  ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChildren(Integer i) {
        List<PmsProductCategoryWithChildrenItem> items = productCategoryMapper.listCatelogWithChildren(i);
        return items;
    }
}
