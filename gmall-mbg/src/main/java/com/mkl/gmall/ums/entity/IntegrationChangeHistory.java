package com.mkl.gmall.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 积分变化历史记录表
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ums_integration_change_history")
@ApiModel(value="IntegrationChangeHistory对象", description="积分变化历史记录表")
public class IntegrationChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "改变类型：0->增加；1->减少")
    @TableField("change_type")
    private Integer changeType;

    @ApiModelProperty(value = "积分改变数量")
    @TableField("change_count")
    private Integer changeCount;

    @ApiModelProperty(value = "操作人员")
    @TableField("operate_man")
    private String operateMan;

    @ApiModelProperty(value = "操作备注")
    @TableField("operate_note")
    private String operateNote;

    @ApiModelProperty(value = "积分来源：0->购物；1->管理员修改")
    @TableField("source_type")
    private Integer sourceType;


}
