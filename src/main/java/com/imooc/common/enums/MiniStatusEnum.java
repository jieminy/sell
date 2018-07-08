package com.imooc.common.enums;

import lombok.Getter;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:25 2018/7/8
 */
@Getter
public enum MiniStatusEnum implements CodeEnum {
    CLOSE(0, "close"),
    OPEN(1, "open");

    MiniStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;
    private String value;
}
