package com.imooc;

import com.imooc.buyer.repository.OrderMasterRepository;
import com.imooc.seller.repository.ProductInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

	@Test
	public void contextLoads() {
        Integer test = orderMasterRepository.findMaxOrderCode();
    }

    @Test
    public void activity() {

    }


}
