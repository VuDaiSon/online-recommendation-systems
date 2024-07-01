package com.example.recommendershop.dto.order.response;


import com.example.recommendershop.entity.Category;
import com.example.recommendershop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewModel {
    private Product product;
    private Category category;
    private String image;
}
