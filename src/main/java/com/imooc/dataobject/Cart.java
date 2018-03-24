package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:31 2018/3/23
 */
@Data
@Entity
@DynamicUpdate
public class Cart {
    @Id
    @GeneratedValue
    @Length(max = 11)
    private Integer cartId;
    @Length(max = 64)
    private String openId;
    @Length(max = 32)
    private String productId;
    @Length(max = 5)
    private Integer count;
    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

}
