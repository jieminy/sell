package com.imooc.seller.service.impl;

import com.imooc.buyer.repository.OrderMasterRepository;
import com.imooc.common.VO.StaProductVO;
import com.imooc.seller.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Object[]> staProducts = orderMasterRepository.statisticProNum(stDate, edDate);
        List<StaProductVO> staProductVOS = new ArrayList<>();
        staProducts.forEach(sta -> {
            StaProductVO staProductVO = new StaProductVO();
            staProductVO.setProductId(sta[0].toString());
            staProductVO.setProductName(sta[1].toString());
            staProductVO.setNum(sta[2].toString());
            staProductVOS.add(staProductVO);
        });
        return staProductVOS;
    }
}
