package com.imooc.common.VO;

import com.imooc.common.dataobject.Advertisement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 9:39 2018/4/14
 */
@Data
@ApiModel(value = "CategoryVO", description = "类目")
public class CategoryVO {
    /**
     * id
     */
    @ApiModelProperty("类目id")
    private Integer categoryId;

    /**
     * 父id
     */
    @ApiModelProperty("父id")
    private Integer parentId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
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

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("子类目")
    private List<CategoryVO> childCategories;

    /**
     * 广告
     */
    private List<Advertisement> advertisements;

}
