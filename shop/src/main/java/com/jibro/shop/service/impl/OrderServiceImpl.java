package com.jibro.shop.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jibro.shop.data.dto.order.OrderApiDto;
import com.jibro.shop.data.dto.order.OrderCheckDto;
import com.jibro.shop.data.dto.order.OrderCreateDto;
import com.jibro.shop.data.dto.order.OrderResponseDto;
import com.jibro.shop.data.entity.Order;
import com.jibro.shop.data.repository.OrderRepository;
import com.jibro.shop.data.repository.ProductRepository;
import com.jibro.shop.service.OrderService;
import com.jibro.shop.utils.OrderIdGenerator;

import reactor.core.publisher.Mono;

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
		
		// 주문 정보 db에 저장
		Order savedOrder = orderRepository.save(order);
		LOGGER.info("[createOrder] savedOrder : {}", savedOrder);
		
		// api 요청을 풀필먼트 컨트롤러 측에 전달
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:9000")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		OrderApiDto orderApiDto = new OrderApiDto();
		orderApiDto.setOrderId(savedOrder.getOrderId());
		orderApiDto.setSelectedCount(savedOrder.getSelectedCount());
		orderApiDto.setOrdererName(savedOrder.getOrdererName());
		orderApiDto.setPhoneNumber(savedOrder.getPhoneNumber());
		orderApiDto.setAddress(savedOrder.getAddress());
		orderApiDto.setStatus(savedOrder.getStatus());
		orderApiDto.setCreatedAt(savedOrder.getCreatedAt());
		orderApiDto.setProductId(savedOrder.getProductId());
		orderApiDto.setSeller("SA001");

		LOGGER.info("[orderApiDto] set orderApiDto : {}", orderApiDto);

		webClient.post().uri(uriBuilder -> uriBuilder.path("/order/receive-from-seller")
						.build())
				.bodyValue(orderApiDto)
				.exchangeToMono(clientResponse -> {
					if(clientResponse.statusCode().is2xxSuccessful()){
						System.out.println("데이터 전송 성공");
						LOGGER.info("[SendApi] statuesCode2xx mono.just : {}", Mono.just("success"));
						savedOrder.setSendOrder(1);
						return Mono.defer(()-> {
							orderRepository.save(savedOrder);
							return Mono.just("success");
						});
					}else {
						System.out.println("데이터 전송 실패");
						LOGGER.info("[SendApi] Api fail mono.just : {}", Mono.just("fail"));
						return Mono.just("fail");
					}
				})
				.block();
		
		
		return savedOrder.getOrderId();
	}

	// 주문 상세 정보 조회
	@Override
	public OrderResponseDto getOrder(OrderCheckDto orderCheckDto) {
		LOGGER.info("[getOrder] input orderCheckDto : {}", orderCheckDto);

		// 쿼리문으로 조건에 맞는 order 조회(주문번호 AND 구매자 성명 AND 비밀번호)
		Optional<Order> order = this.orderRepository.findByOrderIdAndOrdererNameAndOrderPassword(orderCheckDto.getOrderId(), orderCheckDto.getOrdererName(), orderCheckDto.getOrderPassword());
		LOGGER.info("[getOrder] find order : {}", order);

		// 프레젠테이션 영역으로 넘겨줄 dto 객체 생성
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		
		// order 정보 값 존재 시, 해당 dto에 값 담아줌
		/*TODO 만약 시간 있을 시, order 정보 없을 시 추가 대응 가능하도록 조치할 것 */
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
		}
		
		
		LOGGER.info("[getOrder] set orderResponseDto : {}", orderResponseDto);


		return orderResponseDto;
	}


//	// api 배송정보 업데이트
//	@Override
//	public OrderResponseDto updateDelivery(OrderResponseApiDto orderResponseApiDto) {
//		Optional<Order> responseOrder = this.orderRepository.findByOrderId(orderResponseApiDto.getOrderId());
//		// 객체 생성
//		OrderResponseDto orderResponseDto = new OrderResponseDto();
//
//		if (responseOrder.isPresent()) {
//			orderResponseDto.setOrderId(responseOrder.get().getOrderId());
//			orderResponseDto.setSelectedCount(responseOrder.get().getSelectedCount());
//			orderResponseDto.setTotalCost(responseOrder.get().getTotalCost());
//			orderResponseDto.setOrdererName(responseOrder.get().getOrdererName());
//			orderResponseDto.setOrderPassword(responseOrder.get().getOrderPassword());
//			orderResponseDto.setPhoneNumber(responseOrder.get().getPhoneNumber());
//			orderResponseDto.setAddress(responseOrder.get().getAddress());
//			orderResponseDto.setStatus(orderResponseApiDto.getStatus());
//			orderResponseDto.setInvc(orderResponseApiDto.getInvc());
//			orderResponseDto.setCreatedAt(responseOrder.get().getCreatedAt());
//			orderResponseDto.setProductId(responseOrder.get().getProductId());
//			this.orderRepository.save(orderResponseDto);
//		}
//
//		LOGGER.info("[getOrder] set responseOrder : {}", orderResponseDto);
//
//		return null;
//	}

}
