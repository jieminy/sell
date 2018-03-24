package com.imooc.buyer.repository;

import com.imooc.dataobject.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:41 2018/3/23
 */
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
