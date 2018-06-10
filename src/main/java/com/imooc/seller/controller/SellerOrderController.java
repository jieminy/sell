package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.dto.OrderDTO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 卖家端订单
 * Created by JieMin
 * 2017-07-16 17:24
 */
@RestController
@RequestMapping("/seller/order")
@Slf4j
@Api(description = "卖家订单接口")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询订单", notes = "分页查询订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> list(@ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @ApiParam("容量") @RequestParam(value = "size", defaultValue = "15") Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        return ResultVOUtil.success(orderService.findUnfinished(pageRequest));
    }

    @GetMapping("/list/history")
    @ApiOperation(value = "查询订单", notes = "分页查询订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> listOfHistory(@ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @ApiParam("容量") @RequestParam(value = "size", defaultValue = "15") Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        return ResultVOUtil.success(orderService.findHistory(pageRequest));
    }


    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    @ApiOperation(value = "取消订单", notes = "根据订单id取消订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO cancel(@ApiParam("订单id") @RequestParam("orderId") String orderId) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
            return ResultVOUtil.success(ResultEnum.ORDER_CANCEL_SUCCESS.getCode(),ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation(value = "订单查询", notes = "根据订单id查询订单详情", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> detail(@ApiParam("订单id") @RequestParam("orderId") String orderId) {
        try {
            OrderDTO  orderDTO = orderService.findOne(orderId);
            return ResultVOUtil.success(orderDTO);
        }catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @GetMapping("/finish")
    @ApiOperation(value = "完结订单", notes = "根据orderId 修改订单状态为完结 ", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO finished(@ApiParam("订单id") @RequestParam("orderId") String orderId) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
            return ResultVOUtil.success(ResultEnum.ORDER_FINISH_SUCCESS.getCode(), ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
    }

    /**
     * 根据订单状态和支付状态统计订单
     *
     * @param stDate
     * @param edDate
     * @param orderStatus
     * @param payStatus
     * @return
     */
    @GetMapping("/statistic")
    @ApiOperation(value = "统计订单", notes = "待支付(0,0) 配送中(0,1) 已取消(2,0) 已撤单(2,1) 已完结(1,1) ", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO statistic(String stDate, String edDate, Integer orderStatus, Integer payStatus) {
        List<OrderDTO> orderDTOList = orderService.statistic(stDate,
                edDate,
                orderStatus,
                orderStatus);
        return ResultVOUtil.success(orderDTOList);
    }
}
