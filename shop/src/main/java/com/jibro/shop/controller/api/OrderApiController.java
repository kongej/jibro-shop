package com.jibro.shop.controller.api;

import com.jibro.shop.data.dto.order.OrderStatusApiDto;
import com.jibro.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApiController {
    @Autowired
    OrderService orderService;

    @PutMapping("order/update/delivery")
    public String updateDelivery(@RequestBody OrderStatusApiDto orderResponseApiDto) {
        String status;
        if (orderResponseApiDto != null) {
            orderService.updateDelivery(orderResponseApiDto);
            status = "success";
            return status;
        }
        return "fail";
    }

}