package com.jibro.shop.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Make dto 코드
 * 주문 신규 등록 화면으로 이동할 때, 제품코드와 수량 전달하는 용도
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderMakeDto {
	
	/* 관련된 제품 코드 */
    private String productId;
    
    /* 선택 수량 */
	private int SelectedCount;
	
}
