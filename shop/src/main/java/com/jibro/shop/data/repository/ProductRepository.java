package com.jibro.shop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}

