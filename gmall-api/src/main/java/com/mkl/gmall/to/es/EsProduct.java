package com.mkl.gmall.to.es;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class EsProduct  implements Serializable {

    private static final long serialVersionUID = -1L;
    private Long id;  //spuId
    private String productSn;
    private Long brandId;
    private String brandName;

    private Long productCategoryId;
    private String productCategoryName;
    private String pic;
    private String name;//这是需要检索的字段 分词
    private String subTitle;//也可以检索，这是一个加分字段
    private String keywords;// 也可以检索，这是一个加分字段
    private BigDecimal price;//sku-price；
    private Integer sale;//sku-sale
    private Integer newStatus;//
    private Integer recommandStatus;//
    private Integer stock;//sku-stock
    private Integer promotionType;//促销类型
    private Integer sort;//排序
    private Integer commentCount;//评论数量共享spu


    //嵌入式对象的Mapping一定要用nested声明，这样才能正确的检索到数据
    private List<EsProductAttributeValue> attrValueList;//商品的筛选属性(SPU的属性);

    //sku也要供搜索
    private List<EsSkuProductInfo> skuProductInfos;//商品的sku信息




}
