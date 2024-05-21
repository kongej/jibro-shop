package com.jibro.shop.data.dto.order;

import lombok.*;

import java.time.LocalDateTime;
/**
 * @author kej
 * @since 2024.05.21
 * Order Response Api Dto 코드
 * fulfill에서 배송상태 받아오는 용도
 * **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseApiDto {
    /* 주문코드(pk) */
    private String orderId;

    /* 현 상태 */
    private int status;

    /* 송장번호 */
    private Integer invc;
}
