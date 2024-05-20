package com.jibro.shop.service;

import com.jibro.shop.data.dto.ProductDto;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Service 기조 코드
 * **/
public interface ProductService {
	
	/* 제품 상세 정보 제공 */
	ProductDto getProduct(String productId);

}
