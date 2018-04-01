package com.imooc.buyer.controller;

import com.imooc.buyer.service.IReceiverInfoService;
import com.imooc.common.VO.ResultVO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.KeyUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.dataobject.ReceiverInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:50 2018/4/1
 */
@RestController
@RequestMapping("/buyer/receiver")
@Api(description = "收获地址管理")
public class BuyerAddressController {
    @Autowired
    private IReceiverInfoService receiverInfoService;
    @PostMapping("/save")
    @ApiOperation(value = "新增/修改", notes = "当有id时为修改", produces = "application/json;charset=UTF-8")
    public ResultVO save(@Valid ReceiverInfo receiverInfo,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
        }
        if(receiverInfo.getRecId() == null){
            String recId = "rec"+ KeyUtil.genUniqueKey();
            receiverInfo.setRecId(recId);
        }
        receiverInfoService.save(receiverInfo);
        return ResultVOUtil.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询收获地址", notes = "根据openid查询收获地址", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<ReceiverInfo>> getAll(@ApiParam("用户openid") String openid){
        List<ReceiverInfo> receiverInfos;
        receiverInfos = receiverInfoService.findAll(openid);
        return ResultVOUtil.success(receiverInfos);
    }

    @GetMapping("/one")
    @ApiOperation(value = "查询单个收获地址", notes = "根据recId查询收获地址", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<ReceiverInfo>> getOne(@ApiParam("地址recId") @RequestParam("recId") String recId){
        ReceiverInfo receiverInfo = receiverInfoService.findOne(recId);
        return ResultVOUtil.success(receiverInfo);
    }

    @GetMapping("/del")
    @ApiOperation(value = "删除收获地址", notes = "根据openid和收获地址id删除", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResultVO<List<ReceiverInfo>> delete(@ApiParam("收货信息id") @RequestParam("recId") String recId, @ApiParam("用户openid") @RequestParam("openid") String openid){
        receiverInfoService.delete(recId,openid);
        List<ReceiverInfo> receiverInfos = receiverInfoService.findAll(openid);
        return ResultVOUtil.success(receiverInfos);
    }
}
