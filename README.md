# 🛒 Cart Service - Casa do Terno

Microsserviço responsável pelo gerenciamento do carrinho de compras da plataforma **Casa do Terno**, desenvolvido com **Java 17**, **Spring Boot**, **MongoDB** e **Apache Kafka** seguindo a arquitetura de microsserviços.

---

## 📋 Sobre o Projeto

O Cart Service é responsável por controlar todas as operações relacionadas ao carrinho de compras dos clientes da loja.

Ele permite:

- Criar carrinhos de compra
- Adicionar produtos ao carrinho
- Remover produtos
- Atualizar quantidades
- Limpar carrinhos
- Finalizar compras (Checkout)
- Publicar eventos para outros microsserviços através do Kafka

A comunicação assíncrona garante maior escalabilidade e desacoplamento entre os serviços da plataforma.

---

# 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data MongoDB
- Apache Kafka
- Maven
- MongoDB
- Docker (opcional)
- Swagger/OpenAPI

---

# 📂 Estrutura do Projeto

```text
src
└── main
    ├── java
    │   └── com.web.cart_service
    │
    ├── controller
    │   └── CartController.java
    │
    ├── service
    │   └── CartService.java
    │
    ├── repository
    │   └── CartRepository.java
    │
    ├── model
    │   ├── Cart.java
    │   ├── CartItem.java
    │   └── Order.java
    │
    ├── kafka
    │   ├── CartProducer.java
    │   └── CartConsumer.java
    │
    └── CartServiceApplication.java
```

---

# 🏗️ Arquitetura

O projeto segue a arquitetura em camadas:

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MongoDB
```

Além disso, possui integração assíncrona com Kafka:

```text
Cart Service
     │
     ├── cart-topic
     │
     └── order-topic
               │
               ▼
       Outros Microsserviços
```

---

# 📦 MongoDB

O MongoDB é utilizado para armazenar os carrinhos de compra.

Exemplo de documento:

```json
{
  "_id": "123",
  "userId": "user01",
  "items": [
    {
      "productName": "Terno Slim Preto",
      "quantity": 2,
      "price": 499.90
    }
  ],
  "total": 999.80
}
```

---

# 🔄 Integração com Kafka

O serviço utiliza Kafka para troca de mensagens entre microsserviços.

## Producer

Responsável por enviar eventos para:

### cart-topic

Envia informações do carrinho.

```java
sendCart(cart);
```

### order-topic

Envia pedidos finalizados.

```java
sendOrder(order);
```

---

## Consumer

Escuta mensagens do tópico:

```text
cart-topic
```

Quando recebe um carrinho, exibe:

- Usuário
- Valor total
- Produtos
- Quantidade
- Preço

---

# 📌 Endpoints Disponíveis

## Criar Carrinho

```http
POST /cart
```

### Body

```json
{
  "userId": "user01"
}
```

---

## Buscar Carrinho

```http
GET /cart/{userId}
```

---

## Adicionar Item

```http
POST /cart/{userId}/items
```

### Body

```json
{
  "productName": "Terno Slim Preto",
  "quantity": 1,
  "price": 499.90
}
```

---

## Remover Item

```http
DELETE /cart/{userId}/items/{productName}
```

---

## Atualizar Quantidade

```http
PUT /cart/{userId}/items/{productName}?quantity=3
```

---

## Limpar Carrinho

```http
DELETE /cart/{userId}
```

---

## Finalizar Compra (Checkout)

```http
POST /cart/{userId}/checkout
```

Retorna um objeto Order e publica um evento para os demais microsserviços.

---

# 🎯 Responsabilidades do Cart Service

Este microsserviço é responsável por:

✅ Gerenciar carrinhos de compra

✅ Calcular valores totais

✅ Controlar produtos adicionados

✅ Realizar checkout

✅ Publicar eventos para outros serviços

✅ Persistir informações no MongoDB

---

# 🔥 Benefícios da Separação em Microsserviço

A separação do carrinho em um serviço independente oferece:

- Escalabilidade independente
- Maior organização do sistema
- Baixo acoplamento
- Comunicação assíncrona via Kafka
- Facilidade de manutenção
- Melhor tolerância a falhas

Se o serviço de pedidos estiver indisponível, os eventos continuam armazenados no Kafka até serem processados.

---

# ▶️ Executando o Projeto

## Clonar Repositório

```bash
git clone https://github.com/seu-usuario/cart-service.git
```

## Entrar na pasta

```bash
cd cart-service
```

## Compilar

```bash
mvn clean install
```

## Executar

```bash
mvn spring-boot:run
```

---

# 👨‍💻 Autor

Projeto desenvolvido por @Ana Carolina Muniz para a disciplina de Arquitetura Web utilizando conceitos de Microsserviços, MongoDB e Apache Kafka.

**Casa do Terno - Cart Service**
