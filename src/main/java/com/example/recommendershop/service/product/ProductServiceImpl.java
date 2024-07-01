package com.example.recommendershop.service.product;

import com.example.recommendershop.dto.ApiListBaseRequest;
import com.example.recommendershop.dto.BasePage;
import com.example.recommendershop.dto.SearchEntity;
import com.example.recommendershop.dto.product.request.ProductRequest;
import com.example.recommendershop.dto.product.response.ProductAvatar;
import com.example.recommendershop.dto.product.response.ProductResponse;
import com.example.recommendershop.entity.Category;
import com.example.recommendershop.entity.Product;
import com.example.recommendershop.enums.Role;
import com.example.recommendershop.exception.MasterException;
import com.example.recommendershop.mapper.CategoryMapper;
import com.example.recommendershop.mapper.ProductMapper;
import com.example.recommendershop.repository.CategoryRepository;
import com.example.recommendershop.repository.ProductRepository;
import com.example.recommendershop.utils.FilterDataUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final HttpSession httpSession;
    List<Specification<Product>> specifications;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository, CategoryMapper categoryMapper, HttpSession httpSession){
        this.productRepository =productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.httpSession = httpSession;
        specifications = new ArrayList<>();

    }
    @Override
    @Transactional
//    public ProductResponse create(ProductRequest productRequest) {
//        // Kiểm tra xem sản phẩm đã tồn tại hay chưa
//        if (productRepository.findProductByName(productRequest.getName()) != null) {
//            throw new MasterException(HttpStatus.BAD_REQUEST, "Sản phẩm đã tồn tại");
//        }
//
//        // Tìm danh mục bằng categoryId
//        Category category = categoryRepository.findById(productRequest.getCategoryId())
//                .orElseThrow(() -> new MasterException(HttpStatus.NOT_FOUND, "Danh mục không tồn tại"));
//
//        // Tạo mới sản phẩm và thiết lập danh mục
//        Product product = productMapper.toEntity(productRequest);
//        product.setCategory(category);
//
//        // Lưu sản phẩm vào cơ sở dữ liệu
//        product = productRepository.save(product);
//
//        // Chuyển đổi thành ProductResponse để trả về cho client
//        ProductResponse productResponse = productMapper.toDao(product);
//        return productResponse;
//    }
    public ProductResponse create(ProductRequest productRequest) {
        String roleStr = (String) httpSession.getAttribute("Role");
        Role role = Role.valueOf(roleStr);
        if(!(role.equals(Role.ADMIN) || role.equals(Role.SUB_ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        if (productRepository.findProductByName(productRequest.getName()) != null) {
            throw new MasterException(HttpStatus.BAD_REQUEST, "Sản phẩm đã tồn tại");
        }

        // Tìm danh mục bằng categoryId
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new MasterException(HttpStatus.NOT_FOUND, "Danh mục không tồn tại"));

        // Tạo mới sản phẩm và thiết lập danh mục
        Product product = productMapper.toEntity(productRequest);
        product.setCategory(category);

        // Lưu sản phẩm vào cơ sở dữ liệu
        product = productRepository.save(product);

        // Chuyển đổi thành ProductResponse để trả về cho client
        ProductResponse productResponse = productMapper.toDao(product);
        return productResponse;
    }
    @Override
    @Transactional
    public ProductResponse update(UUID productId, ProductRequest productRequest) {
        String roleStr = (String) httpSession.getAttribute("Role");
        Role role = Role.valueOf(roleStr);
        if(!(role.equals(Role.ADMIN) || role.equals(Role.SUB_ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new MasterException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm"));

        // Tìm danh mục bằng categoryId
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new MasterException(HttpStatus.NOT_FOUND, "Danh mục không tồn tại"));

        // Cập nhật thông tin sản phẩm và thiết lập danh mục
        productMapper.update(productRequest, existingProduct);
        existingProduct.setCategory(category);
        Product updatedProduct = productRepository.save(existingProduct);

        // Chuyển đổi thành ProductResponse để trả về cho client
        ProductResponse productResponse = productMapper.toDao(updatedProduct);
        return productResponse;
    }
    @Override
    public void delete(UUID productId){
        String roleStr = (String) httpSession.getAttribute("Role");
        Role role = Role.valueOf(roleStr);
        if(!(role.equals(Role.ADMIN) || role.equals(Role.SUB_ADMIN))){
            throw new MasterException(HttpStatus.FORBIDDEN, "bạn không có quyền!");
        }
        if(!productRepository.existsById(productId)){
            throw new MasterException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm");
        }
        productRepository.deleteById(productId);
    }
    public ProductResponse detail(UUID productId){
        if(!productRepository.existsById(productId)){
            throw new MasterException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm");
        }
        return productMapper.toDao(productRepository.getReferenceById(productId));


    }
    protected BasePage<ProductAvatar> map(Page<Product> page) {
        BasePage<ProductAvatar> rPage = new BasePage<>();
        rPage.setData(productMapper.toListDao(page.getContent()));
        rPage.setTotalPage(page.getTotalPages());
        rPage.setTotalRecord( page.getTotalElements());
        rPage.setPage(page.getPageable().getPageNumber());
        return rPage;
    }

    public BasePage<ProductAvatar> getAll(ApiListBaseRequest listRequest){
        Page<Product> page = productRepository.findAll(FilterDataUtil.buildPageRequest(listRequest));
        return this.map(page);
    }
    @Override
    public BasePage<ProductAvatar> search(SearchEntity search, ApiListBaseRequest filter) {
        String keyword = search.getKeyword();

        Specification<Product> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(keyword)) {
                predicates.add(builder.like(builder.upper(root.get("name")), "%" + keyword.trim().toUpperCase() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Product> page = productRepository.findAll(spec, FilterDataUtil.buildPageRequest(filter));
        return this.map(page);
    }

    public BasePage<ProductAvatar> getProductsByCategory(UUID categoryId, ApiListBaseRequest listBaseRequest){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new MasterException(HttpStatus.NOT_FOUND, "không tìm thấy sản phẩm của category này"));
        Page<Product> productPage = productRepository.findProductByCategory(category, FilterDataUtil.buildPageRequest(listBaseRequest));
        return this.map(productPage);
    }



}
