package com.imooc.common.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:06 2018/7/8
 */
@Entity
@Data
public class MiniProgramInfo {

    /**
     * id
     */
    @Id
    @Column(length = 4)
    private String programId;
    /**
     * 小程序状态 0打烊 1正常运营
     */
    @Column(length = 1)
    private Integer status;
}
