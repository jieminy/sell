package com.imooc.seller.service.impl;

import com.imooc.common.dataobject.SellerInfo;
import com.imooc.common.form.SellerInfoForm;
import com.imooc.seller.repository.SellerInfoRepository;
import com.imooc.seller.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SellerInfoForm> findall() {
        List<SellerInfo> sellerInfos = repository.findAll();
        List<SellerInfoForm> sellerInfoForms = new ArrayList<>();
        sellerInfos.forEach(sellerInfo -> {
            SellerInfoForm sellerInfoForm = new SellerInfoForm();
            BeanUtils.copyProperties(sellerInfo,sellerInfoForm);
            sellerInfoForms.add(sellerInfoForm);
        });
        return sellerInfoForms;
    }
}
