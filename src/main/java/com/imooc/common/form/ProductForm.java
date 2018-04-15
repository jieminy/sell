package com.imooc.common.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by JieMIn
 * 2017-07-23 17:20
 */
@Data
@ApiModel(value = "ProductInfo", description = "商品")
public class ProductForm {

    @ApiModelProperty("商品id")
    private String productId;

    /** 名字. */
    @ApiModelProperty("商品名称")
    @NotNull
    private String productName;

    /** 单价. */
    @ApiModelProperty("商品单价")
    @NotNull
    private BigDecimal productPrice;

    /** 库存. */
    @ApiModelProperty("商品销量")
    @NotNull
    private Integer productSales;

    /** 描述. */
    @ApiModelProperty("商品描述")
    private String productDescription;

    /** 小图. */
    @ApiModelProperty("商品图标地址")
    private String productIcon;

    /** 状态, 0正常1下架. */
    @ApiModelProperty("商品上下架")
    private Integer productStatus;

    @ApiModelProperty("类目id")
    @NotNull
    private Integer categoryId;


}
