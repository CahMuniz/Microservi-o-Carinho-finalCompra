package com.web.cart_service.service;

import com.web.cart_service.kafka.CartProducer;
import com.web.cart_service.model.Cart;
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

    // CHECKOUT
    public Order checkout(String userId) {

        Cart cart = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho vazio");
        }

        double total = cart.getItems().stream()
                .mapToDouble(item ->
                        (item.getPrice() == null ? 0.0 : item.getPrice()) *
                                (item.getQuantity() == null ? 0 : item.getQuantity())
                )
                .sum();

        Order order = new Order(userId, cart.getItems(), total);

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
}