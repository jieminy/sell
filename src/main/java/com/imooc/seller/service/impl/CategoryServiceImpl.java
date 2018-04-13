package com.imooc.seller.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.seller.repository.CategoryRepository;
import com.imooc.seller.repository.ProductCategoryRepository;
import com.imooc.seller.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 * Created by 廖师兄
 * 2017-05-09 10:16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList) {
        return repository.findByCategoryIdIn(categoryIdList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public void delete(Integer categoryId) {
        repository.delete(categoryId);
    }
}
