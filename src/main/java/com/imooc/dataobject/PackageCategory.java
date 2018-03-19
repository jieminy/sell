package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:09 2018/3/19
 */
@Entity
@Data
@DynamicUpdate
public class PackageCategory {
    /** 类目id. */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoryType")
    private List<PackageInfo> packageInfos;

    public PackageCategory() {}

    public PackageCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
