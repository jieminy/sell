package com.imooc.seller.controller;

import com.imooc.common.VO.CategoryVO;
import com.imooc.common.VO.ResultVO;
import com.imooc.common.converter.DBToVO;
import com.imooc.common.dataobject.Category;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.form.CategoryForm;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.CategoryService;
import com.imooc.seller.service.impl.SmallCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 卖家类目
 * Created by JieMin
 * 2017-07-23 21:06
 */
@RestController
@RequestMapping("/seller")
@Api(description = "卖家类目接口")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SmallCategoryService smallCategoryService;

    /**
     * 查询某根类目及其二级类目
     * @return
     */
    @GetMapping("/category/all")
    @ApiOperation(value = "查询根类目及其子类目", notes = "查询根类目及其子类目", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<List<CategoryVO>> categoryList() {
        List<Category> categories = categoryService.findByParent(-1);
        categories.forEach(category -> {
            List<Category> childCategories = categoryService.findByLevel(2, category.getCategoryId());
            category.setChildCategories(childCategories);
        });
        List<CategoryVO> categoryVOS = DBToVO.getCategoryVO(categories);
        return ResultVOUtil.success(categoryVOS);
    }

    /**
     * 根据id查找类目
     * @param categoryId
     * @return
     */
    @GetMapping("/category/one")
    @ApiOperation(value = "查询小类目", notes = "根据id查询所有类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO<Category> findCategory(@ApiParam("类目id") @RequestParam(value = "categoryId") Integer categoryId) {
        Category category = categoryService.findOne(categoryId);
        CategoryVO categoryVO = DBToVO.getCategoryVO(category);
        return ResultVOUtil.success(categoryVO);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/category/save")
    @ApiOperation(value = "新增/更新类目", notes = "新增类目",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO saveCategory(@Valid @RequestBody CategoryForm form,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            Category category = new Category();
            if(form.getCategoryId() != null){
                category = categoryService.findOne(form.getCategoryId());
                if (category == null) {
                    throw new SellException(ResultEnum.CATEOGRY_NOT_EXIST);
                }
            }
            BeanUtils.copyProperties(form, category);
            categoryService.save(category);
            return ResultVOUtil.success();
        } catch (SellException e) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
    }

    /**
     * 根据id删除类目
     *
     * @param categoryId
     * @return
     */
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

}
