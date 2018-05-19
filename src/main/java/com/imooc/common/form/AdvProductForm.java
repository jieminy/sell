package com.imooc.common.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:31 2018/5/14
 */
@Data
public class AdvProductForm {
    /**
     * 商品Id
     */
    private String productId;
    /**
     * id
     */
    protected Integer advId;

    /**
     * 图片路径
     */
    @NotNull
    protected String pic;


    /**
     * 类别id
     */
    @NotNull
    protected Integer categoryId;

    /**
     * 广告类别
     */
    protected String type = "1";


}
