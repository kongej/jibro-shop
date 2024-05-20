package com.jibro.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.shop.data.dto.OrderMakeDto;
import com.jibro.shop.service.ProductService;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Controller 코드
 * Shop 내부용 컨트롤러 (외부 api 제공 x)
 * **/
@Controller
public class ProductController {
	
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	// 의존성 주입(ProductService)
	@Autowired
	private ProductService productService;
	
	// product 상세 화면으로 이동
	@GetMapping("/product/{productId}")
	public ModelAndView getProduct(
			@PathVariable String productId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/detail");
		return mav;
	}
	
	// order 신규 등록 화면으로 이동
	@PostMapping("/order/make")
	public ModelAndView makeOrder(OrderMakeDto orderMakeDto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/create");
		return mav;
	}
}
