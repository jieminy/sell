package com.imooc.seller.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.common.enums.ProductStatusEnum;
import com.imooc.common.utils.EnumUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 廖师兄
 * 2017-07-23 17:20
 */
@Data
@ApiModel(value = "ProductInfo", description = "商品")
public class ProductForm {

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

    /** 状态, 0正常1下架. */
    @ApiModelProperty("商品上下架")
    private Integer productStatus;

    /** 类目编号. */
    @ApiModelProperty("小类目id")
    private Integer smallCategoryId;


}
