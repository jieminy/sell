package com.imooc;

import com.imooc.buyer.repository.OrderMasterRepository;
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

	@Test
	public void contextLoads() {
        Integer test = orderMasterRepository.findMaxOrderCode("oof0D5iVEnzb5Hq9FW4BvgNa02tM");
	}

}
