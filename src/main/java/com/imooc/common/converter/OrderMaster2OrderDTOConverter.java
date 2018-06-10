package com.imooc.common.converter;

import com.imooc.common.dataobject.OrderMaster;
import com.imooc.common.dto.OrderDTO;
import com.imooc.common.enums.OrderStatusEnum;
import com.imooc.common.enums.PayStatusEnum;
import com.imooc.common.utils.EnumUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 廖师兄
 * 2017-06-11 22:02
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderStatusDes(EnumUtil.getByCode(orderDTO.getOrderStatus(), OrderStatusEnum.class).getMessage());
        orderDTO.setPayStatusDes(EnumUtil.getByCode(orderDTO.getPayStatus(), PayStatusEnum.class).getMessage());
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
