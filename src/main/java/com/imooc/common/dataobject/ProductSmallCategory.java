package com.imooc.common.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicUpdate
public class ProductSmallCategory {
    @Id
    @GeneratedValue
    /** 小类目Id */
    private Integer smallCategoryId;
    /**
     * 小类目名称
     */
    private String smallCategoryName;
    /**
     * 小类目图标
     */
    private String smallPic;
    /**
     * 所属大类目类别
     */
    private Integer categoryId;

    //    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "smallCategoryId")
    @Transient
    private List<ProductInfo> productInfos = new ArrayList<>();

    private Date createTime;
    private Date updateTime;
}
