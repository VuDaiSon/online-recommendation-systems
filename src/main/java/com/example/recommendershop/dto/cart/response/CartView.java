package com.example.recommendershop.dto.cart.response;

import com.example.recommendershop.dto.cartDetail.CartDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartView {
    private UUID cartId;
    private List<CartDetailResponse> cartDetails;
}
