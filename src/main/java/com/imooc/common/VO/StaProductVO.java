package com.imooc.common.VO;

import lombok.Data;

import java.io.Serializable;


/**
 * @author itw_yinjm
 * @date 2018/5/8
 */
@Data
public class StaProductVO implements Serializable {
    public StaProductVO() {
    }

    public StaProductVO(String productId, String productName, String num) {
        this.productId = productId;
        this.productName = productName;
        this.num = num;
    }
    private String productId;
    private String productName;
    private String num;
}
