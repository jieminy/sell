package com.imooc.seller.repository;

import com.imooc.dataobject.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author itw_yinjm
 * @date 2018/4/13
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
