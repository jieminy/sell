package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.dataobject.Activity;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.form.ActivityForm;
import com.imooc.common.utils.KeyUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.IActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author itw_yinjm
 * @date 2018/5/7
 */
@RestController
@RequestMapping(value = "/activity")
@Api(description = "活动接口")
public class SellerActivityController {

    @Autowired
    private IActivityService activityService;

    @GetMapping("list")
    @ApiOperation(value = "查询所有活动", notes = "查询所有活动", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO list() {
        return ResultVOUtil.success(activityService.findAll());
    }

    @PostMapping("save")
    @ApiOperation(value = "保存/修改", notes = "提供id为修改，反之为保存", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO saveOrUpdate(@Valid @RequestBody ActivityForm activityForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Activity activity = new Activity();
        if (StringUtils.isEmpty(activityForm.getAtvId()) == true) {
            activityForm.setAtvId(KeyUtil.genUniqueKey());
            activity.setType(1);
        } else {
            activity = activityService.findById(activityForm.getAtvId());
            if (activity == null) {
                throw new SellException(ResultEnum.ACTIVITY_NOT_EXIST);
            }
        }
        BeanUtils.copyProperties(activityForm, activity);
        activityService.save(activity);
        return ResultVOUtil.success();
    }

    @GetMapping("del")
    @ApiOperation(value = "删除", notes = "根据id删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO del(String atvId) {
        activityService.delete(atvId);
        return ResultVOUtil.success();
    }
}
