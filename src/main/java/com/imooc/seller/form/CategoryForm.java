package com.imooc.seller.form;

import com.imooc.dataobject.ProductSmallCategory;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-07-23 21:43
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "categoryId")
    private List<ProductSmallCategory> productSmallCategories = new ArrayList<>();

}
