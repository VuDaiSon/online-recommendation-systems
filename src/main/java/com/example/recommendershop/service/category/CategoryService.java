package com.example.recommendershop.service.category;

import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.category.request.CategoryRequest;
import com.example.recommendershop.dto.category.response.CategoryAvatar;
import com.example.recommendershop.dto.category.response.CategoryResponse;

import java.util.UUID;

public interface CategoryService {
    public CategoryResponse create(CategoryRequest categoryRequest);
    public CategoryResponse update(UUID categoryId, CategoryRequest categoryRequest);
    public CategoryResponse detail(UUID categoryID);
    public void delete(UUID categoryId);
    public BasePage<CategoryAvatar> getAll(ApiListBaseRequest listRequest);

}
