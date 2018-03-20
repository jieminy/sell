package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class PackageInfo {
    @Id
    private String packageId;

    /** 套餐名字 */
    @Length(max = 100)
    private String packageName;

    /** 图标 */
    @Length(max = 1000)
    private String packageIcon;

    /** 销量 */
    @Length(max = 10)
    private String packageSales;

    /** 上架状态 0下架 1上架*/
    @Length(max = 1)
    private Integer packageStatus;

    /** 套餐类别 */
    @Length(max = 1)
    private Integer categoryType;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "productId")
    private List<ProductInfo> productInfos;

    private Date createTime;

    private Date updateTime;
}
