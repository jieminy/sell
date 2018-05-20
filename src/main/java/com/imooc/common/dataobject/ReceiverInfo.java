package com.imooc.common.dataobject;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:10 2018/4/1
 */
@Data
@Entity
public class ReceiverInfo {
    @Column(length = 32)
    @Id
    private String recId;

    /**
     * 微信用户openid
     */
    @Column(length = 64)
    @NotNull
    @NotEmpty
    private String openid;

    /**
     * 电话
     */
    @Column(length = 12)
    @NotNull
    @NotEmpty
    private String phone;

    /**
     * 姓名
     */
    @Column(length = 32)
    @NotNull
    @NotEmpty
    private String name;

    /**
     * 地址
     */
    @NotNull
    @Column(length = 256)
    @NotEmpty
    private String address;

    /**
     * 详细地址
     */
    @NotNull
    @Column(length = 256)
    @NotEmpty
    private String detail;

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
