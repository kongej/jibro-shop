package com.jibro.shop.controller;

import java.util.NoSuchElementException;

import com.jibro.shop.data.dto.order.OrderResponseApiDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.shop.data.dto.order.OrderCheckDto;
import com.jibro.shop.data.dto.order.OrderCreateDto;
import com.jibro.shop.data.dto.order.OrderResponseDto;
import com.jibro.shop.service.OrderService;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Controller 코드
 * Shop 내부용 컨트롤러 (외부 api 제공 x)
 * **/
@Controller
public class OrderController {
	
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	// 의존성 주입(OrderService)
	@Autowired
	private OrderService orderService;

	// 주문 신규 등록
	@PostMapping("/order/create")
	public ModelAndView createOrder(OrderCreateDto orderCreateDto) {
		ModelAndView mav = new ModelAndView();
		String result = this.orderService.createOrder(orderCreateDto);
		mav.addObject("message", "주문이 완료되었습니다. (주문번호: " + result + ")");
		mav.addObject("location", "/order/check");
		mav.setViewName("common/check");
		return mav;
	}
	
	// 주문 확인 입력 페이지로 이동
	@GetMapping("/order/check")
	public ModelAndView checkOrder() {
		ModelAndView mav = new ModelAndView("order/check");
		return mav;
	}
	
	// 주문 상세 페이지로 이동
	@PostMapping("/order/check")
	public ModelAndView getOrder(
			@Validated OrderCheckDto orderCheckDto,
			Errors errors) {
		ModelAndView mav = new ModelAndView();
		
		/*
		 * if (errors.hasErrors()) { String errorMessage = errors .getFieldErrors() //
		 * 에러 뽑아오기 .stream() // 뽑아낸 error들 stream 방식으로 바꾸기 .map(x -> x.getField() +
		 * " : " + x.getDefaultMessage()) // error들 하나씩 꺼내서 한 쌍으로 만들어주기
		 * .collect(Collectors.joining("\n")); return this.checkException(errorMessage,
		 * String.format("/order/check", orderCheckDto.getOrderId())); }
		 */

		OrderResponseDto orderResponseDto = this.orderService.getOrder(orderCheckDto);
		mav.addObject("orderResponseDto", orderResponseDto);
		mav.setViewName("order/detail");
		return mav;
	}
	
	// error 처리
	private ModelAndView checkException(String message, String location) {
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", message);
		mav.addObject("location", location);
		mav.setViewName("common/check");
		return mav;
	}
	
	// 해당 주문번호가 없을 시 or 입력 값 틀렸을 시
	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
		return this.checkException("일치하는 정보가 없습니다.", "/order/check");
	}
}
