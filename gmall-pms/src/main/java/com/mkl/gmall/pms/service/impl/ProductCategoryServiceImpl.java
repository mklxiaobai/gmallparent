package com.mkl.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.constant.SysCacheConstant;
import com.mkl.gmall.pms.entity.ProductCategory;
import com.mkl.gmall.pms.mapper.ProductCategoryMapper;
import com.mkl.gmall.pms.service.ProductCategoryService;
import com.mkl.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
@Slf4j
@com.alibaba.dubbo.config.annotation.Service
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements  ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChildren(Integer i) {
        Object cachemenu = redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);
        List<PmsProductCategoryWithChildrenItem> items;
        if(cachemenu!=null){ ;
            log.info("从缓存中取数");
            System.out.println("-----------------------------");
            items =(List<PmsProductCategoryWithChildrenItem>) cachemenu;
        }else {
            items = productCategoryMapper.listCatelogWithChildren(i);
            redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY,items);
        }
        return items;
    }
}
