package com.imooc.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by JieMin
 * 2017-06-18 23:31
 */
@Data
@ApiModel(value = "OrderForm", description = "订单")
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    @ApiModelProperty("地址")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    @ApiModelProperty("openId")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空")
    private BigDecimal orderAmount;

    /**
     * 配送时间
     */
    @NotNull(message = "配送时间不能为空")
    private String distributeTime;

    /**
     * 配送方式
     */
    @NotNull(message = "配送方式不能为空")
    private Integer distributeType;

    /**
     * 运费
     */
    private BigDecimal freight = new BigDecimal(0);
}
