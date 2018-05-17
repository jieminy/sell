package com.imooc.common.VO;

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

    @ApiModelProperty("详情图")
    private String detailIcons;

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

    @ApiModelProperty(hidden = true)
    private Integer parentCategoryId;

    @ApiModelProperty("类目id")
    private Integer categoryId;

    @ApiModelProperty(name = "根类目", hidden = true)
    private String parentCategoryName;

    @ApiModelProperty(name = "类目", hidden = true)
    private String categoryName;

    private int count = 0;
}
