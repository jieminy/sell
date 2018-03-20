package com.imooc.service.impl;

import com.imooc.dataobject.PackageCategory;
import com.imooc.repository.PackageCategoryRepository;
import com.imooc.service.IPackageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 21:22 2018/3/20
 */
@Service
public class PackageCategoryService implements IPackageCategoryService {
    @Autowired
    private PackageCategoryRepository packageCategoryRepository;
    @Override
    public PackageCategory selectByKey(Integer categoryId) {
        return null;
    }

    @Override
    public List<PackageCategory> findAll() {
        return packageCategoryRepository.findAll();
    }

    @Override
    public int insert(PackageCategory packageCategory) {
        return 0;
    }

    @Override
    public int update(PackageCategory packageCategory) {
        return 0;
    }

    @Override
    public int delete(PackageCategory packageCategory) {
        return 0;
    }
}
