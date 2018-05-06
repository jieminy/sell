package com.imooc.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.common.dataobject.OrderDetail;
import com.imooc.common.enums.DistributeEnum;
import com.imooc.common.enums.OrderStatusEnum;
import com.imooc.common.enums.PayStatusEnum;
import com.imooc.common.utils.EnumUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-11 18:30
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "OrderDTO", description = "订单")
public class OrderDTO {

    /** 订单id. */
    @ApiModelProperty("订单id")
    private String orderId;

    /** 买家名字. */
    @ApiModelProperty("买家名字")
    private String buyerName;

    /** 买家手机号. */
    @ApiModelProperty("手机号")
    private String buyerPhone;

    /** 买家地址. */
    @ApiModelProperty("地址")
    private String buyerAddress;

    /** 买家微信Openid. */
    @ApiModelProperty("openId")
    private String buyerOpenid;

    /** 订单总金额. */
    @ApiModelProperty("总金额")
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    @ApiModelProperty("支付状态")
    private Integer payStatus;

    /**
     * 配送时间
     */
    @ApiModelProperty("配送时间")
    private String distributeTime;

    /**
     * 配送方式
     */
    @ApiModelProperty("配送方式")
    private Integer distributeType;

    /**
     * 取货码
     */
    @ApiModelProperty("取货码")
    private Integer orderCode;

    /** 创建时间. */
//    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(hidden = true)
    private Date createTime;

    /** 更新时间. */
//    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty("详情")
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

    @JsonIgnore
    public DistributeEnum getDistributeEnum() {
        return EnumUtil.getByCode(distributeType, DistributeEnum.class);
    }

}
