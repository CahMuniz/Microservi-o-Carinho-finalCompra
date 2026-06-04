package com.web.cart_service.dto;

import com.web.cart_service.model.CartItem;

import java.util.List;

public class CartResponse {

    private String userId;

    private List<CartItem> items;

    private Double total;

    public CartResponse() {
    }

    public CartResponse(String userId,
                        List<CartItem> items,
                        Double total) {

        this.userId = userId;
        this.items = items;
        this.total = total;
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
}