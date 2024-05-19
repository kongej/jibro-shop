package com.jibro.shop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}

