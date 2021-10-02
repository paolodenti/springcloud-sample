package com.github.paolodenti.products.web;

import com.github.paolodenti.products.model.Product;
import com.github.paolodenti.products.repository.ProductRepository;
import com.github.paolodenti.products.web.conditioner.ProductControllerConditioner;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    final private Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    private final ProductControllerConditioner productControllerConditioner;

    public ProductController(ProductRepository productRepository, ProductControllerConditioner productControllerConditioner) {
        this.productRepository = productRepository;
        this.productControllerConditioner = productControllerConditioner;
    }

    @GetMapping
    @Timed(value = "products.nocheck.time", description = "Time to load products")
    public List<Product> products() {
        logger.info("executing products products");
        return productRepository.findAll();
    }

    @GetMapping("conditioner/{delay}")
    public String conditioner(@PathVariable long delay) {
        productControllerConditioner.setMillisDelay(delay);
        return String.valueOf(delay);
    }
}
