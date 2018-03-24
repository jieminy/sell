package com.imooc.dataobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@DynamicUpdate
@Data
@ApiModel(value = "ProductCategory", description = "商品类目")
public class ProductCategory{

    /** 类目id. */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名字. */
    @ApiModelProperty(value = "类目名称")
    private String categoryName;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "categoryId")
//    @Transient
    @ApiModelProperty(hidden = true)
    private List<ProductSmallCategory> productSmallCategories = new ArrayList<>();

    public ProductCategory() {
    }

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
