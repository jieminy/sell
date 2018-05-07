package com.imooc.seller.service.impl;

import com.imooc.common.dataobject.Activity;
import com.imooc.seller.repository.ActivityRepository;
import com.imooc.seller.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
@Service
public class ActivityService implements IActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public void save(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void delete(String atvId) {
        activityRepository.delete(atvId);
    }

    @Override
    public Activity findById(String atvId) {
        return activityRepository.findOne(atvId);
    }
}
