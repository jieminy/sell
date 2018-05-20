package com.imooc.websocket;


import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 * @author itw_yinjm
 * @date 2018/5/18
 */
@ServerEndpoint("/endpoint")
@Slf4j
public class WebSocketController {

    @OnOpen
    public void onOpen() {
        log.info("onOpen");
    }
}

