package com.web.cart_service.kafka;

// Importa classe Cart
import com.web.cart_service.model.Cart;

// Importações do Kafka
import org.springframework.kafka.annotation.KafkaListener;

// Importação do Spring
import org.springframework.stereotype.Component;

// Define que essa classe será gerenciada pelo Spring
@Component
public class CartConsumer {

    // Método que escuta mensagens do Kafka
    // "cart-topic" é o nome do tópico Kafka
    @KafkaListener(
            topics = "cart-topic",
            groupId = "cart-group"
    )
    public void consume(Cart cart) {

        // Exibe informações recebidas do Kafka
        System.out.println("Carrinho recebido do Kafka:");

        System.out.println("Usuário: " + cart.getUserId());

        System.out.println("Total: " + cart.getTotal());

        // Percorre lista de itens do carrinho
        cart.getItems().forEach(item -> {

            System.out.println(
                    "Produto: " + item.getName()
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