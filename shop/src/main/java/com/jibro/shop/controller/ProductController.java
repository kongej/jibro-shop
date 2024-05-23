package com.jibro.shop.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.shop.data.dto.order.OrderMakeDto;
import com.jibro.shop.data.dto.product.ProductOrderDto;
import com.jibro.shop.data.dto.product.ProductResponseDto;
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
			@PathVariable String productId) throws NoSuchElementException {
		ModelAndView mav = new ModelAndView();
		ProductResponseDto productResponseDto = this.productService.getProduct(productId);
		mav.addObject("productResponseDto", productResponseDto);
		mav.setViewName("product/detail");
		return mav;
	}
	
	// order 신규 등록 화면으로 이동
	@PostMapping("/order/make")
	public ModelAndView makeOrder(OrderMakeDto orderMakeDto) throws NoSuchElementException {
		ModelAndView mav = new ModelAndView();
		ProductOrderDto productOrderDto = this.productService.makeOrder(orderMakeDto);
		LOGGER.info("ProductOrderDto: {}", productOrderDto);
		mav.addObject("productOrderDto", productOrderDto);
		mav.setViewName("order/create");
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
	
	// 해당 상품 번호가 없을 시 or 입력 값 틀렸을 시
	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
		return this.checkException("일치하는 상품 정보가 없습니다.", "/product/24P001");
	}
	
}
