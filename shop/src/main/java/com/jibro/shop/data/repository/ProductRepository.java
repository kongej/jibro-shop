package com.jibro.shop.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Product;

/**
 * @author ljy
 * @since 2024.05.19
 * 제품 관련 repository 코드
 * **/
public interface ProductRepository extends JpaRepository<Product, String> {
	
	// productId 기반으로 상품 조회
	Optional<Product> findByProductId(String productId);
}

