package com.jibro.shop.data.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Check Dto 코드
 * 비회원 주문 확인 시 사용
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderCheckDto {

	/* 주문코드(pk) */
	private String orderId;
	
	/* 구매자명 */
	private String ordererName;
	
	/* 주문 비밀번호 */
	private String orderPassword;
}
