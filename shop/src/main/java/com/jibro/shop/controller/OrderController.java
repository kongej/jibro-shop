package com.jibro.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.shop.data.dto.OrderCheckDto;
import com.jibro.shop.data.dto.OrderCreateDto;
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
	public String createOrder(OrderCreateDto orderCreateDto) {
		
		return "redirect:/order/check";
	}
	
	// 주문 확인 입력 페이지로 이동
	@GetMapping("/order/check")
	public ModelAndView checkOrder() {
		ModelAndView mav = new ModelAndView("order/check");
		return mav;
	}
	
	// 주문 상세 페이지로 이동
	@PostMapping("/order/check")
	public ModelAndView getOrder(OrderCheckDto orderCheckDto) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("order/detail");
		return mav;
	}
}
