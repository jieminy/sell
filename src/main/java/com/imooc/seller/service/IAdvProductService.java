package com.imooc.seller.service;

import com.imooc.common.dataobject.AdvProduct;

import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:24 2018/5/14
 */
public interface IAdvProductService {
    List<AdvProduct> findAll();

    void save(AdvProduct advProduct);

    void delete(Integer advId);

    AdvProduct getById(Integer advId);
}
