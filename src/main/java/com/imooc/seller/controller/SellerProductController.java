package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.exception.SellException;
import com.imooc.seller.form.ProductForm;
import com.imooc.seller.service.CategoryService;
import com.imooc.seller.service.ProductService;
import com.imooc.common.utils.KeyUtil;
import com.lly835.bestpay.rest.type.Get;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/product")
@Api(description = "商品接口")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询商品", notes = "分页查询默认 page = 1, size = 10", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Page<ProductInfo>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
            PageRequest request = new PageRequest(page - 1, size);
            Page<ProductInfo> productInfoPage = productService.findAll(request);
            return ResultVOUtil.success(productInfoPage);
    }

    /**
     * 商品上架
     * @param productId
     * @return
     */
    @GetMapping("/on_sale")
    @ApiOperation(value = "上架", notes = "商品上架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO onSale(@RequestParam("productId") String productId) {
        try {
            ProductInfo productInfo = productService.onSale(productId);
            return ResultVOUtil.success(productInfo);
        } catch (SellException e) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(),ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
    }
    /**
     * 商品下架
     * @param productId
     * @return
     */
    @GetMapping("/off_sale")
    @ApiOperation(value = "下架", notes = "商品下架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO offSale(@RequestParam("productId") String productId) {
        try {
            ProductInfo productInfo = productService.offSale(productId);
            return ResultVOUtil.success(productInfo);
        } catch (SellException e) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(),ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
    }

    @GetMapping("/index")
    @ApiOperation(value = "查询商品", notes = "根据id查询商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductInfo> index(@RequestParam(value = "productId") String productId) {
        ProductInfo productInfo = productService.findOne(productId);
        return ResultVOUtil.success(productInfo);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增/更新", notes = "新增商品/更新商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO save(@Valid ProductForm form,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
            return ResultVOUtil.success();
        } catch (SellException e) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
        }
    }

    @GetMapping("/del")
    @ApiOperation(value = "删除", notes = "根据id删除", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delete(@ApiParam("商品id") @RequestParam("productId") String productId){
        try{
            productService.delete(productId);
            return ResultVOUtil.success();
        }catch (SellException e){
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(),ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }

    }
}
