package com.jibro.shop.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.jibro.shop.data.dto.product.ProductStockDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.shop.data.dto.order.OrderMakeDto;
import com.jibro.shop.data.dto.product.ProductOrderDto;
import com.jibro.shop.data.dto.product.ProductResponseDto;
import com.jibro.shop.data.entity.Product;
import com.jibro.shop.data.repository.OrderRepository;
import com.jibro.shop.data.repository.ProductRepository;
import com.jibro.shop.service.ProductService;

import javax.persistence.EntityNotFoundException;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Service 코드
 * **/
@Service
public class ProductServiceImpl implements ProductService {
	// 로그 설정
	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	// 필요 레포지토리 연결
	private final ProductRepository productRepository;
	
	// 의존성 주입(di)
	@Autowired // => 없어도 되는지?
	public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
		this.productRepository = productRepository;
	}
	
	// 상품 상세 정보 return
	@Override
	public ProductResponseDto getProduct(String productId) throws NoSuchElementException {
		LOGGER.info("[getProduct] input productId : {}", productId);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Product not found with id : " + productId));
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		// 해당 product 존재 시, 해당 값 ProductResponseDto에 넣어서 보내주기
		LOGGER.info("[getProduct] product number : {}, name : {}", product.getProductId(),
	            product.getProduct());
		productResponseDto.setProductId(product.getProductId());
		productResponseDto.setProduct(product.getProduct());
		productResponseDto.setProductCount(product.getProductCount());
		productResponseDto.setImg(product.getImg());
		productResponseDto.setCost(product.getCost());
		
		return productResponseDto;
	}

	// 주문 위한 상품 상세 정보 & 이전 페이지에서 건네받은 상품 선택 개수 ProductOrderDto 형태로 합쳐서 return
	@Override
	public ProductOrderDto makeOrder(OrderMakeDto orderMakeDto) {
		
		LOGGER.info("[makeOrder] input orderMakeDto productId : {}, selectedCount : {}", 
				orderMakeDto.getProductId(), orderMakeDto.getSelectedCount());
		Product product = productRepository.findById(orderMakeDto.getProductId())
				.orElseThrow(() -> new NoSuchElementException("Product not found with id : " + orderMakeDto.getProductId()));
		
		ProductOrderDto productOrderDto = new ProductOrderDto();
		productOrderDto.setSelectedCount(orderMakeDto.getSelectedCount());
		
		// 해당 product 존재 시, 해당 값 ProductResponseDto에 넣어서 보내주기
		LOGGER.info("[makeOrder] product number : {}, name : {}", product.getProductId(),
	            product.getProduct());
		productOrderDto.setProductId(product.getProductId());
		productOrderDto.setProduct(product.getProduct());
		productOrderDto.setImg(product.getImg());
		productOrderDto.setCost(product.getCost());
		
		return productOrderDto;
	}

	// 풀필먼트에서 수량 정보 업데이트
	@Override
	public ProductResponseDto updateStock(ProductStockDto productStockDto) throws NoSuchElementException  {
		Product responseProduct = this.productRepository.findById(productStockDto.getProductId())
				.orElseThrow(()-> new NoSuchElementException("Product not found with id" + productStockDto.getProductId()));

		// 수량 setting
		responseProduct.setProductCount(productStockDto.getProductCount());

		Product updateProduct = this.productRepository.save(responseProduct);

		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setProductId(updateProduct.getProductId());
		productResponseDto.setProduct(updateProduct.getProduct());
		productResponseDto.setProductCount(updateProduct.getProductCount());
		productResponseDto.setCost(updateProduct.getCost());
		productResponseDto.setImg(updateProduct.getImg());

		System.out.println(updateProduct.toString());
		LOGGER.info("[get Stock] set updateProduct : {}", updateProduct);

		return productResponseDto;
	}

}
