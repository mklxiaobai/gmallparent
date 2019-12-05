package com.mkl.gmall.pms.mapper;

import com.mkl.gmall.pms.entity.ProductAttribute;
import com.mkl.gmall.pms.entity.ProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mkl.gmall.to.es.EsProductAttributeValue;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 Mapper 接口
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValue> {

    List<EsProductAttributeValue> selectProductBaseAttrAndValue(Long id);

    List<ProductAttribute> selectProductSaleAttrName(Long id);
}
