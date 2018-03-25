package com.imooc.buyer.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductSmallCategory;
import com.imooc.seller.service.CategoryService;
import com.imooc.seller.service.ProductService;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.seller.service.impl.SmallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * 买家商品
 * Created by
 * 2017-05-12 14:08
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SmallCategoryService smallCategoryService;

    @GetMapping("/list")
    public ResultVO list() {
        List<ProductCategory> productCategories = categoryService.findAll();
        return ResultVOUtil.success(productCategories);
    }

    @GetMapping("/list/small")
    public ResultVO displaySmallCatgs(){
        List<ProductSmallCategory> productSmallCategories = smallCategoryService.findAll();
        return ResultVOUtil.success(productSmallCategories);
    }

    @GetMapping("/category/small/findById")
    public ResultVO getSmCatgsByCategoryType(@RequestParam("categoryId") Integer categoryId){
        List<ProductSmallCategory> productSmallCategories = smallCategoryService.findByProdcutCategory(categoryId);
        return ResultVOUtil.success(productSmallCategories);
    }
}
