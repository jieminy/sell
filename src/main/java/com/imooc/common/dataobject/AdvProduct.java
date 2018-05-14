package com.imooc.common.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * @author itw_yinjm
 * @date 2018/4/12
 */
@Entity(name = "adv_product")
@Data
public class AdvProduct extends Advertisement {

    /**
     * 商品Id
     */
    @Column(length = 32)
    private String productId;

    @Transient
    private String categoryName;

}
