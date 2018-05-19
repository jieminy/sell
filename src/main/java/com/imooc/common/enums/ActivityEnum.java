package com.imooc.common.enums;

import lombok.Getter;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
@Getter
public enum ActivityEnum implements CodeEnum {
    NOTING(-1, "无活动"),
    DISCOUNT(1, "打折");

    ActivityEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;
    private String value;

}
