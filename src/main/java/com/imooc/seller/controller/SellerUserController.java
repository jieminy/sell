package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.dataobject.SellerInfo;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.form.SellerInfoForm;
import com.imooc.common.utils.KeyUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.SellException;
import com.imooc.seller.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 卖家用户
 * Created by
 * 2017-07-30 15:28
 */
@RestController
@RequestMapping("/seller/user")
@Api(description = "卖家用户接口")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @PostMapping("/save")
    @ApiOperation(value = "添加/修改", notes = "新增或者修改用户 参数带有id时判定为修改", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO save(@Valid @RequestBody SellerInfoForm sellerInfoForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        SellerInfo sellerInfo = new SellerInfo();
        if(StringUtils.isEmpty(sellerInfoForm.getSellerId()) == false){
            sellerInfo = sellerService.findSellerUsername(sellerInfoForm.getUsername());
            if(sellerInfo == null){
                throw new SellException(ResultEnum.USER_NOT_EXIST);
            }
        }else{
            sellerInfoForm.setSellerId(KeyUtil.genUniqueKey());
        }
        BeanUtils.copyProperties(sellerInfoForm,sellerInfo);
        try{
            sellerService.save(sellerInfo);
            return ResultVOUtil.success();
        }catch (SellException e){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
    }


    @GetMapping("/del")
    @ApiOperation(value = "删除", notes = "根据用户id删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO del(@ApiParam("用户id") @RequestParam("userId") String userId){
        try {
            sellerService.delete(userId);
            return ResultVOUtil.success();
        }catch (SellException e){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询", notes = "查询所有用户", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<SellerInfoForm>> index(){
        List<SellerInfoForm> infoList = sellerService.findall();
        return ResultVOUtil.success(infoList);
    }



}
