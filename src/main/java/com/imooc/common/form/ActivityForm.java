package com.imooc.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
@Data
@ApiModel("活动")
public class ActivityForm {
    /**
     * 活动id
     */
    @ApiModelProperty("活动id")
    private String atvId;

    /**
     * 活动类型
     */
    @ApiModelProperty("活动类型")
    private Integer type;

    /**
     * 折扣
     */
    @ApiModelProperty("折扣")
    private BigDecimal discount;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String des;

    /**
     * 小图标
     */
    @ApiModelProperty("小图标")
    private String icon;

}
