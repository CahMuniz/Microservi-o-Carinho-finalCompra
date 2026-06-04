package com.web.cart_service.dto;

import com.web.cart_service.model.CartItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private String orderId;

    private String userId;

    private List<CartItem> items;

    private Double total;

    private String status;

    private LocalDateTime createdAt;

    public OrderResponse() {
    }

    public OrderResponse(String orderId,
                         String userId,
                         List<CartItem> items,
                         Double total,
                         String status,
                         LocalDateTime createdAt) {

        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
