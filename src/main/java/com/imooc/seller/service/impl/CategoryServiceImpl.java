package com.imooc.seller.service.impl;

import com.imooc.common.dataobject.Category;
import com.imooc.seller.repository.CategoryRepository;
import com.imooc.seller.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 * Created by 廖师兄
 * 2017-05-09 10:16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * 通过parentId获取目录
     *
     * @param parentId (根节点 -1)
     * @return
     */
    @Override
    public List<Category> findByParent(Integer parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    /**
     * 获取某root类目下的level级子类目(分页)
     *
     * @param level
     * @param rootId
     * @return
     */
    @Override
    public List<Category> findByLevel(int level, Integer rootId, Pageable pageable) {
        //根类目
        if (level == 1 && rootId == null) {
            return findByParent(-1);
        } else {
            List<Category> levelCategories;
            if (pageable != null) {
                Page<Category> pageCagetgory = categoryRepository.findByParentId(rootId, pageable);
                levelCategories = pageCagetgory.getContent();
            } else {
                levelCategories = categoryRepository.findByParentId(rootId);
            }
            getNextLevel(levelCategories, level - 2);
            return levelCategories;
        }
    }

    /**
     * 获取某root类目下的level级子类目
     * @param level
     * @param rootId
     * @return
     */
    @Override
    public List<Category> findByLevel(int level, Integer rootId) {
        return findByLevel(level, rootId, null);
    }

    /**
     * 获取固定深度的所有子类目
     *
     * @param categories
     * @param deep
     */
    private void getNextLevel(List<Category> categories, int deep) {
        if (deep == 0) {
            return;
        } else {
            List<Category> childCategories;
            for (Category category : categories) {
                childCategories = categoryRepository.findByParentId(category.getCategoryId());
                category.setChildCategories(childCategories);
                getNextLevel(childCategories, deep--);
            }
        }
    }

    /**
     * 查询id列表中的类目
     *
     * @param categoryIdList
     * @return
     */
    @Override
    public List<Category> findByCategoryIdIn(List<Integer> categoryIdList) {
        return categoryRepository.findByCategoryIdIn(categoryIdList);
    }

    /**
     * 更新/保存
     *
     * @param category
     * @return
     */
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * 删除
     * @param categoryId
     */
    @Override
    public void delete(Integer categoryId) {
        categoryRepository.delete(categoryId);
    }
}
