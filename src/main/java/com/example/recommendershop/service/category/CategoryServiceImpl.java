package com.example.recommendershop.service.category;

import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.category.request.CategoryRequest;
import com.example.recommendershop.dto.category.response.CategoryAvatar;
import com.example.recommendershop.dto.category.response.CategoryResponse;
import com.example.recommendershop.entity.Category;
import com.example.recommendershop.entity.User;
import com.example.recommendershop.enums.Role;
import com.example.recommendershop.exception.MasterException;
import com.example.recommendershop.mapper.CategoryMapper;
import com.example.recommendershop.repository.CategoryRepository;
import com.example.recommendershop.repository.UserRepository;
import com.example.recommendershop.utils.FilterDataUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper,  HttpSession httpSession,
                               UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.httpSession = httpSession;
        this.userRepository = userRepository;
    }
    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        UUID userId = (UUID) httpSession.getAttribute("UserId");
        User user = userRepository.getByUserId(userId);
        if(!(user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.SUB_ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        if(categoryRepository.findCategoryByName(categoryRequest.getName()) !=null){
            throw new MasterException(HttpStatus.BAD_REQUEST, "Sản phẩm đã tồn tại");
        }
        Category category = categoryRepository.save(categoryMapper.toEntity(categoryRequest));
        CategoryResponse categoryResponse = categoryMapper.toDao(category);
        System.out.print("Tao san pham thanh cong");
        return categoryResponse;
    }

    @Override
    public CategoryResponse update(UUID categoryId, CategoryRequest categoryRequest) {
        UUID userId = (UUID) httpSession.getAttribute("UserId");
        User user = userRepository.getByUserId(userId);
        if(!(user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.SUB_ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        if(!categoryRepository.existsById(categoryId)){
            throw new MasterException(HttpStatus.NOT_FOUND, "khong ton tai");
        }
        Category category = categoryRepository.getReferenceById(categoryId);
        categoryMapper.update(categoryRequest, category);
        CategoryResponse categoryResponse = categoryMapper.toDao(categoryRepository.save(category));
        return categoryResponse;
    }

    @Override
    public CategoryResponse detail(UUID categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            throw new MasterException(HttpStatus.NOT_FOUND, "khong tim thay san pham");
        }
        return categoryMapper.toDao(categoryRepository.getReferenceById(categoryId));
    }

    @Override
    public void delete(UUID categoryId) {
        UUID userId = (UUID) httpSession.getAttribute("UserId");
        User user = userRepository.getByUserId(userId);
        if(!(user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.SUB_ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        if(!categoryRepository.existsById(categoryId)){
            throw new MasterException(HttpStatus.NOT_FOUND, "Khong tim thay san pham");
        }
        categoryRepository.deleteById(categoryId);
    }
    protected BasePage<CategoryAvatar> map(Page<Category> page) {
        BasePage<CategoryAvatar> rPage = new BasePage<>();
        rPage.setData(categoryMapper.toListDao(page.getContent()));
        rPage.setTotalPage(page.getTotalPages());
        rPage.setTotalRecord( page.getTotalElements());
        rPage.setPage(page.getPageable().getPageNumber());
        return rPage;
    }
    public BasePage<CategoryAvatar> getAll(ApiListBaseRequest listRequest){
        Page<Category> page = categoryRepository.findAll(FilterDataUtil.buildPageRequest(listRequest));
        return this.map(page);
    }

}
