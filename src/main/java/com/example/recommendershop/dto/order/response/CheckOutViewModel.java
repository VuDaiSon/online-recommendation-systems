package com.example.recommendershop.dto.order.response;

import com.example.recommendershop.dto.cart.response.CartResponse;
import com.example.recommendershop.dto.cartDetail.CartDetailResponse;
import com.example.recommendershop.dto.user.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutViewModel {
    private UserResponse user;
    private CartResponse cart;
    private List<CartDetailResponse> cartDetails;
    private int subtotal;
    private int shippingFee;
    public int getTotal() {
        return subtotal + shippingFee;
    }
}
