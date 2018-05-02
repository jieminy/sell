package com.imooc.buyer.repository;

import com.imooc.common.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by JieMin
 * 2017-06-11 17:24
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);
}
