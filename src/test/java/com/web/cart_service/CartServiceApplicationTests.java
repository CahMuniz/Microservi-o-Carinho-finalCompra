package com.web.cart_service;

import com.web.cart_service.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CartServiceApplicationTests {

	@Autowired
	private CartService cartService;

	@Test
	void serviceExiste() {

		assertNotNull(cartService);

		System.out.println("CartService carregado com sucesso!");
	}
}
