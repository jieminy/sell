package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.VO.StaProductVO;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.seller.service.IStatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/5/8
 */
@RestController
@RequestMapping("/statistic")
@Api("统计接口")
public class StatisticController {
    @Autowired
    private IStatisticService statisticService;

    /**
     * 统计商品销量（时间段）
     *
     * @param stDate
     * @param edDate
     * @return
     */
    @GetMapping("/product/num")
    @ApiOperation(value = "统计商品销量", notes = "统计商品销量", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO staProNum(String stDate, String edDate) {
        List<StaProductVO> staProductVOS = statisticService.getProductNum(stDate, edDate);
        return ResultVOUtil.success(staProductVOS);
    }
}
