package com.imooc.repository;

import com.imooc.dataobject.PackageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 21:14 2018/3/20
 */
public interface PackageInfoRepository extends JpaRepository<PackageInfo,String> {
}
