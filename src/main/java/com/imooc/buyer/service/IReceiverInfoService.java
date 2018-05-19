package com.imooc.buyer.service;

import com.imooc.common.dataobject.ReceiverInfo;

import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:45 2018/4/1
 */
public abstract class IReceiverInfoService {
    public abstract void save (ReceiverInfo receiverInfo);

    public abstract List<ReceiverInfo> findAll(String openid);

    public abstract void delete(String recId, String openid);

    public abstract ReceiverInfo findOne(String recId);
}
