package com.example.recommendershop.dto.order.response;

import com.example.recommendershop.entity.Order;

import java.util.List;

public class AdminEditResponse {
    private Order order;
    private List<String> orderStatuses;

    public AdminEditResponse(Order order, List<String> orderStatuses) {
        this.order = order;
        this.orderStatuses = orderStatuses;
    }

    public Order getOrder() {
        return order;
    }

    public List<String> getOrderStatuses() {
        return orderStatuses;
    }
}