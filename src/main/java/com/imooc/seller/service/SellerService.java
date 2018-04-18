package com.imooc.seller.service;

import com.imooc.common.dataobject.SellerInfo;
import com.imooc.common.form.SellerInfoForm;

import java.util.List;

/**
 * 卖家端
 * Created by 廖师兄
 * 2017-07-29 23:14
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerUsername(String openid);

    SellerInfo save(SellerInfo sellerInfo);

    void delete(String sellerId);

    List<SellerInfoForm> findall();

}
