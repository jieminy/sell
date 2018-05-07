package com.imooc.common.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
@Entity
@Data
@DynamicUpdate
public class Activity {
    /**
     * 活动id
     */
    @Column(length = 32)
    @Id
    private String atvId;

    /**
     * 活动类型
     */
    private Integer type;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 描述
     */
    @Column(length = 128)
    private String des;

    /**
     * 小图标
     */
    @Column(length = 512)
    private String icon;

    /**
     * 创建时间.
     */
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    /**
     * 更新时间.
     */
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateTime;

}
