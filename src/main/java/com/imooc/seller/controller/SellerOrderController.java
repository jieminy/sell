package com.imooc.seller.controller;

import com.imooc.buyer.dto.OrderDTO;
import com.imooc.common.VO.ResultVO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.awt.*;
import java.util.Map;

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
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询订单", notes = "分页查询订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Page<OrderDTO>> list(@ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
                         @ApiParam("容量") @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        return ResultVOUtil.success(orderDTOPage);
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
    public ResultVO detail(@ApiParam("订单id") @RequestParam("orderId") String orderId) {
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
}
