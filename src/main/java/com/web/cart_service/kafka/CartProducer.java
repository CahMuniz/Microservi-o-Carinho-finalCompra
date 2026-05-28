package com.web.cart_service.kafka;

import com.web.cart_service.model.Cart;
import com.web.cart_service.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CART_TOPIC = "cart-topic";
    private static final String ORDER_TOPIC = "order-topic";

    public CartProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCart(Cart cart) {
        kafkaTemplate.send(CART_TOPIC, cart);
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send(ORDER_TOPIC, order);
    }
}