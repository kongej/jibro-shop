package com.jibro.shop.service;

import com.jibro.shop.data.dto.order.OrderMakeDto;
import com.jibro.shop.data.dto.product.ProductOrderDto;
import com.jibro.shop.data.dto.product.ProductResponseDto;
import com.jibro.shop.data.dto.product.ProductStockDto;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Service 기조 코드
 * **/
public interface ProductService {
	
	/* 제품코드로 제품 상세 정보 제공 */
	ProductResponseDto getProduct(String productId);

	/* 주문 등록 페이지에 필요한 정보 제공 */
	ProductOrderDto makeOrder(OrderMakeDto orderMakeDto);

	/* 수량 정보 업데이트 */
	ProductResponseDto updateStock(ProductStockDto productStockDto);
}
