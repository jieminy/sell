package com.imooc.seller.service;

import com.imooc.common.VO.StaProductVO;

import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/5/8
 */
public interface IStatisticService {
    List<StaProductVO> getProductNum(String stDate, String edDate);
}
