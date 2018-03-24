package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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
    /** 小类目名称 */
    private String smallCategoryName;
    /** 小类目图标 */
    private String smallPic;
    /** 所属大类目类别 */
    private Integer categoryId;

//    @Transient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "smallCategoryId")
    private List<ProductInfo> productInfos = new ArrayList<>();

    private Date createTime;
    private Date updateTime;
}
