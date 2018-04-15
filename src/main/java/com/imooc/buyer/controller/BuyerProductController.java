package com.imooc.buyer.controller;

import com.imooc.common.VO.CategoryDetailVO;
import com.imooc.common.VO.CategoryVO;
import com.imooc.common.VO.ResultVO;
import com.imooc.common.converter.DBToVO;
import com.imooc.common.dataobject.Category;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.seller.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 买家商品
 * Created by JieMin
 * 2017-05-12 14:08
 */
@RestController
@RequestMapping("/buyer/product")
@Api(description = "买家类目/商品接口")
public class BuyerProductController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 查询所有一级类目
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询类目", notes = "查询所有一级类目", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO list() {
        List<Category> categories = categoryService.findByParent(-1);
        List<CategoryVO> categoryVOS = DBToVO.getCategoryVO(categories);
        return ResultVOUtil.success(categoryVOS);
    }

    /**
     * 查询某根类目及其二级类目
     *
     * @param parentId
     * @return
     */
    @GetMapping("/list/small/prodcut")
    @ApiOperation(value = "查询二级类目(带商品)", notes = "查询某根类目及其二级类目", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO listLevel2WithProduct(@ApiParam("父类目id") @RequestParam("parentId") Integer parentId,
                                          @ApiParam("页数") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @ApiParam("容量") @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        List<Category> categories = categoryService.findByLevel(2, parentId, pageRequest);
        List<CategoryDetailVO> categoryVOS = DBToVO.getCategoryDetailVO(categories);
        return ResultVOUtil.success(categoryVOS);
    }

}
