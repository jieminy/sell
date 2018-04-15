package com.imooc.common.dataobject;

import com.imooc.common.enums.OrderStatusEnum;
import com.imooc.common.enums.PayStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JieMin
 * 2017-06-11 17:08
 */
@Entity
@Data
@DynamicUpdate
@ApiModel(value = "OrderMaster", description = "订单")
public class OrderMaster {

    /**
     * 订单id.
     */
    @Id
    @ApiModelProperty("订单id")
    private String orderId;

    /**
     * 买家名字.
     */
    @ApiModelProperty("姓名")
    private String buyerName;

    /**
     * 买家手机号.
     */
    @ApiModelProperty("手机号")
    private String buyerPhone;

    /**
     * 买家地址.
     */
    @ApiModelProperty("地址")
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    @ApiModelProperty("openId")
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    @ApiModelProperty("总金额")
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    @ApiModelProperty("订单状态")
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态, 默认为0未支付.
     */
    @ApiModelProperty("支付状态")
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

}
