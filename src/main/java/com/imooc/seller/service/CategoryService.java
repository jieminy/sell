package com.imooc.seller.service;

import com.imooc.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * Created by 廖师兄
 * 2017-05-09 10:12
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);

    ProductCategory save(ProductCategory productCategory);

    void delete(Integer categoryId);
}
