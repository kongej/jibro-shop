package com.jibro.shop.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.19
 * Order entity 코드
 * **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "seller_order")
public class Order {
	/* 주문코드(pk) */
	@Id
	@Column(name = "order_id", length = 50)
	private String orderId;
	
	/* 선택 수량 */
	@Column(name = "selected_count", nullable = false)
	private Integer SelectedCount;
	
	/* 총 가격 */
	@Column(name = "total_cost", nullable = false)
	private Integer totalCost;
	
	/* 구매자명 */
	@Column(name = "orderer_name", nullable = false, length = 20)
	private String ordererName;
	
	/* 주문 비밀번호 */
	@Column(name = "order_password", nullable = false, length = 20)
	private String orderPassword;
	
	/* 전화번호 */
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;
	
	/* 주소 */
	@Column(name = "address", nullable = false, length = 255)
	private String address;
	
	/* 현 상태 */
	@Column(name = "status", nullable = false)
	@Builder.Default
	private Integer status = 0;
	
	/* 송장번호 */
	@Column(name = "invc", unique = true)
	private Integer invc;
	
	/* 주문 날짜 */
	@CreatedDate
	@Column(name = "order_date", 
			nullable = false, 
			updatable = false)
	private LocalDate orderDate;
	
}
