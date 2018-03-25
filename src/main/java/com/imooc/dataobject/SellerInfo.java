package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by JieMin
 * 2017-07-23 23:02
 */
@Data
@Entity
public class SellerInfo {

    @Id
    @Column(length = 32)
    private String sellerId;

    @Column(length = 64)
    private String username;

    @Column(length = 32)
    private String password;

    private Date createTime;
    private Date updateTime;
}
