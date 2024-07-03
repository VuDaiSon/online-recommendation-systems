package com.example.recommendershop.dto.product.response;

import com.example.recommendershop.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAvatar extends BaseDto {
    private UUID productId;
    private String name;
    private int quantity;
    private String image;
    private Double price;
}
