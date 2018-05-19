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

    /** 描述. */
    @ApiModelProperty("商品描述")
    private String productDescription;

    /** 小图. */
    @ApiModelProperty("商品图标地址")
    private String productIcon;


    @ApiModelProperty("类目id")
    @NotNull
    private Integer categoryId;

    /**
     * 详情图
     */
    @ApiModelProperty("详情图")
    private String detailIcons;

    /**
     * 轮播图
     */
    @ApiModelProperty("轮播图")
    private String swipeIcons;

    /**
     * 重量
     */
    @ApiModelProperty("重量")
    private String weight;

    /**
     * 包装
     */
    @ApiModelProperty("包装")
    private String packing;

    /**
     * 保质期
     */
    @ApiModelProperty("保质期")
    private String shelfLife;

    /**
     * 储藏方式
     */
    @ApiModelProperty("储藏方式")
    private String storageMode;

    @ApiModelProperty("活动id")
    private String atvId;


}
