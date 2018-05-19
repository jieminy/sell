package com.imooc.seller.service;

import com.imooc.common.dataobject.Activity;

import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
public interface IActivityService {
    List<Activity> findAll();

    void save(Activity activity);

    void delete(String atvId);

    Activity findById(String atvId);
}
