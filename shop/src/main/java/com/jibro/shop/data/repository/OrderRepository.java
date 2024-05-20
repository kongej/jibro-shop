package com.jibro.shop.data.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Order;

/**
 * @author ljy
 * @since 2024.05.19
 * 주문 관련 repository 코드
 * **/
public interface OrderRepository extends JpaRepository<Order, String> {

	// 조회 당일 날짜와 같은 날 들어온 주문 갯수를 세는 쿼리
	Long countByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}

