package com.jibro.shop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.shop.data.dto.order.OrderCheckDto;
import com.jibro.shop.data.dto.order.OrderCreateDto;
import com.jibro.shop.data.dto.order.OrderResponseDto;
import com.jibro.shop.data.repository.OrderRepository;
import com.jibro.shop.data.repository.ProductRepository;
import com.jibro.shop.service.OrderService;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Service 코드
 * **/
@Service
public class OrderServiceImpl implements OrderService {
	
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	// 필요 레포지토리 연결
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	
	// 의존성 주입(di)
	@Autowired // => 없어도 되는지?
	public OrderServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}
	
	@Override
	public Integer createOrder(OrderCreateDto orderCreateDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderResponseDto getOrder(OrderCheckDto orderCheckDto) {
		// TODO Auto-generated method stub
		return null;
	}


}
