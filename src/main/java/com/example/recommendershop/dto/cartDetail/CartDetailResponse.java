package com.example.recommendershop.dto.cartDetail;

import com.example.recommendershop.dto.order.response.ProductInOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailResponse {
    private int quantity;
    private ProductInOrder product;
    public Double getTotalAmount() {return product.getPrice() * quantity;  }

}
