package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductSmallCategory;
import com.imooc.exception.SellException;
import com.imooc.seller.form.CategoryForm;
import com.imooc.seller.form.SmallCategoryForm;
import com.imooc.seller.service.CategoryService;
import com.imooc.seller.service.impl.SmallCategoryService;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目
 * Created by 廖师兄
 * 2017-07-23 21:06
 */
@RestController
@RequestMapping("/seller")
@Api(description = "类目接口")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SmallCategoryService smallCategoryService;

    /**
     * 类目列表
     * @return
     */
    @GetMapping("/category/all")
    @ApiOperation(value = "查询类目", notes = "查询所有类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<ProductCategory>> categoryList() {
        List<ProductCategory> categoryList = categoryService.findAll();
        return ResultVOUtil.success(categoryList);
    }

    /**
     * 小类目列表
     * @return
     */
    @GetMapping("/smallCategory/all")
    @ApiOperation(value = "查询类目", notes = "查询所有小类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<ProductSmallCategory>> smCategoryList() {
        List<ProductSmallCategory> categoryList = smallCategoryService.findAll();
        return ResultVOUtil.success(categoryList);
    }

    /**
     * 查找大类目
     * @param categoryId
     * @return
     */
    @GetMapping("/category/one")
    @ApiOperation(value = "查询小类目", notes = "根据id查询所有类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductCategory> findCategory(@ApiParam("类目id") @RequestParam(value = "categoryId") Integer categoryId) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
        return ResultVOUtil.success(productCategory);
    }

    /**
     * 查找小类目
     * @param smCategoryId
     * @return
     */
    @GetMapping("/smallCategory/one")
    @ApiOperation(value = "查询小类目", notes = "根据Id查询所有小类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<ProductSmallCategory> findSmCategory(@ApiParam("小类目id") @RequestParam(value = "smCategoryId") Integer smCategoryId) {
        ProductSmallCategory productSmallCategory = smallCategoryService.findOne(smCategoryId);
        return ResultVOUtil.success(productSmallCategory);
    }

    @GetMapping("/smallCategory/byCatId")
    @ApiOperation(value = "查询小类目", notes = "查询类目id查询所有小类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<ProductSmallCategory>> findByCategoryId(@ApiParam("类目id") @RequestParam("categoryId") Integer categoryId){
        List<ProductSmallCategory> productSmallCategories = smallCategoryService.findByProdcutCategory(categoryId);
        return ResultVOUtil.success(productSmallCategories);
    }



    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/category/save")
    @ApiOperation(value = "新增/更新类目", notes = "新增类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO saveCategory(@ApiParam("类目对象") @Valid CategoryForm form,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if(form.getCategoryId() != null){
                productCategory = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
        }
        return ResultVOUtil.success();
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/smallCategory/save")
    @ApiOperation(value = "新增/更新小类目", notes = "新增小类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO saveSmCategory(@ApiParam("小类目对象")  @Valid SmallCategoryForm form,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }

        ProductSmallCategory productCategory = new ProductSmallCategory();
        try {
            if(form.getSmallCategoryId() != null){
                productCategory = smallCategoryService.findOne(form.getSmallCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            smallCategoryService.save(productCategory);
        } catch (SellException e) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/category/del")
    @ApiOperation(value = "删除类目", notes = "根据id删除类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delCategory(@ApiParam("类目id") @RequestParam("categoryId") Integer categoryId){
        try{
            categoryService.delete(categoryId);
            return ResultVOUtil.success();
        }catch (SellException e){
            return ResultVOUtil.error(ResultEnum.CATEOGRY_NOT_EXIST.getCode(), ResultEnum.CATEOGRY_NOT_EXIST.getMessage());
        }
    }

    @GetMapping("/smallCategory/del")
    @ApiOperation(value = "删除类目", notes = "根据id删除类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO delSmallCategory(@ApiParam("小类目id") @RequestParam("smallCategoryId") Integer smallCategoryId){
        try{
            smallCategoryService.delete(smallCategoryId);
            return ResultVOUtil.success();
        }catch (SellException e){
            return ResultVOUtil.error(ResultEnum.CATEOGRY_NOT_EXIST.getCode(), ResultEnum.CATEOGRY_NOT_EXIST.getMessage());
        }
    }
}
