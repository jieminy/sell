package com.imooc.buyer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.dataobject.OrderDetail;
import com.imooc.common.enums.OrderStatusEnum;
import com.imooc.common.enums.PayStatusEnum;
import com.imooc.common.utils.EnumUtil;
import com.imooc.common.utils.serializer.Date2LongSerializer;
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

    /** 创建时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(hidden = true)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
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
}
