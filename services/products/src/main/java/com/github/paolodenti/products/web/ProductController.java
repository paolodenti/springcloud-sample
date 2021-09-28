package com.github.paolodenti.products.web;

import com.github.paolodenti.products.model.Product;
import com.github.paolodenti.products.repository.ProductRepository;
import com.github.paolodenti.products.web.conditioner.ProductControllerConditioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    public List<Product> products(@RequestHeader("X-Correlation-ID") String correlationId) {
        logger.info("Received correlation ID: '{}'", correlationId);
        return productRepository.findAll();
    }

    @GetMapping("conditioner/{delay}")
    public String conditioner(@PathVariable long delay) {
        productControllerConditioner.setMillisDelay(delay);
        return String.valueOf(delay);
    }
}
