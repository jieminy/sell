package com.imooc.seller.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.common.enums.ProductStatusEnum;
import com.imooc.common.utils.EnumUtil;
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
public class ProductForm {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productSales;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer smallCategoryId;


}
