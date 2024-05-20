package com.jibro.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.shop.service.OrderService;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Controller 코드
 * Shop 내부용 컨트롤러 (외부 api 제공 x)
 * **/
@Controller
public class OrderController {
	
	// 의존성 주입(OrderService)
	@Autowired
	private OrderService orderService;

	@PostMapping("/order/create")
	public String createOrder() {
		
		return "redirect:/order/check";
	}
	
	@GetMapping("/order/check")
	public ModelAndView checkOrder() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@PostMapping("/order/detail")
	public ModelAndView getOrder() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
