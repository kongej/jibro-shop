package com.jibro.shop.data.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.shop.data.entity.Order;

/**
 * @author ljy
 * @since 2024.05.19
 * 주문 관련 repository 코드
 * **/
public interface OrderRepository extends JpaRepository<Order, String> {
	
	/* orderId 기반으로 단일 상품 조회 */
	Optional<Order> findByOrderId(String orderId);

	/* 조회 당일 날짜와 같은 날 들어온 주문 갯수를 세는 쿼리 */
	Long countByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
	
	/* 주문번호, 구매자 이름, 비밀번호 모두 일치할 때 주문 조회 가능하게 하는 쿼리 */
	Optional<Order> findByOrderIdAndOrdererNameAndOrderPassword(String orderId, String ordererName, String orderPassword);
}

