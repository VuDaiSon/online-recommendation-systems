package com.example.recommendershop.service.cart;

import com.example.recommendershop.dto.ResponseData;
import com.example.recommendershop.dto.cart.request.AddToCartRequest;
import com.example.recommendershop.dto.cart.response.CartView;

import java.util.Map;
import java.util.UUID;

public interface CartService {
    public ResponseData<?> addToCart(AddToCartRequest addToCartRequest);
    public ResponseData<?> updateCart(Map<String, Object> data);
    public ResponseData<?> deleteCartLine(UUID cartDetailId);
    public CartView CartCheck();
}
