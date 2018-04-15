package com.imooc.seller.repository;

import com.imooc.common.dataobject.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/4/13
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByParentId(Integer parentId, Pageable pageable);

    List<Category> findByParentId(Integer parentId);

    List<Category> findByCategoryIdIn(List<Integer> categoryIdList);
}
