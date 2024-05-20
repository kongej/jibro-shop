package com.jibro.shop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Product;

/**
 * @author ljy
 * @since 2024.05.19
 * 제품 관련 repository 코드
 * **/
public interface ProductRepository extends JpaRepository<Product, String> {

}

