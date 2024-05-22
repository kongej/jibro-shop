package com.jibro.shop.controller.api;

import com.jibro.shop.data.dto.product.ProductStockDto;
import com.jibro.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiController {
    @Autowired
    ProductService productService;

    @PutMapping("order/update/stock")
    public String updateStock(@RequestBody ProductStockDto productStockDto){
        String status;
        if (productStockDto != null){
            productService.updateStock(productStockDto);
            status = "success";
            return status;
        }
        return "fail";
    }

}
