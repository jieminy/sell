package com.imooc.websocket;

/**
 * @author itw_yinjm
 * @date 2018/5/18
 */
public class AricResponse {
    private String responseMessage;

    public AricResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
