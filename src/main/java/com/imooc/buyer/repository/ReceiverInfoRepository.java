package com.imooc.buyer.repository;

import com.imooc.dataobject.ReceiverInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:35 2018/4/1
 */
public interface ReceiverInfoRepository extends JpaRepository<ReceiverInfo,String> {
    List<ReceiverInfo> findAllByOpenid(String openid);
    void deleteByRecIdAndOpenid(String recId,String openid);
}
