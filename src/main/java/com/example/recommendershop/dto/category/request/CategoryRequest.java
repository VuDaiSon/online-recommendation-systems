package com.example.recommendershop.dto.category.request;

import com.example.recommendershop.dto.product.request.ProductRequest;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotNull
    private String name;
    @NotNull
    private String image;
}
