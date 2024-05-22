package com.jibro.shop.data.dto.product;

import lombok.*;
/**
 * @author kej
 * @since 2024.05.21
 * Product Stock dto 코드
 * fulfill에서 제품 수량을 받아오는 용도
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductStockDto {
    /* 제품코드(pk) */
    private String productId;

    /* 수량 */
    private Integer productCount;
}
