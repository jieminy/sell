package com.imooc.common.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

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
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, mappedBy = "categoryId")
    private List<Advertisement> advertisements;

    /**
     * 商品
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, mappedBy = "categoryId")
    @Where(clause = "product_status = 0")
    @OrderBy(clause = "update_time desc")
    private List<ProductInfo> productInfos;

//    @Generated(GenerationTime.INSERT)
    /**
     * 创建时间.
     */
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

//    @Generated(GenerationTime.ALWAYS)
    /**
     * 更新时间.
     */
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateTime;

    /**
     * 存放子类目
     */
    @Transient
    private List<Category> childCategories;
}
