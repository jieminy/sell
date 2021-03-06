package com.imooc.seller.service;

import com.imooc.common.dataobject.ProductInfo;
import com.imooc.common.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by 廖师兄
 * 2017-05-09 17:27
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findOffAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加销量
    void increaseSales(List<CartDTO> cartDTOList);

    //减销量
    void decreaseSales(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);

    void delete(String productId);

    List<ProductInfo> findByCategoryId(Integer categoryId);

    List<ProductInfo> findByActivityId(String atvId);
}
