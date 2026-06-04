package com.web.cart_service.controller;

import com.web.cart_service.model.Cart;
import com.web.cart_service.model.CartItem;
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

    // CREATE CART
    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return service.save(cart);
    }

    // GET CART BY USER ID
    @GetMapping("/{userId}")
    public Cart get(@PathVariable String userId) {
        return service.getByUserId(userId);
    }

    // ADD ITEM
    @PostMapping("/{userId}/items")
    public Cart addItem(@PathVariable String userId,
                        @RequestBody CartItem item) {

        return service.addItem(userId, item);
    }

    // REMOVE ITEM
    @DeleteMapping("/{userId}/items/{productName}")
    public Cart removeItem(@PathVariable String userId,
                           @PathVariable String productName) {

        return service.removeItem(userId, productName);
    }

    // UPDATE QUANTITY
    @PutMapping("/{userId}/items/{productName}")
    public Cart updateQuantity(@PathVariable String userId,
                               @PathVariable String productName,
                               @RequestParam Integer quantity) {

        return service.updateQuantity(userId, productName, quantity);
    }

    // CLEAR CART
    @DeleteMapping("/{userId}")
    public Cart clearCart(@PathVariable String userId) {
        return service.clearCart(userId);
    }

    // CHECKOUT
    @PostMapping("/{userId}/checkout")
    public Order checkout(@PathVariable String userId) {
        return service.checkout(userId);
    }
}