package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author itw_yinjm
 * @date 2018/4/8
 */
@Entity
@Data
public class Advertisement {
    /**
     * id
     */
    @Id
    @Column(length = 32)
    private String advId;

    /**
     * 图片路径
     */
    @Column(length = 128)
    private String pic;

    /**
     * 类别id
     */
    private Integer categoryId;
    private Date createTime;
    private Date updateTime;
}
