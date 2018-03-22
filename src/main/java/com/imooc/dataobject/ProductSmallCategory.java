package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@Entity
@DynamicUpdate
public class ProductSmallCategory {
    @Id
    @GeneratedValue
    /** 小类目Id */
    @Length(max = 11)
    private Integer smallCategoryId;
    /** 小类目名称 */
    @Length(max = 64)
    private String smallCategoryName;
    /** 小类目图标 */
    @Length(max = 512)
    private String smallPic;
    /** 所属大类目类别 */
    @Length(max = 11)
    private Integer categoryType;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "smallCategoryType")
    List<ProductInfo> productInfos;
    private Date createTime;
    private Date updateTime;
}
