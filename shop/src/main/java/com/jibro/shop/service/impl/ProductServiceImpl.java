package com.jibro.shop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.shop.data.dto.order.OrderMakeDto;
import com.jibro.shop.data.dto.product.ProductOrderDto;
import com.jibro.shop.data.dto.product.ProductResponseDto;
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
	public ProductResponseDto getProduct(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOrderDto makeOrder(OrderMakeDto orderMakeDto) {
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		ProductOrderDto productOrderDto = new ProductOrderDto();
		
		productOrderDto.setProductId(productResponseDto.getProductId());
		productOrderDto.setCost(productResponseDto.getCost());
		productOrderDto.setProduct(productResponseDto.getProduct());
		productOrderDto.setImg(productResponseDto.getImg());
		productOrderDto.setSelectedCount(orderMakeDto.getSelectedCount());
		
		return productOrderDto;
	}

}
