package com.github.paolodenti.dashboard.service.client;

import com.github.paolodenti.dashboard.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("products")
public interface ProductsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "product", consumes = "application/json")
    List<Product> getProducts(@RequestHeader("X-Correlation-ID") String correlationId);
}
