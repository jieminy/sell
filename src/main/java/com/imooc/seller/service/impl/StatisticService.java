package com.imooc.seller.service.impl;

import com.imooc.buyer.repository.OrderMasterRepository;
import com.imooc.common.VO.StaProductVO;
import com.imooc.seller.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/5/8
 */
@Service
public class StatisticService implements IStatisticService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public List<StaProductVO> getProductNum(String stDate, String edDate) {
        return orderMasterRepository.statisticProNum(stDate, edDate);
    }
}
