package com.web.cart_service;

import com.web.cart_service.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartServiceApplicationTests {

	@Autowired
	private CartService cartService;

	@Test
	void serviceExiste() {
		System.out.println(cartService);
	}
}
