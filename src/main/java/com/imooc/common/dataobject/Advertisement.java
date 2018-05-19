package com.imooc.common.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Transient
    /**
     * 图片地址数组
     */
    protected List<String> picList;

    /**
     * 类别id
     */
    protected Integer categoryId;

    /**
     * 广告类别
     */
    @Column(length = 4)
    protected String type;

    /**
     * 创建时间.
     */
    @Column(columnDefinition="timestamp default current_timestamp")
    private Date createTime;

    /**
     * 更新时间.
     */
    @Column(columnDefinition="timestamp default current_timestamp on update current_timestamp")
    private Date updateTime;
}
