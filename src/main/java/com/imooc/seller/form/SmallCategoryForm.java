package com.imooc.seller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by JieMin
 * 2017-06-18 23:31
 */
@Data
@ApiModel(value = "SmallCategoryForm", description = "小类目对象")
public class SmallCategoryForm {

    /** 小类目Id */
    @ApiModelProperty(value = "小类目id")
    private Integer smallCategoryId;
    /** 小类目名称 */
    @ApiModelProperty(value = "小类目名称")
    private String smallCategoryName;
    /** 小类目图标 */
    @ApiModelProperty(value = "小类目图片")
    private String smallPic;
    /** 所属大类目类别 */
    @ApiModelProperty(value = "所属类目id")
    @NotNull
    private Integer categoryId;


}
