package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/4/12
 */
@Data
@Entity
@DynamicUpdate
public class Category {
    /**
     * id
     */
    @Id
    private Integer ctgId;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 名称
     */
    @Column(length = 32)
    private String name;

    /**
     * 描述
     */
    @Column(length = 128)
    private String des;

    /**
     * 图片
     */
    @Column(length = 256)
    private String pic;

    /**
     * 广告
     */
    @Transient
    private List<Advertisement> advertisements;

    /**
     * 商品
     */
    @Transient
    private List<ProductInfo> productInfos;

    @Generated(GenerationTime.INSERT)
    private Date createTime;

    //    @Column(columnDefinition="timestamp default current_timestamp comment '活动开始时间'")
    @Generated(GenerationTime.ALWAYS)
    private Date updateTime;
}
