package com.jibro.shop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Order;

/**
 * @author ljy
 * @since 2024.05.19
 * 주문 관련 repository 코드
 * **/
public interface OrderRepository extends JpaRepository<Order, String> {

}

