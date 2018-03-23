package com.imooc.seller.form;

import com.imooc.dataobject.ProductInfo;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JM @Date
 */
public class SmallCategoryForm {

    /** 小类目Id */
    private Integer smallCategoryId;
    /** 小类目名称 */
    private String smallCategoryName;
    /** 小类目编号 */
    private Integer smallCategoryType;
    /** 小类目图标 */
    private String smallPic;
    /** 所属大类目类别 */
    private Integer categoryId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "smallCategoryId")
    private List<ProductInfo> productInfos = new ArrayList<>();

}
