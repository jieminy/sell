package com.imooc.service.impl;

import com.imooc.dataobject.PackageInfo;
import com.imooc.service.IPackageInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 21:16 2018/3/20
 */
@Service
public class PackageInfoService implements IPackageInfoService {
    @Override
    public PackageInfo selectByKey(String packageId){
         return null;
     }

    @Override
    public List<PackageInfo> findAll() {
        return null;
    }

    @Override
    public int insert(PackageInfo packageInfo) {
        return 0;
    }

    @Override
    public int update(PackageInfo packageInfo) {
        return 0;
    }

    @Override
    public int delete(PackageInfo packageInfo) {
        return 0;
    }
}
