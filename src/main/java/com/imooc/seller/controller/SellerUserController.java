package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.common.constant.CookieConstant;
import com.imooc.common.constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.common.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.seller.form.SellerInfoForm;
import com.imooc.seller.service.SellerService;
import com.imooc.common.utils.CookieUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * Created by
 * 2017-07-30 15:28
 */
@Controller
@RequestMapping("/seller/user")
@ApiIgnore
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @PostMapping("/save")
    @ApiOperation(value = "添加/修改", notes = "新增或者修改用户 参数带有id时判定为修改", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO save(@Valid SellerInfoForm sellerInfoForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        SellerInfo sellerInfo = new SellerInfo();
        if(StringUtils.isEmpty(sellerInfoForm.getSellerId())){
            sellerInfo = sellerService.findSellerUsername(sellerInfoForm.getSellerId());
        }
        BeanUtils.copyProperties(sellerInfoForm,sellerInfo);
        try{
            sellerService.save(sellerInfo);
            return ResultVOUtil.success();
        }catch (SellException e){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
    }





}
