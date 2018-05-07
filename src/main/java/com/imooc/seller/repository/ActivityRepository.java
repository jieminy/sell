package com.imooc.seller.repository;

import com.imooc.common.dataobject.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
public interface ActivityRepository extends JpaRepository<Activity, String> {
}
