package com.imooc.seller.service;

import com.imooc.common.dataobject.MiniProgramInfo;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:13 2018/7/8
 */
public interface IMiniProgramInfoService {
    MiniProgramInfo findOne(String id);

    void update(MiniProgramInfo miniProgramInfo);
}
