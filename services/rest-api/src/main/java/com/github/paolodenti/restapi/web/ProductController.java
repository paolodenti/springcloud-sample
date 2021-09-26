package com.github.paolodenti.restapi.web;

import com.github.paolodenti.restapi.model.Product;
import com.github.paolodenti.restapi.service.client.ProductsFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductsFeignClient productsFeignClient;

    public ProductController(ProductsFeignClient productsFeignClient) {
        this.productsFeignClient = productsFeignClient;
    }

    @GetMapping
    public List<Product> test() {
        return productsFeignClient.getProducts();
    }
}
