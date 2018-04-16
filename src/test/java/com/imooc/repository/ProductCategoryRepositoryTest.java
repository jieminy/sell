package com.imooc.repository;

import com.imooc.common.dataobject.Category;
import com.imooc.seller.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-05-07 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void findOneTest() {
        Category productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest() {
        Category productCategory = new Category();
        Category result = repository.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2,3,4);

        List<Category> result = repository.findByCategoryIdIn(list);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateTest() {
//        ProductCategory productCategory = repository.findOne(4);
//        productCategory.setCategoryName("男生最爱1");
        Category productCategory = new Category();
        Category result = repository.save(productCategory);
        Assert.assertEquals(productCategory, result);
    }
}