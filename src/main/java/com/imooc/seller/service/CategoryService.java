package com.imooc.seller.service;

import com.imooc.common.dataobject.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 类目
 * Created by JieMin
 * 2017-05-09 10:12
 */
public interface CategoryService {

    Category findOne(Integer categoryId);

    List<Category> findAll();

    List<Category> findByParent(Integer parentId);

    List<Category> findByLevel(int level, Integer rootId, Pageable pageable);

    List<Category> findByLevel(int level, Integer rootId);

    List<Category> findByCategoryIdIn(List<Integer> categoryIdList);

    Category save(Category category);

    void delete(Integer categoryId);


}
