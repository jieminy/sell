package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.dataobject.AdvProduct;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.form.AdvProductForm;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.IAdvProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:26 2018/5/14
 */
@RestController
@RequestMapping("/adv/product")
@Api(description = "商品广告管理接口")
public class AdvProductController {
    @Autowired
    private IAdvProductService advProductService;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有", notes = "查询所有商品广告", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<AdvProduct> list() {
        List<AdvProduct> advProducts = advProductService.findAll();
        return ResultVOUtil.success(advProducts);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增或修改", notes = "id不为空为修改否则为新增", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO save(@Valid @RequestBody AdvProductForm advProductForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        AdvProduct advProduct;
        if (advProductForm.getAdvId() != null) {
            advProduct = advProductService.getById(advProductForm.getAdvId());
            if (advProduct == null) {
                throw new SellException(ResultEnum.ADV_NOT_EXTIST);
            }
        } else {
            advProduct = new AdvProduct();
        }
        BeanUtils.copyProperties(advProductForm, advProduct);
        advProductService.save(advProduct);
        return ResultVOUtil.success();
    }

    @GetMapping("/del")
    @ApiOperation(value = "删除", notes = "删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delete(Integer advId) {
        advProductService.delete(advId);
        return ResultVOUtil.success();
    }

}
