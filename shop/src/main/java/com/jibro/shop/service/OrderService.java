package com.jibro.shop.service;

import com.jibro.shop.data.dto.OrderCheckDto;
import com.jibro.shop.data.dto.OrderCreateDto;
import com.jibro.shop.data.dto.OrderResponseDto;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Service 기조 코드
 * **/
public interface OrderService {
	
	/* 주문 신규 등록 */
	int createOrder(OrderCreateDto orderCreateDto);
	
	/* 주문 조회 */
	OrderResponseDto getOrder(OrderCheckDto orderCheckDto);
	
}
