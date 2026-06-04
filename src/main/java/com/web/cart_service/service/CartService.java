package com.web.cart_service.service;

import com.web.cart_service.kafka.CartProducer;
import com.web.cart_service.model.Cart;
import com.web.cart_service.model.CartItem;
import com.web.cart_service.model.Order;
import com.web.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartRepository repository;
    private final CartProducer producer;

    public CartService(CartRepository repository, CartProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    // CREATE / SAVE CART
    public Cart save(Cart cart) {
        return repository.save(cart);
    }

    // GET CART BY USER ID
    public Cart getByUserId(String userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }

    // ADD ITEM
    public Cart addItem(String userId, CartItem newItem) {

        Cart cart = repository.findByUserId(userId)
                .orElse(new Cart());

        cart.setUserId(userId);

        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        cart.getItems().add(newItem);

        recalculateTotal(cart);

        return repository.save(cart);
    }

    // REMOVE ITEM
    public Cart removeItem(String userId, String productName) {

        Cart cart = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        cart.getItems().removeIf(item ->
                item.getProductName().equalsIgnoreCase(productName));

        recalculateTotal(cart);

        return repository.save(cart);
    }

    // UPDATE QUANTITY
    public Cart updateQuantity(String userId,
                               String productName,
                               Integer quantity) {

        Cart cart = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        cart.getItems().forEach(item -> {

            if (item.getProductName().equalsIgnoreCase(productName)) {
                item.setQuantity(quantity);
            }
        });

        recalculateTotal(cart);

        return repository.save(cart);
    }

    // CLEAR CART
    public Cart clearCart(String userId) {

        Cart cart = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        cart.setItems(new ArrayList<>());
        cart.setTotal(0.0);

        return repository.save(cart);
    }

    // CHECKOUT
    public Order checkout(String userId) {

        Cart cart = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho vazio");
        }

        recalculateTotal(cart);

        Order order = new Order(userId, cart.getItems(), cart.getTotal());

        try {
            producer.sendOrder(order);
        } catch (Exception e) {
            System.err.println("Erro ao enviar Order para Kafka: " + e.getMessage());
        }

        cart.setItems(new ArrayList<>());
        cart.setTotal(0.0);

        repository.save(cart);

        return order;
    }

    // RECALCULATE TOTAL
    private void recalculateTotal(Cart cart) {

        double total = cart.getItems().stream()
                .mapToDouble(item ->
                        (item.getPrice() == null ? 0.0 : item.getPrice()) *
                                (item.getQuantity() == null ? 0 : item.getQuantity())
                )
                .sum();

        cart.setTotal(total);
    }
}