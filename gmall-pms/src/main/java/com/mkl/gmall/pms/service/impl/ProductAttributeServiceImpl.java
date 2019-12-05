package com.mkl.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.pms.entity.ProductAttribute;
import com.mkl.gmall.pms.mapper.ProductAttributeMapper;
import com.mkl.gmall.pms.service.ProductAttributeService;
import com.mkl.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {
    @Autowired
    ProductAttributeMapper productAttributeMapper;

    @Override
    public PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        QueryWrapper<ProductAttribute> productAttributeQueryWrapper = new QueryWrapper<ProductAttribute>()
                .eq("product_attribute_category_id",cid)
                .eq("type",type);
        IPage<ProductAttribute> productAttributeIPage = productAttributeMapper.selectPage(new Page<ProductAttribute>(pageNum, pageSize), productAttributeQueryWrapper);
        return PageInfoVo.getVo(productAttributeIPage,pageSize.longValue());
    }
}
