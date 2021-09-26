package com.github.paolodenti.products.web;

import com.github.paolodenti.products.model.Product;
import com.github.paolodenti.products.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> products() {
        System.out.println(productRepository.findAll());
        return productRepository.findAll();
    }
}
