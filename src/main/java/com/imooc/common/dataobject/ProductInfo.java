package com.imooc.common.dataobject;

import com.imooc.common.enums.ProductStatusEnum;
import com.imooc.common.utils.EnumUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * Created by JieMin
 * 2017-05-09 11:30
 */
@Entity
@Data
@DynamicUpdate
@ApiModel(value = "ProductInfo", description = "商品")
public class ProductInfo {

    @Id
    @Column(length = 32)
    @ApiModelProperty("商品id")
    private String productId;

    /**
     * 名字.
     */
    @Column(length = 64)
    @ApiModelProperty("商品名称")
    private String productName;

    /**
     * 单价.
     */
    @Column(length = 8, precision = 2)
    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    /**
     * 销量.
     */
    @ApiModelProperty("商品销量")
    private Integer productSales;

    /**
     * 描述.
     */
    @Column(length = 128)
    @ApiModelProperty("商品描述")
    private String productDescription;

    /**
     * 小图.
     */
    @Column(length = 512)
    @ApiModelProperty("商品图标地址")
    private String productIcon;

    /**
     * 详情图
     */
    @Column(length = 1028)
    private String detailIcons;

    /**
     * 轮播图
     */
    @Column(length = 1028)
    private String swipeIcons;

    /**
     * 重量
     */
    @Column(length = 32)
    private String weight;

    /**
     * 包装
     */
    @Column(length = 32)
    private String packing;

    /**
     * 保质期
     */
    @Column(length = 32)
    private String shelfLife;

    /**
     * 储藏方式
     */
    @Column(length = 32)
    private String storageMode;

    /**
     * 状态, 0正常1下架.
     */
    @ApiModelProperty("商品上下架")
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 小类目id.
     */
    @ApiModelProperty("小类目id")
    private Integer categoryId;

    /**
     * 活动id
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "atv_id")
    private Activity activity;

    /**
     * 创建时间.
     */
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    /**
     * 更新时间.
     */
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateTime;

    @Transient
    @ApiModelProperty(hidden = true)
    private int count = 0;

    //    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
