package com.imooc.seller.service.impl;

import com.imooc.common.dataobject.AdvProduct;
import com.imooc.seller.repository.AdvProductRepository;
import com.imooc.seller.repository.CategoryRepository;
import com.imooc.seller.service.IAdvProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:24 2018/5/14
 */
@Service
public class AdvProductService implements IAdvProductService {
    @Autowired
    private AdvProductRepository advProductRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<AdvProduct> findAll() {
        List<AdvProduct> advProducts = advProductRepository.findAll();
        advProducts.forEach(advProduct ->
                advProduct.setCategoryName(categoryRepository.getOne(advProduct.getCategoryId()).getName())
        );
        return advProducts;
    }

    @Override
    public void save(AdvProduct advProduct) {
        advProductRepository.save(advProduct);
    }

    @Override
    public void delete(Integer advId) {
        advProductRepository.delete(advId);
    }

    @Override
    public AdvProduct getById(Integer advId) {
        return advProductRepository.getOne(advId);
    }
}
