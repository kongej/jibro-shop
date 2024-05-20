package com.jibro.shop.data.dto;

import java.util.List;

import com.jibro.shop.data.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.20
 * Product dto 코드
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
	/* 제품코드(pk) */
	private String productId;
	
	/* 제품명 */
	private String product;
	
	/* 수량 */
	private Integer productCount = 0;
	
	/* 판매가 */
	private Integer cost;
	
	/* 이미지 */
	private String img;
	
	/* order 엔티티와 OneToMany 매핑 */
	private List<Order> orders;
}
