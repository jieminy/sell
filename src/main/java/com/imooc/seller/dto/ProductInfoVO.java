package com.imooc.seller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 17:38 2018/3/31
 */
@Data
@ApiModel(value = "ProductInfo", description = "商品")
public class ProductInfoVO {
    @ApiModelProperty("商品id")
    private String productId;

    /** 名字. */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 单价. */
    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    /** 库存. */
    @ApiModelProperty("商品销量")
    private Integer productSales;

    /** 描述. */
    @ApiModelProperty("商品描述")
    private String productDescription;

    /** 小图. */
    @ApiModelProperty("商品图标地址")
    private String productIcon;

    private Integer smallCategoryId;

    private Integer categoryId;

    @ApiModelProperty("小类目")
    private String smallCategoryName;

    @ApiModelProperty("类目")
    private String categoryName;
}
