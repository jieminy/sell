package com.imooc.seller.controller;

import com.imooc.common.VO.ProductInfoVO;
import com.imooc.common.VO.ResultVO;
import com.imooc.common.dataobject.Activity;
import com.imooc.common.dataobject.Category;
import com.imooc.common.dataobject.ProductInfo;
import com.imooc.common.enums.ProductStatusEnum;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.form.ProductForm;
import com.imooc.common.utils.KeyUtil;
import com.imooc.common.utils.QiNiuUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.CategoryService;
import com.imooc.seller.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/product")
@Api(description = "卖家商品接口")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QiNiuUtil qiNiuUtil;


    /**
     * 列表

     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询商品", notes = "type = 0上架 1下架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductInfoVO> list(@RequestParam(value = "type", defaultValue = "0") Integer productStatus) {
        List<ProductInfo> productInfos;
        Map<Integer, Category> categoryMap = new HashMap<>();
        List<Category> categories = categoryService.findAll();
        categories.forEach(category ->
                categoryMap.put(category.getCategoryId(), category)
        );
        if(productStatus == 0){
            productInfos  = productService.findUpAll();
        }else{
            productInfos  = productService.findOffAll();
        }
        List<ProductInfoVO> productInfoVOS = new ArrayList<>();
        productInfos.forEach(productInfo -> {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo, productInfoVO);
            productInfoVO.setCategoryName(categoryMap.get(productInfoVO.getCategoryId()).getName());
            Integer parentId = categoryMap.get(productInfoVO.getCategoryId()).getParentId();
            productInfoVO.setParentCategoryId(parentId);
            productInfoVO.setParentCategoryName(categoryMap.get(parentId).getName());
            productInfoVO.setAtvId(productInfo.getActivity().getAtvId());
            productInfoVO.setAtvDes(productInfo.getActivity().getDes());
            productInfoVOS.add(productInfoVO);
        });
        return ResultVOUtil.success(productInfoVOS);
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
    public ResultVO save(@Valid @RequestBody ProductForm form,
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
                productInfo.setProductSales(0);
            }
            BeanUtils.copyProperties(form, productInfo);
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            Activity activity = new Activity();
            activity.setAtvId(form.getAtvId());
            productInfo.setActivity(activity);
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
