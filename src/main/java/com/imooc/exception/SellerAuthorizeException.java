package com.imooc.exception;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-07-30 17:41
 */
public class SellerAuthorizeException extends RuntimeException {

    @Getter
    private Integer code;

    public SellerAuthorizeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SellerAuthorizeException() {
    }
}
