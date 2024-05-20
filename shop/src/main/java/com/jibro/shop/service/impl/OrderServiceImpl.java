package com.jibro.shop.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.shop.data.dto.order.OrderCheckDto;
import com.jibro.shop.data.dto.order.OrderCreateDto;
import com.jibro.shop.data.dto.order.OrderResponseDto;
import com.jibro.shop.data.entity.Order;
import com.jibro.shop.data.repository.OrderRepository;
import com.jibro.shop.data.repository.ProductRepository;
import com.jibro.shop.service.OrderService;
import com.jibro.shop.utils.OrderIdGenerator;

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
	
	// 주문 신규 생성
	@Override
	public String createOrder(OrderCreateDto orderCreateDto) {
		LOGGER.info("[createOrder] input orderCreateDto : {}", orderCreateDto);
		// 결과 int로 return (0: 실패, 1: 성공)
		int result = 1;
		
		// 이전까지 오늘 내로 주문 들어온 수 카운트
		LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
       
        System.out.println("startOfDay : " + startOfDay);
        System.out.println("endOfDay : " + endOfDay);
        
		int countBeforeOrders = this.orderRepository.countByCreatedAtBetween(startOfDay, endOfDay).intValue();
		LOGGER.info("[createOrder] find today's order count : {}", countBeforeOrders);
		
		// 주문번호 기준에 맞춰 신규 생성
		String newOrderId = OrderIdGenerator.generateOrderCode(countBeforeOrders + 1);
		LOGGER.info("[createOrder] make new order ID : {}", newOrderId);
		
		// product 객체 불러옴
		
		// order 엔티티 객체 생성
		Order order = Order.builder()
				.orderId(newOrderId)
				.selectedCount(orderCreateDto.getSelectedCount())
				.totalCost(orderCreateDto.getTotalCost())
				.ordererName(orderCreateDto.getOrdererName())
				.orderPassword(orderCreateDto.getOrderPassword())
				.phoneNumber(orderCreateDto.getPhoneNumber())
				.address(orderCreateDto.getAddress())
				.productId(orderCreateDto.getProductId())
				.build();
		
		Order savedOrder = orderRepository.save(order);
		LOGGER.info("[createOrder] savedOrder : {}", savedOrder);
		
		return savedOrder.getOrderId();
	}

	// 주문 상세 정보 조회
	@Override
	public OrderResponseDto getOrder(OrderCheckDto orderCheckDto) {
		LOGGER.info("[getOrder] input orderCheckDto : {}", orderCheckDto);
		
		Optional<Order> order = this.orderRepository.findByOrderIdAndOrdererNameAndOrderPassword(orderCheckDto.getOrderId(), orderCheckDto.getOrdererName(), orderCheckDto.getOrderPassword());
		LOGGER.info("[getOrder] find order : {}", order);
		
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		
		if (order.isPresent()) {
			orderResponseDto.setOrderId(order.get().getOrderId());
			orderResponseDto.setSelectedCount(order.get().getSelectedCount());
			orderResponseDto.setTotalCost(order.get().getTotalCost());
			orderResponseDto.setOrdererName(order.get().getOrdererName());
			orderResponseDto.setOrderPassword(order.get().getOrderPassword());
			orderResponseDto.setPhoneNumber(order.get().getPhoneNumber());
			orderResponseDto.setAddress(order.get().getAddress());
			orderResponseDto.setStatus(order.get().getStatus());
			orderResponseDto.setInvc(order.get().getInvc() != null ? order.get().getInvc() : 0);
			orderResponseDto.setCreatedAt(order.get().getCreatedAt());
			orderResponseDto.setUpdatedAt(order.get().getUpdatedAt());
			orderResponseDto.setProductId(order.get().getProductId());
		} else {
			System.out.println("false");
		}
		/* LOGGER.info("[getOrder] set orderResponseDto : {}", orderResponseDto); */
		
		return orderResponseDto;
	}


}
