package com.imooc.repository;

import com.imooc.dataobject.AdvProduct;
import com.imooc.seller.repository.AdvProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author itw_yinjm
 * @date 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementRepositoryTest {
    @Autowired
    private AdvProductRepository advProductRepository;

    @Test
    public void testSelect() {
        AdvProduct advProduct = advProductRepository.findOne(1001);
    }
}
