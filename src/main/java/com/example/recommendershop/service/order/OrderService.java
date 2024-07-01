package com.example.recommendershop.service.order;


import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.order.request.OrderRequest;
import com.example.recommendershop.dto.order.response.AdminEditResponse;
import com.example.recommendershop.dto.order.response.CheckOutViewModel;
import com.example.recommendershop.dto.order.response.OrderResponse;
import com.example.recommendershop.entity.Order;

import java.util.UUID;

public interface OrderService {
    public ResponseData<?> checkout();

    public ResponseData<?> confirmOrder(OrderRequest orderRequest);

    public BasePage<OrderResponse> getAllOrders(ApiListBaseRequest apiListBaseRequest);

    public CheckOutViewModel OrderDetail(UUID orderId);
    public ResponseData cancelOrder(UUID orderId);
    public BasePage<OrderResponse> AdminIndex(ApiListBaseRequest listBaseRequest);
    public AdminEditResponse AdminCheck(UUID orderId);
    public ResponseData<?> AdminEdit(UUID id, Order updatedOrder) ;
    }