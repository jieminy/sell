package com.imooc.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by JieMin
 * 2017-06-18 23:31
 */
@Data
@ApiModel(value = "CategoryForm", description = "类目对象")
public class CategoryForm {

    /**
     * id
     */
    @ApiModelProperty("类目id")
    private Integer categoryId;

    /**
     * 父id
     */
    @ApiModelProperty("父id")
    @NotNull
    private Integer parentId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @NotNull
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String des;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String pic;


}
