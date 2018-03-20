package com.imooc.service;

import com.imooc.dataobject.PackageCategory;

import java.util.List;

/**
 * @Author: JieMin
 * @Description: 组合套餐类别service
 * @Date: created in 21:21 2018/3/20
 */
public interface IPackageCategoryService {
    PackageCategory selectByKey(Integer categoryId);
    List<PackageCategory> findAll();
    int insert(PackageCategory packageCategory);
    int update(PackageCategory packageCategory);
    int delete(PackageCategory packageCategory);
}
