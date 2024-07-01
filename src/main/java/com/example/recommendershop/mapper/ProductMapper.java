package com.example.recommendershop.mapper;

import com.example.recommendershop.dto.product.request.ProductRequest;
import com.example.recommendershop.dto.product.response.ProductAvatar;
import com.example.recommendershop.dto.product.response.ProductResponse;
import com.example.recommendershop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest productRequest);

    ProductResponse toDao(Product product);
    List<ProductAvatar> toListDao(List<Product> product);
    void update(ProductRequest productRequest, @MappingTarget Product product);



}
