package com.mkl.gmall.pms.entity;

import java.math.BigDecimal;
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
 * 产品阶梯价格表(只针对同商品)
 * </p>
 *
 * @author mkl
 * @since 2019-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_ladder")
@ApiModel(value="ProductLadder对象", description="产品阶梯价格表(只针对同商品)")
public class ProductLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @ApiModelProperty(value = "满足的商品数量")
    @TableField("count")
    private Integer count;

    @ApiModelProperty(value = "折扣")
    @TableField("discount")
    private BigDecimal discount;

    @ApiModelProperty(value = "折后价格")
    @TableField("price")
    private BigDecimal price;


}
