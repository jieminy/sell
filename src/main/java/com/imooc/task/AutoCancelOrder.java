package com.imooc.task;

import com.imooc.buyer.repository.OrderMasterRepository;
import com.imooc.common.dto.OrderDTO;
import com.imooc.common.utils.TimeUtil;
import com.imooc.seller.service.OrderService;
import com.imooc.seller.service.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 17:37 2018/5/20
 */
@Component
@Slf4j
public class AutoCancelOrder {
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void autoCancel() {
        log.info("autoCancel");
        webSocket.sendMessage("1");
        List<OrderDTO> orderMasterList = orderService.findByStatus(0, 0);
        orderMasterList.forEach(orderDTO -> {
            if (TimeUtil.intervalOfMinute(new Date(), orderDTO.getCreateTime()) > 60) {
                orderService.cancel(orderDTO);
            }
        });
    }
}
