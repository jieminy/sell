package com.imooc.buyer.controller;

import com.imooc.buyer.service.BuyerService;
import com.imooc.common.VO.ResultVO;
import com.imooc.common.converter.OrderForm2OrderDTOConverter;
import com.imooc.common.dto.OrderDTO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.form.OrderForm;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jiemin
 * 2017-06-18 23:27
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
@Api(description = "买家订单接口")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    @ApiOperation(value = "创建订单", notes = "创建订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    @ApiOperation(value = "查询订单列表", notes = "根据用户的openid查询所有订单", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> list(@ApiParam("openid") @RequestParam("openid") String openid,
                                   @ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @ApiParam("容量") @RequestParam(value = "size", defaultValue = "5") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page - 1, size);
        List<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderDTOPage);
    }


    //订单详情
    @GetMapping("/detail")
    @ApiOperation(value = "订单详情", notes = "根据openid 和orderId查询", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    @ApiOperation(value = "取消订单", notes = "需要openid orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

    @GetMapping("/paid")
    @ApiOperation(value = "完成支付订单", notes = "需要openid orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO paid(@RequestParam("openid") String openid,
                         @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        orderService.paid(orderDTO);
        return ResultVOUtil.success(orderDTO);
    }
}
