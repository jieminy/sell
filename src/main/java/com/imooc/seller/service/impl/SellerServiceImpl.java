package com.imooc.seller.service.impl;

import com.imooc.dataobject.SellerInfo;
import com.imooc.seller.repository.SellerInfoRepository;
import com.imooc.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 廖师兄
 * 2017-07-29 23:15
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return repository.save(sellerInfo);
    }

    @Override
    public void delete(String sellerId){
         repository.delete(sellerId);
    }
}
