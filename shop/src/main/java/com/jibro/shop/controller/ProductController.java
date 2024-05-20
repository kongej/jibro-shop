package com.jibro.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.shop.service.ProductService;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Controller 코드
 * Shop 내부용 컨트롤러 (외부 api 제공 x)
 * **/
@Controller
public class ProductController {
	
	// 의존성 주입(ProductService)
	@Autowired
	private ProductService productService;
	
	// product 상세 화면으로 이동
	@GetMapping("/product/{productId}")
	public ModelAndView getProduct() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	// order 신규 등록 화면으로 이동
	@PostMapping("/order/make")
	public ModelAndView makeOrder() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
