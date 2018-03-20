package com.imooc.service;

import com.imooc.dataobject.PackageInfo;

import java.util.List;

/**
 * @Author: JieMin
 * @Description: 组合套餐service
 * @Date: created in 21:15 2018/3/20
 */
public interface IPackageInfoService {
    PackageInfo selectByKey(String packageId);
    List<PackageInfo> findAll();
    int insert(PackageInfo packageInfo);
    int update(PackageInfo packageInfo);
    int delete(PackageInfo packageInfo);

}
