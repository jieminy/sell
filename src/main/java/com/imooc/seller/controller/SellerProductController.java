package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.enums.ProductStatusEnum;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.KeyUtil;
import com.imooc.common.utils.QiNiuUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dataobject.ProductSmallCategory;
import com.imooc.exception.SellException;
import com.imooc.seller.dto.ProductInfoVO;
import com.imooc.seller.form.ProductForm;
import com.imooc.seller.service.CategoryService;
import com.imooc.seller.service.ProductService;
import com.imooc.seller.service.impl.SmallCategoryService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/seller/product")
@Api(description = "商品接口")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SmallCategoryService smallCategoryService;

    @Autowired
    private QiNiuUtil qiNiuUtil;


    /**
     * 列表

     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询商品", notes = "type = 0上架 1下架", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<ProductInfoVO>> list(@RequestParam(value = "type", defaultValue = "0") Integer productStatus) {
        List<ProductInfo> productInfos;
        List<ProductCategory> productCategories = categoryService.findAll();
        List<ProductSmallCategory> productSmallCategories = smallCategoryService.findAll();
        Map<Integer,ProductSmallCategory> smallCategoryMap = new HashMap<>();
        productSmallCategories.forEach(smallCategory-> {
            smallCategoryMap.put(smallCategory.getSmallCategoryId(), smallCategory);
        });
        Map<Integer, ProductCategory> productCategoryMap = new HashMap<>();
        productCategories.forEach(category -> {
            productCategoryMap.put(category.getCategoryId(),category);
        });
        if(productStatus == 0){
            productInfos  = productService.findUpAll();
        }else{
            productInfos  = productService.findOffAll();
        }
        List<ProductInfoVO> productInfoVOS = new ArrayList<>();
        productInfos.forEach(productInfo -> {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo, productInfoVO);
            productInfoVO.setSmallCategoryName(smallCategoryMap.get(productInfoVO.getSmallCategoryId()).getSmallCategoryName());
            productInfoVO.setCategoryId(smallCategoryMap.get(productInfoVO.getSmallCategoryId()).getCategoryId());
            productInfoVO.setCategoryName(productCategoryMap.get(productInfoVO.getCategoryId()).getCategoryName());
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
            }
            BeanUtils.copyProperties(form, productInfo);
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
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

    @PutMapping("/upload")
    @ApiOperation(value = "上传图片", notes = "上传图片", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultVO upload(@RequestParam("files")MultipartFile multipartFile){
        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            throw new SellException(ResultEnum.IMAGE_NOT_EXIST);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new SellException(ResultEnum.IMAGE_FORMAT_ERROR);
        }
        String fileName = multipartFile.getOriginalFilename();
        String url = "";
        log.info("上传图片：name={},contentType={}",fileName,contentType);
        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            String suffix = fileName.substring(fileName.indexOf('.'),fileName.length());
            fileName = UUID.randomUUID().toString();
            url = qiNiuUtil.uploadImg(fileInputStream, fileName + suffix);
            if ("".equals(url)) {
                throw new SellException(ResultEnum.IMAGE_UPLOAD_FAILED);
            }
        }catch (IOException e){
            throw new SellException(ResultEnum.IMAGE_UPLOAD_FAILED);
        }
        return ResultVOUtil.success(url);
    }
}
