package com.imooc.repository;

import com.imooc.dataobject.PackageCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 21:13 2018/3/20
 */
public interface PackageCategoryRepository extends JpaRepository<PackageCategory,Integer> {
}
