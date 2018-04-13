package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author itw_yinjm
 * @date 2018/4/8
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Advertisement implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer advId;

    /**
     * 图片路径
     */
    @Column(length = 1000)
    protected String pic;

    /**
     * 类别id
     */
    protected Integer categoryId;

    /**
     * 广告类别
     */
    protected String type;


    protected Date createTime;
    protected Date updateTime;
}
