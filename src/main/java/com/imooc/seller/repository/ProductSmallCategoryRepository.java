package com.imooc.seller.repository;

import com.imooc.common.dataobject.ProductSmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by JM ${date}
 */
public interface ProductSmallCategoryRepository extends JpaRepository<ProductSmallCategory,Integer> {

    List<ProductSmallCategory> findByCategoryId(Integer categoryId);
}
