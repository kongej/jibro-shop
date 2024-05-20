package com.jibro.shop.service;

import com.jibro.shop.data.dto.order.OrderCheckDto;
import com.jibro.shop.data.dto.order.OrderCreateDto;
import com.jibro.shop.data.dto.order.OrderResponseDto;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Service 기조 코드
 * **/
public interface OrderService {
	
	/* 주문 신규 등록 */
	String createOrder(OrderCreateDto orderCreateDto);
	
	/* 주문 조회 */
	OrderResponseDto getOrder(OrderCheckDto orderCheckDto);
	
}
