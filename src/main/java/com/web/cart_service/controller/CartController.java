package com.web.cart_service.controller;

import com.web.cart_service.model.Cart;
import com.web.cart_service.model.Order;
import com.web.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    // Criar carrinho
    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return service.save(cart);
    }

    // Buscar carrinho por userId
    @GetMapping("/{userId}")
    public Cart get(@PathVariable String userId) {
        return service.getByUserId(userId);
    }

    // Checkout
    @PostMapping("/{userId}/checkout")
    public Order checkout(@PathVariable String userId) {
        return service.checkout(userId);
    }
}