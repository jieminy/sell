package com.imooc.buyer.repository;

import com.imooc.common.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by JieMin
 * 2017-06-11 17:24
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);

    @Query(value = "select max(order_code) " +
            "from order_master " +
            "where buyer_openid = :openid " +
            "and DATE_FORMAT (update_time,'Y%-m%-d%') = DATE_FORMAT(NOW(),'Y%-m%-d%')", nativeQuery = true)
    Integer findMaxOrderCode(@Param("openid") String openid);
}
