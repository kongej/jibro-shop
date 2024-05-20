package com.jibro.shop.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Response dto 코드
 * product 조회 시 코드
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductResponseDto {
	
	/* 제품코드(pk) */
	private String productId;
	
	/* 제품명 */
	private String product;
	
	/* 수량 */
	private Integer productCount;
	
	/* 판매가 */
	private Integer cost;
	
	/* 이미지 */
	private String img;
	
	
}