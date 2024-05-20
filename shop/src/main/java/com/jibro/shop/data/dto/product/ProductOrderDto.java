package com.jibro.shop.data.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.20
 * Product Order dto 코드
 * 신규 주문 신청 시 제품 관련 정보와 숫자 돌려 받는 코드
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductOrderDto {
	/* 제품코드(pk) */
	private String productId;
	
	/* 제품명 */
	private String product;
	
	/* 선택 수량 */
	private int selectedCount;
	
	/* 판매가 */
	private Integer cost;
	
	/* 이미지 */
	private String img;

	
}
