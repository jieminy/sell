package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by JieMin
 * 2017-07-23 23:02
 */
@Data
@Entity
@Table(name = "seller_info",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class SellerInfo {

    @Id
    @Column(length = 32)
    private String sellerId;


    @Column(length = 64)
    private String username;

    @Column(length = 32)
    private String password;

    private boolean admin;

    private Date createTime;
    private Date updateTime;
}
