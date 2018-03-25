package com.imooc.dataobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Jiemin
 * 2017-06-11 17:20
 */
@Entity
@Data
@ApiModel(value = "OrderDetail", description = "订单详情")
public class OrderDetail {

    @Id
    @Column(length = 32)
    @ApiModelProperty("详情id")
    private String detailId;

    /** 订单id. */
    @Column(length = 32)
    @ApiModelProperty("订单id")
    private String orderId;

    /** 商品id. */
    @Column(length = 32)
    @ApiModelProperty("商品id")
    private String productId;

    /** 商品名称. */
    @Column(length = 64)
    @ApiModelProperty("商品名")
    private String productName;

    /** 商品单价. */
    @ApiModelProperty("单价")
    @Column(length = 8,precision = 2)
    private BigDecimal productPrice;

    /** 商品数量. */
    @ApiModelProperty("数量")
    private Integer productQuantity;

    /** 商品小图. */
    @Column(length = 512)
    @ApiModelProperty("小图")
    private String productIcon;
}
