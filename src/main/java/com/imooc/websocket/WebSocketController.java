package com.imooc.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itw_yinjm
 * @date 2018/5/18
 */
@RestController
@MessageMapping("/notify")
public class WebSocketController {

    @MessageMapping("/pay")
    @SendTo("/topic/seller")
    public AricResponse say() {
        return new AricResponse("success");
    }
}

