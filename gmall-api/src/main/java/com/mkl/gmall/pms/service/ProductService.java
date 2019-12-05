package com.mkl.gmall.pms.service;

import com.mkl.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mkl.gmall.vo.PageInfoVo;
import com.mkl.gmall.vo.product.PmsProductParam;
import com.mkl.gmall.vo.product.PmsProductQueryParam;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
public interface ProductService extends IService<Product> {
//    根据复杂查询条件返回数据
    Product productInfo(Long id);
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);


    void saveProduct(PmsProductParam productParam);

    void updatePublishStatus(List<Long> ids, Integer publishStatus);
}
