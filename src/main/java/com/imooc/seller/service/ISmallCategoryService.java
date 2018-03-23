package com.imooc.seller.service;

import com.imooc.dataobject.ProductSmallCategory;

import java.util.List;

/**
 * Created by JM ${date}
 */
public interface ISmallCategoryService {
    List<ProductSmallCategory> findAll();
    List<ProductSmallCategory> findByProdcutCategory(Integer categoryId);
}
