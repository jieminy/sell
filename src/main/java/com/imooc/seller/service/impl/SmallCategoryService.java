package com.imooc.seller.service.impl;

import com.imooc.dataobject.ProductSmallCategory;
import com.imooc.seller.repository.ProductSmallCategoryRepository;
import com.imooc.seller.service.ISmallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JM
 */
@Service
public class SmallCategoryService implements ISmallCategoryService {
    @Autowired
    private ProductSmallCategoryRepository smallCategoryRepository;
    @Override
    public List<ProductSmallCategory> findAll() {
        return smallCategoryRepository.findAll();
    }

    @Override
    public List<ProductSmallCategory> findByProdcutCategory(Integer categoryId){
        return smallCategoryRepository.findByCategoryId(categoryId);
    }
}
