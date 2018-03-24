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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * Created by 廖师兄
 * 2017-07-23 15:12
 */
@RestController
@RequestMapping("/seller/product")
@ApiIgnore
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
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
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
    public ResultVO offSale(@RequestParam("productId") String productId) {
        try {
            ProductInfo productInfo = productService.offSale(productId);
            return ResultVOUtil.success(productInfo);
        } catch (SellException e) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(),ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
    }

    @GetMapping("/index")
    public ResultVO index(@RequestParam(value = "productId", required = false) String productId) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            return ResultVOUtil.success(productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        return ResultVOUtil.success(categoryList);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    public ResultVO save(@Valid ProductForm form,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

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
}
