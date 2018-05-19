package com.imooc.common.enums;

import lombok.Getter;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 17:30 2018/5/5
 */
@Getter
public enum DistributeEnum implements CodeEnum {
    SELF_PICK(1, "自提"),
    DISTRIBUTE(2, "配送");

    private Integer code;
    private String value;

    DistributeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
