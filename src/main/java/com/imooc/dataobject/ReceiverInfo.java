package com.imooc.dataobject;

import lombok.Data;

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
    @Column(length = 64)
    @NotNull
    private String openid;
    @Column(length = 12)
    @NotNull
    private String phone;
    @Column(length = 32)
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String detail;

    private Date createTime;
    private Date updateTime;
}
