package com.interview.shoppingcart.shoppingcart;

import com.interview.shoppingcart.bo.PremiumSlab;
import com.interview.shoppingcart.bo.RegularSlab;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ShoppingcartApplicationTests {

    @InjectMocks
	PremiumSlab premiumSlab;

    @InjectMocks
	RegularSlab regularSlab;

	@Test
	void contextLoads() {
	}


	@Test
	void regularCustomerFinalAmountTest(){
		Double finalCost;
		finalCost = regularSlab.calculateFinalCost(6000.00);
		assertEquals(5900, finalCost);

	}

	@Test
	void premiumCustomerFinalAmountTest(){
		Double finalCost;
		finalCost = premiumSlab.calculateFinalCost(15000.00);
		assertEquals(12300, finalCost);

	}
}
