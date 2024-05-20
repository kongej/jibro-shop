package com.jibro.shop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.shop.data.dto.ProductDto;
import com.jibro.shop.data.repository.OrderRepository;
import com.jibro.shop.data.repository.ProductRepository;
import com.jibro.shop.service.ProductService;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Service 코드
 * **/
@Service
public class ProductServiceImpl implements ProductService {
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	// 필요 레포지토리 연결
	private final ProductRepository productRepository;
	
	// 의존성 주입(di)
	@Autowired // => 없어도 되는지?
	public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public ProductDto getProduct(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
