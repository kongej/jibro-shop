package com.jibro.shop.data.dto.order;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.20
 * Order Create dto 코드
 * order 신규 생성 시 사용
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderCreateDto {
	
	/* 선택 수량 */
	private int selectedCount;
	
	/* 총 가격 */
	private int totalCost;
	
	/* 구매자명 */
	private String ordererName;
	
	/* 주문 비밀번호 */
	private String orderPassword;
	
	/* 연락처 */
	private String phoneNumber;
	
	/* 주소 */
	private String address;
	
	/* 현 상태 */
	private int status;
	
	/* 송장번호 */
	private int invc;
	
	/* 관련된 제품 코드 */
    private String productId;
}
