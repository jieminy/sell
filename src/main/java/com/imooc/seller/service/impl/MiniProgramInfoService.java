package com.imooc.seller.service.impl;

import com.imooc.common.dataobject.MiniProgramInfo;
import com.imooc.seller.repository.MiniProgramInfoRepository;
import com.imooc.seller.service.IMiniProgramInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:14 2018/7/8
 */
@Service
public class MiniProgramInfoService implements IMiniProgramInfoService {
    @Autowired
    private MiniProgramInfoRepository miniProgramInfoRepository;


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public MiniProgramInfo findOne(String id) {
        return miniProgramInfoRepository.findOne(id);
    }

    @Override
    public void update(MiniProgramInfo miniProgramInfo) {
        miniProgramInfoRepository.save(miniProgramInfo);
    }
}
