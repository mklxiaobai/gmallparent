package com.mkl.gmall.vo.product;


import com.mkl.gmall.pms.entity.ProductCategory;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 */
@Data
@ToString
public class PmsProductCategoryWithChildrenItem extends ProductCategory  implements Serializable {



    private List<ProductCategory> children;

}
