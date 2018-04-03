package com.imooc.seller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by JieMin
 * 2017-06-18 23:31
 */
@Data
@ApiModel(value = "CategoryForm", description = "类目对象")
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    @ApiModelProperty(value = "类目名称")
    private String categoryName;


}
