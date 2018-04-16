package com.imooc.common.converter;

import com.imooc.common.VO.CategoryDetailVO;
import com.imooc.common.VO.CategoryVO;
import com.imooc.common.dataobject.Advertisement;
import com.imooc.common.dataobject.Category;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 11:26 2018/4/14
 */
public class DBToVO {
    /**
     * 类目List转换
     *
     * @param categories
     * @return
     */
    public static List<CategoryVO> getCategoryVO(List<Category> categories) {
        List<CategoryVO> categoryVOS = new ArrayList<>();
        categories.forEach(category -> {
            CategoryVO categoryVO = new CategoryVO();
            recursiveAssign(category, categoryVO);
            categoryVOS.add(categoryVO);
        });
        return categoryVOS;
    }

    /**
     * 类目转换
     *
     * @param category
     * @return
     */
    public static CategoryVO getCategoryVO(Category category) {
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        return categoryVO;
    }

    /**
     * 递归赋值
     *
     * @param category
     * @param categoryVO
     */
    private static void recursiveAssign(Category category, CategoryVO categoryVO) {
        BeanUtils.copyProperties(category, categoryVO);
        List<Category> childCategories = category.getChildCategories();
        if (childCategories != null && childCategories.size() >= 1) {
            List<CategoryVO> childCategoryVOS = new ArrayList<>();
            childCategories.forEach(ctg -> {
                CategoryVO ctgVO = new CategoryVO();
                recursiveAssign(ctg, ctgVO);
                childCategoryVOS.add(ctgVO);
            });
            categoryVO.setChildCategories(childCategoryVOS);
        }
    }

    /**
     * 类目转换-附带商品信息
     *
     * @param categories
     * @return
     */
    public static List<CategoryDetailVO> getCategoryDetailVO(List<Category> categories) {
        List<CategoryDetailVO> categoryVOS = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDetailVO categoryDetailVO = new CategoryDetailVO();
            BeanUtils.copyProperties(category, categoryDetailVO);
            categoryVOS.add(categoryDetailVO);
        });
        return categoryVOS;
    }


}
