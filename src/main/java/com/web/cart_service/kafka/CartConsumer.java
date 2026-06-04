package com.web.cart_service.kafka;

import com.web.cart_service.model.Cart;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CartConsumer {

    @KafkaListener(
            topics = "cart-topic",
            groupId = "cart-group"
    )
    public void consume(Cart cart) {

        System.out.println("Carrinho recebido do Kafka:");

        System.out.println("Usuário: " + cart.getUserId());

        System.out.println("Total: " + cart.getTotal());

        cart.getItems().forEach(item -> {

            System.out.println(
                    "Produto: " + item.getProductName()
            );

            System.out.println(
                    "Quantidade: " + item.getQuantity()
            );

            System.out.println(
                    "Preço: " + item.getPrice()
            );
        });
    }
}