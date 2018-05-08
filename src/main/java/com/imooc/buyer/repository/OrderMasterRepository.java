package com.imooc.buyer.repository;

import com.imooc.common.VO.StaProductVO;
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

    @Query(value = "SELECT * " +
            "FROM torder_master " +
            "WHERE DATE_FORMAT(update_time, 'Y%-m%-%d') >= :stDate " +
            "AND DATE_FORMAT(update_time, 'Y%-m%-%d') <= :edDate " +
            "AND order_status = :orderStatus " +
            "AND pay_status = :payStatus", nativeQuery = true)
    List<OrderMaster> statistic(@Param("stDate") String stDate,
                                @Param("edDate") String edDate,
                                @Param("orderStatus") Integer orderStatus,
                                @Param("payStatus") Integer payStatus);

    @Query(value = "SELECT\n" +
            "\tproduct_id,\n" +
            "\tproduct_name,\n" +
            "\tcount(*) num\n" +
            "FROM\n" +
            "\torder_master om\n" +
            "LEFT JOIN order_detail od ON od.order_id = om.order_id\n" +
            "WHERE\n" +
            "\tDATE_FORMAT(update_time, '%Y-%m-%d') >= '2018-05-01'\n" +
            "AND DATE_FORMAT(update_time, '%Y-%m-%d') <= '2018-05-01'\n" +
            "AND order_status = '1'\n" +
            "AND pay_status = '1'\n" +
            "GROUP BY\n" +
            "\tproduct_id,\n" +
            "\tproduct_name", nativeQuery = true)
    List<StaProductVO> statisticProNum(@Param("stDate") String stDate,
                                       @Param("edDate") String edDate);
}
