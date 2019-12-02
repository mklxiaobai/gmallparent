package com.mkl.gmall.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件
 * </p>
 *
 * @author mkl
 * @since 2019-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_category_attribute_relation")
@ApiModel(value="ProductCategoryAttributeRelation对象", description="产品的分类和属性的关系表，用于设置分类筛选条件")
public class ProductCategoryAttributeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_category_id")
    private Long productCategoryId;

    @TableField("product_attribute_id")
    private Long productAttributeId;


}
