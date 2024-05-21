package com.jibro.shop.data.dto.order;

import lombok.*;

import java.time.LocalDateTime;
/**
 * @author kej
 * @since 2024.05.21
 * Order Api Dto 코드
 * 주문 정보 fulfill로 전달 하는 용도
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderApiDto {
    /* 주문코드(pk) */
    private String orderId;

    /* 선택 수량 */
    private int selectedCount;

    /* 구매자명 */
    private String ordererName;

    /* 연락처 */
    private String phoneNumber;

    /* 주소 */
    private String address;

    /* 현 상태 */
    private int status;

    /* 주문 날짜 */
    private LocalDateTime createdAt;

    /* 관련된 제품 코드 */
    private String productId;

    /* 셀러 정보 */
    private String seller;
}
