package com.imooc.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:12 2018/3/25
 */
@Data
@ApiModel(value = "SellerInfoForm", description = "用户")
public class SellerInfoForm {

        @ApiModelProperty("用户id")
        private String sellerId;

        @ApiModelProperty("用户名")
        @NotNull
        private String username;

        @ApiModelProperty("密码")
        @NotNull
        private String password;

        @ApiModelProperty("是否为超级管理员")
        private boolean admin;


}
