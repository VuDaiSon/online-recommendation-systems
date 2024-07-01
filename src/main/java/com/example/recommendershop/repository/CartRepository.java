package com.example.recommendershop.repository;

import com.example.recommendershop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID>, JpaSpecificationExecutor<Cart> {
    Optional<Cart> findCartByUser_UserIdAndStatus(UUID userId, String status);
    Optional<Cart> findByUser_UserIdAndCartId(UUID userId, UUID cartId);
}
