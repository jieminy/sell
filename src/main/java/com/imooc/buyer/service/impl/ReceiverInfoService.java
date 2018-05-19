package com.imooc.buyer.service.impl;

import com.imooc.buyer.repository.ReceiverInfoRepository;
import com.imooc.buyer.service.IReceiverInfoService;
import com.imooc.common.dataobject.ReceiverInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:45 2018/4/1
 */
@Service
public class ReceiverInfoService extends IReceiverInfoService {
    @Autowired
    private ReceiverInfoRepository receiverInfoRepository;
    @Override
    public void save (ReceiverInfo receiverInfo){

        receiverInfoRepository.save(receiverInfo);
    }

    @Override
    public List<ReceiverInfo> findAll(String openid){
        return receiverInfoRepository.findAllByOpenid(openid);
    }

    @Override
    public void delete(String recId ,String openid){
        receiverInfoRepository.deleteByRecIdAndOpenid(recId, openid);
    }

    @Override
    public ReceiverInfo findOne(String recId) {
        return receiverInfoRepository.findOne(recId);
    }
}
